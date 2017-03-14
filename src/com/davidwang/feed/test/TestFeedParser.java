package com.davidwang.feed.test;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.davidwang.feed.model.Feed;
import com.davidwang.feed.model.FeedItem;
import com.davidwang.feed.model.FeedSource;
import com.davidwang.feed.model.Image;
import com.davidwang.feed.read.RSSFeedParserYahoo;
import com.davidwang.feed.utils.Const;
import com.davidwang.feed.utils.Utils;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.eclipsesource.json.WriterConfig;
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

	static Connection conn;

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, ParseException {

		List<FeedSource> feedSource = new ArrayList<FeedSource>();

		feedSource = ParseFile(readFile(Const.FILE_PATH));

		for (FeedSource fs : feedSource) {
			if (!fs.getLink().isEmpty()) {

				String source_name = fs.getCompanyName().trim();
				String channel = fs.getChannel().trim();
				String link = fs.getLink();
				RSSFeedParserYahoo parser = new RSSFeedParserYahoo();

				if (source_name.isEmpty() || channel.isEmpty()) {
					System.out.println("source name and channel should not be empty!");
					break;
				}

				// First connect to DB
				connectToDB();

				// for the first time that source and channel are not in source
				// table.
				// insert to source table and parse the feed, incert to message
				// table and image table.
				if (!isInSourceTable(source_name, channel)) {
					insertToSourceTable(source_name, channel);

					// parse the feed
					Feed feed = parser.readFeed(source_name, channel, link, "", true);
					// System.out.println(feed);

					// update feed source table
					updateFeedSourceDB(source_name, channel, feed.getLastBuildDate(), feed.getPreviousLastUpdate());

					// if message has image, write to image DB
					for (FeedItem item : feed.getItems()) {

						String contentsFileURL = writeToHTML(item);
						item.setLink(contentsFileURL);

						if (!contentsFileURL.isEmpty() && contentsFileURL != null) {
							if (item.isHas_image()) {
								parser.saveImgToFile(item.getImage().getFullFIleName(), item.getImage().getLink());

								BufferedImage bimg = ImageIO.read(new File(item.getImage().getFullFIleName()));
								item.getImage().setWidth(bimg.getWidth());
								item.getImage().setHeight(bimg.getHeight());
								item.setLink(contentsFileURL);

							}
							// insert Item
							insertItemDB(source_name, channel, contentsFileURL, item);
						}
					}

					// write to Jason file
					writeTOJasonFile(source_name, channel, feed.getItems());

				} else { // not the first time
							// get last update time for each source/channel
					String last_update_from_table = getLastUpdateDate(source_name, channel);
					System.out.println("Data_Base_date  =" + last_update_from_table);

					String lastBuildDate = parser.getLastUpdateTime(link);
					System.out.println("last_build_date =" + lastBuildDate);

					String previoud_last_update = getPreviousLastUpdate(source_name, channel);

					// there is new update in the feed
					if (!last_update_from_table.equalsIgnoreCase(lastBuildDate)) {

						// List<String > titleList =
						// getAllCurrentTitles(source_name,channel);

						Feed feed = parser.readFeed(source_name, channel, previoud_last_update, link, false);

						updateFeedSourceDB(source_name, channel, lastBuildDate, feed.getPreviousLastUpdate());

						// if message has image, write to image DB
						for (FeedItem item : feed.getItems()) {

							String contentsFileURL = writeToHTML(item);

							if (!contentsFileURL.isEmpty() && contentsFileURL != null) {

								if (item.isHas_image()) {
									parser.saveImgToFile(item.getImage().getFullFIleName(), item.getImage().getLink());
									BufferedImage bimg = ImageIO.read(new File(item.getImage().getFullFIleName()));
									item.getImage().setWidth(bimg.getWidth());
									item.getImage().setHeight(bimg.getHeight());
								}
								// insert Item
								insertItemDB(source_name, channel, contentsFileURL, item);
							}
						}
					} else {
						System.out.println("feed message is up to date, no DB update required for " + source_name
								+ " and " + channel);
					}
				}
			}
		}

		// after all DB task done, close the db connection
		if (!conn.isClosed()) {
			conn.close();
		}

	}

	private static String writeToHTML(FeedItem item) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub

		String timestamp = item.getTimestamp();
		String fileName = Const.SAVED_FOLDER + item.getDayCreated() + Const.SLASH + timestamp + Const.DOT + Const.HTML;
		String fileNameHttp = Const.ANDROID_CONNECT + item.getDayCreated() + Const.SLASH + timestamp + Const.DOT
				+ Const.HTML;

		PrintWriter writer = new PrintWriter(fileName, Const.UTF8);
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset=UTF-8>");

		writer.print(item.getContents());
		writer.println("</head>");
		writer.println("</html>");

		writer.close();

		return fileNameHttp;
	}

	private static void connectToDB() throws ClassNotFoundException, SQLException {
		Class.forName(Const.JDBC_DRIVER);
		conn = (Connection) DriverManager.getConnection(Const.FEED_SOURCE, Const.DB_USER_NAME, Const.DB_PASSWORD);
	}

	private static boolean isInSourceTable(String source_name, String channel)
			throws ClassNotFoundException, SQLException {

		Statement stmt = (Statement) conn.createStatement();
		String sql = "SELECT * FROM source.feed_source where source_name=" + "'" + source_name + "'" + " and channel="
				+ "'" + channel + "'";
		ResultSet rs = (ResultSet) stmt.executeQuery(sql);
		if (!rs.next()) {
			return false;
		} else {
			return true;
		}
	}

	private static void insertToSourceTable(String source_name, String channel)
			throws ClassNotFoundException, SQLException {

		String sql = "insert into source.feed_source (source_name, channel, created_time) " + " values (?,?,?)";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
		ps.setString(1, source_name);
		ps.setString(2, channel);
		ps.setString(3, Utils.formatTime(LocalDateTime.now()));
		ps.execute();

	}

	private static List<FeedSource> ParseFile(String result) {
		List<FeedSource> feedSource = new ArrayList<FeedSource>();

		JsonArray array = (JsonArray) Json.parse(result).asObject().get(Const.RSSFEED).asArray();
		for (JsonValue value : array) {
			FeedSource feed = new FeedSource();
			feed.setCompanyName(value.asObject().getString(Const.COMPANY_NAME, ""));
			feed.setChannel(value.asObject().getString(Const.CHANNEL, ""));
			feed.setLink(value.asObject().getString(Const.LINK, ""));
			feedSource.add(feed);
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
		Statement stmt = null;
		ResultSet rs = null;
		try {

			stmt = (Statement) conn.createStatement();
			String sql = "SELECT last_update_time FROM source.feed_source where source_name=" + "'" + source_name + "'"
					+ " and channel=" + "'" + channel + "'";

			rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				date = rs.getString(1);
				// System.out.println("date =" + date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();

		}

		// DataSource is a new better way
		// MysqlDataSource

		return date;
	}

	private static String getPreviousLastUpdate(String source_name, String channel) throws SQLException {
		String date = "";

		// DriverMnager is an old way
		Statement stmt = null;
		ResultSet rs = null;
		try {

			stmt = (Statement) conn.createStatement();
			String sql = "SELECT previous_last_update FROM source.feed_source where source_name=" + "'" + source_name
					+ "'" + " and channel=" + "'" + channel + "'";

			rs = (ResultSet) stmt.executeQuery(sql);
			while (rs.next()) {
				date = rs.getString(1);
				// System.out.println("date =" + date);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stmt.close();
		}

		// DataSource is a new better way
		// MysqlDataSource

		return date;

	}

	private static void updateFeedSourceDB(String companyName, String channel, String update_date,
			String previoudLastUpdate) throws SQLException {
		String sql;

		try {
			sql = "update source.feed_source set last_update_time = ?, previous_last_update = ? where source_name = ? and channel = ?";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, update_date);
			stmt.setString(2, previoudLastUpdate);
			stmt.setString(3, companyName);
			stmt.setString(4, channel);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	private static void insertItemDB(String sourceName, String channel, String contentsFileURL, FeedItem item)
			throws SQLException, ClassNotFoundException {
		String sql = "";

		try {
			sql = "insert into message (source_name, channel, title, creator, link, description, contents, timestamp, has_image, pub_date, day_created, image_type, image_url, image_width, image_height)"
					+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			// System.out.println(item);
			// System.out.println("title = " + title);
			if (item.getTitle().isEmpty() || sourceName.isEmpty() || channel.isEmpty()) {
				System.out.println(
						"source_name = " + sourceName + ", channel = " + channel + ", title = " + item.getTitle());
			} else {
				PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);

				stmt.setString(1, sourceName);
				stmt.setString(2, channel);
				stmt.setString(3, item.getTitle());
				stmt.setString(4, sourceName);
				stmt.setString(5, contentsFileURL);
				stmt.setString(6, item.getTitle());
				stmt.setString(7, item.getContents());
				stmt.setString(8, item.getTimestamp());
				stmt.setBoolean(9, item.isHas_image());
				stmt.setString(10, item.getPubDate());
				stmt.setString(11, item.getDayCreated());

				String image_type = "";
				String image_url = "";
				int image_width = 0;
				int image_height = 0;

				if (item.isHas_image()) {
					image_type = item.getImage().getImage_type();
					image_url = item.getImage().getImage_url();
					image_width = item.getImage().getWidth();
					image_height = item.getImage().getHeight();
				}

				stmt.setString(12, image_type);
				stmt.setString(13, image_url);
				stmt.setInt(14, image_width);
				stmt.setInt(15, image_height);

				stmt.execute();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("insert been rejected due to trying insert duplicate key to feed_item table!!!");
		} finally {

		}

	}

	private static void writeTOJasonFile(String source_name, String channel, List<FeedItem> items) throws IOException {
		// TODO Auto-generated method stub

		FileWriter writer = new FileWriter(source_name + Utils.formatTime(LocalDateTime.now()) + ".json");

		JsonObject obj;
		JsonArray array = new JsonArray();
		String image_url = "";

		for (int i = 0; i < items.size(); i++) {
			FeedItem item = items.get(i);

			JsonObject itemMsg = new JsonObject();

			if (item.isHas_image()) {
				image_url = item.getImage().getImage_url();
			}

			itemMsg = Json.object().add(Const.SOURCE_NAME, source_name).add(Const.CHANNEL, channel)
					.add(Const.TITLE, item.getTitle()).add(Const.TIME, item.getPubDate()).add(Const.LINK, image_url)
					.add(Const.CONTENTS_URL, item.getLink());

			array.add(itemMsg);

		}
		obj = new JsonObject().add(Const.ITEM, array);

		obj.writeTo(writer, WriterConfig.PRETTY_PRINT);

		writer.flush();
		writer.close();

	}

	private static void insertImageDB(String source_name, String channel2, Image image)
			throws ClassNotFoundException, SQLException {
		String sql = "";

		sql = "insert into image (image_type, image_name, image_url,  width, height)" + " values (?,?,?,?,?)";

		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);

		stmt.setString(1, image.getImage_type());
		stmt.setString(2, image.getImage_name());
		stmt.setString(3, image.getImage_url());
		stmt.setInt(4, image.getWidth());
		stmt.setInt(5, image.getHeight());
		stmt.execute();

	}

}
