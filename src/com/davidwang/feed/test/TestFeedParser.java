package com.davidwang.feed.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.davidwang.feed.model.Feed;
import com.davidwang.feed.model.FeedItem;
import com.davidwang.feed.model.FeedSource;
import com.davidwang.feed.model.Image;
import com.davidwang.feed.read.RSSFeedParser;
import com.davidwang.feed.read.RSSFeedParserAsahi;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class TestFeedParser {

	// private static final String FILE_PATH = "ABCNewsFeed.json";
	// private static final String FILE_PATH = "NewYorkTimesFeed.json";
	// private static final String FILE_PATH = "FoxNewsFeed.json";
	// private static final String FILE_PATH = "CNNFeed.json";
	// private static final String FILE_PATH = "BBCNewsFeed.json";
	private static final String FILE_PATH = "TopStoriesJP.json";
	private static final String RSSFEED = "RSSFeed";
	private static final String COMPANY_NAME = "companyName";
	private static final String CHANNEL = "channel";
	private static final String LINK = "link";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String FEED_SOURCE = "jdbc:mysql://localhost:3306/source?useUnicode=yes&characterEncoding=UTF-8";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "Hongying@2017!";
	private static final String MESSAGE = "message";
	private static final String SOURCE = "source";
	private static final String ITEM = "item";
	private static final String TITLE = "title";
	private static final String TIME = "time";

	public static void main(String[] args)
			throws JSONException, SQLException, ClassNotFoundException, IOException, ParseException {

		List<FeedSource> feedSource = new ArrayList<FeedSource>();
		String result = readFile(FILE_PATH);
		feedSource = ParseFile(result);

		for (FeedSource fs : feedSource) {
			if (!fs.getLink().isEmpty()) {
				RSSFeedParserAsahi parser = new RSSFeedParserAsahi(fs.getLink());
				String source_name = fs.getCompanyName().trim();
				String channel = fs.getChannel().trim();

				if (source_name.isEmpty() || channel.isEmpty()) {
					System.out.println("source name and channel should not be empty!");
					break;
				}

				Feed feed = parser.readFeed(source_name, channel);
				// System.out.println(feed);

				if (!isInFeedSource(source_name, channel)) {
					insertToFeedSource(source_name, channel);
				}

				// get last update time for each source/channel
				String update_date = getLastUpdateDate(source_name, channel);
				System.out.println("Data_Base_date  =" + update_date);
				System.out.println("last_build_date =" + feed.getLastBuildDate());

				if (feed.getLastBuildDate() == null) {
					System.out.println("feed last build date is null! check the feed please! NO DB ACTION !");
				} else
					if (update_date == null || update_date.isEmpty() || !update_date.equals(feed.getLastBuildDate())) {
					updateFeedSourceDB(source_name, channel, feed.getLastBuildDate());
					insertItemDB(source_name, channel, feed.getItems());

					//write to Jason file
					writeTOJasonFile(source_name, channel,feed.getItems());
					
					//if message has image, write to image DB
					for (FeedItem item : feed.getItems()) {
						if (item.getImageList() != null && item.getImageList().size() != 0) {
							insertImageDB(source_name, channel, item.getImageList());
						}
					}

				} else {
					System.out.println("no need update feed_source table on " + source_name + " and " + channel);
				}

			}
		}
	}


	private static boolean isInFeedSource(String source_name, String channel)
			throws ClassNotFoundException, SQLException {

		Class.forName(JDBC_DRIVER);
		Connection conn = (Connection) DriverManager.getConnection(FEED_SOURCE, DB_USER_NAME, DB_PASSWORD);
		Statement stmt = (Statement) conn.createStatement();
		String sql = "SELECT * FROM source.feed_source where source_name=" + "'" + source_name + "'"
				+ " and channel=" + "'" + channel + "'";
		ResultSet rs = (ResultSet) stmt.executeQuery(sql);
		if (!rs.next()) {
			conn.close();
			return false;
		} else {
			conn.close();
			return true;
		}
	}

	private static void insertToFeedSource(String source_name, String channel)
			throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		Connection conn = (Connection) DriverManager.getConnection(FEED_SOURCE, DB_USER_NAME, DB_PASSWORD);
		String sql = "insert into source.feed_source (source_name, channel) " + " values (?,?)";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, source_name);
		ps.setString(2, channel);
		ps.execute();

		conn.close();

	}

	private static List<FeedSource> ParseFile(String result) throws JSONException {
		List<FeedSource> feedSource = new ArrayList<FeedSource>();

		JSONObject object = new JSONObject(result);
		JSONArray jArray = object.getJSONArray(RSSFEED);

		for (int i = 0; i < jArray.length(); i++) {
			JSONObject item = (JSONObject) jArray.get(i);
			FeedSource fs = null;
			for (int j = 0; j < item.length(); j++) {
				fs = new FeedSource();
				fs.setCompanyName(item.getString(COMPANY_NAME));
				fs.setChannel(item.getString(CHANNEL));
				fs.setLink(item.getString(LINK));
			}
			feedSource.add(fs);
		}

		return feedSource;
	}

	private static String readFile(String filePath) {
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			StringBuilder sb = new StringBuilder();

			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}

			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static String getLastUpdateDate(String source_name, String channel) throws SQLException {
		String date = "";

		// DriverMnager is an old way
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(FEED_SOURCE, DB_USER_NAME, DB_PASSWORD);
			stmt = (Statement) conn.createStatement();
			String sql = "SELECT last_update_time FROM source.feed_source where source_name=" + "'" + source_name + "'"
					+ " and channel=" + "'" + channel + "'";

			rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				date = rs.getString(1);
				// System.out.println("date =" + date);
			}

			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}

		// DataSource is a new better way
		// MysqlDataSource

		return date;
	}

	private static void updateFeedSourceDB(String companyName, String channel, String update_date) throws SQLException {
		Connection conn = null;
		String sql;

		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(FEED_SOURCE, DB_USER_NAME, DB_PASSWORD);
			sql = "update source.feed_source set last_update_time = ? where source_name = ? and channel = ?";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, update_date);
			stmt.setString(2, companyName);
			stmt.setString(3, channel);
			stmt.executeUpdate();

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	private static void insertItemDB(String sourceName, String channel, List<FeedItem> items)
			throws SQLException, ClassNotFoundException {
		Connection conn = null;
		String sql = "";

		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(FEED_SOURCE, DB_USER_NAME, DB_PASSWORD);
			sql = "insert into message (message_id, source_name, channel, title, creator, link, description, contents, timestamp, number_of_images, pub_date, day_created)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?)";

			for (FeedItem item : items) {
				// System.out.println(item);
				String title = item.getTitle().toString();
				// System.out.println("title = " + title);
				if (item.getTitle().isEmpty() || sourceName.isEmpty() || channel.isEmpty()) {
					System.out.println(
							"source_name = " + sourceName + ", channel = " + channel + ", title = " + item.getTitle());
				} else {
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setLong(1, item.getMessage_id());
					stmt.setString(2, sourceName);
					stmt.setString(3, channel);
					stmt.setString(4, item.getTitle());
					stmt.setString(5, item.getCreator());
					stmt.setString(6, item.getLink());
					stmt.setString(7, item.getDescription());
					stmt.setString(8, item.getContents());
					stmt.setString(9, item.getTimestamp());
					stmt.setInt(10, item.getNumberOfImages());
					stmt.setString(11, item.getPubDate());
					stmt.setString(12, item.getDayCreated());
					stmt.execute();
				}
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insert been rejected due to trying insert duplicate key to feed_item table!!!");
		} finally {
			conn.close();
		}

	}
	

	private static void writeTOJasonFile(String source_name, String channel, List<FeedItem> items) throws IOException, JSONException {
		// TODO Auto-generated method stub
		 
		JSONObject obj = new JSONObject();
		obj.put(SOURCE, source_name);
		obj.put(CHANNEL, channel);
		
		for(int i = 0; i<items.size(); i++){
			FeedItem item = items.get(i);
			JSONArray jArray = new JSONArray();
			
			JSONObject title = new JSONObject();
			title.put(TITLE, item.getTitle());
			
			JSONArray imageArray = new JSONArray();
			for(int j=0; j<item.getImageList().size(); j++){
				Image image = item.getImageList().get(j);
				imageArray.put(image.getImage_id());						
			}
			
			jArray.put(title);
			jArray.put(imageArray);
			
			obj.put(ITEM, jArray);
		}
		
		FileWriter writer = new FileWriter(source_name + ".txt");
		writer.write(obj.toString());
		
		writer.flush();
		writer.close();
		
	}

	private static void insertImageDB(String source_name, String channel2, List<Image> images)
			throws ClassNotFoundException, SQLException {
		Connection conn = null;
		String sql = "";

		Class.forName(JDBC_DRIVER);
		conn = (Connection) DriverManager.getConnection(FEED_SOURCE, DB_USER_NAME, DB_PASSWORD);
		sql = "insert into image (image_id, image_type, image_name, image_file_name, width, height)"
				+ " values (?,?,?,?,?,?)";

		for (Image image : images) {
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, image.getImage_id());
			stmt.setString(2, image.getImage_type());
			stmt.setString(3, image.getImage_name());
			stmt.setString(4, image.getImage_file_name());
			stmt.setInt(5, image.getWidth());
			stmt.setInt(6, image.getHeight());
			stmt.execute();
		}
	}

}
