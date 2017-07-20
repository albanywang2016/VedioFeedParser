package com.davidwang.feed.parse;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.davidwang.feed.model.Feed;
import com.davidwang.feed.model.FeedItem;
import com.davidwang.feed.model.FeedSource;
import com.davidwang.feed.read.RSSFeedParser;
import com.davidwang.feed.read.VedioFeedParser;
import com.davidwang.feed.utils.Const;
import com.davidwang.feed.utils.Utils;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonValue;
import com.mysql.jdbc.Connection;

public class ParseFeed {

	static Connection conn;

	public static void main(String[] args) {

		String[] allFeeds = { Const.JPVEDIO };

		for (String feed_path : allFeeds) {

			List<FeedSource> feedSource = new ArrayList<FeedSource>();

			feedSource = ParseFile(readFile(feed_path));

			for (FeedSource fs : feedSource) {
				if (!fs.getLink().isEmpty()) {

					String source_name = fs.getCompanyName().trim();
					String channel = fs.getChannel().trim();
					String link = fs.getLink();
					VedioFeedParser parser = new VedioFeedParser();

					if (source_name.isEmpty() || channel.isEmpty()) {
						System.out.println("source name and channel should not be empty!");
						break;
					}

					// for the first time that source and channel are not in source table.
					// insert to source table and parse the feed, insert to message table and image table.
					try {
						if (!isInSourceTable(source_name, channel)) {
							insertToSourceTable(source_name, channel);

							// parse the feed
							Feed feed = parser.readFeed(source_name, channel, link, "", true);
							// System.out.println(feed);

							// update feed source table
							updateFeedSourceDB(source_name, channel, feed.getLastBuildDate(),
									feed.getPreviousLastUpdate());

							// if message has image, write to image DB
							for (FeedItem item : feed.getItems()) {

								// String contentsFileURL = writeToHTML(item);
								// item.setLink(contentsFileURL);

								if (item.isHas_image()) {
									// try {
									// parser.saveImgToFile(item.getImage().getFullFIleName(),
									// item.getImage().getLink());
									// } catch (Exception e) {
									// e.printStackTrace();
									// }
									// BufferedImage bimg = ImageIO.read(new
									// File(item.getImage().getFullFIleName()));
									// item.getImage().setWidth(bimg.getWidth());
									// item.getImage().setHeight(bimg.getHeight());
									URL imageURL = new URL(item.getImage().getLink());
									System.out.println("imageurl = " + imageURL);
									BufferedImage img = ImageIO.read(imageURL);
									item.getImage().setWidth(img.getWidth());
									item.getImage().setHeight(img.getHeight());
									InsertMessageWithImage(source_name, channel, item);

								} else {
									InsertMessageNoImage(source_name, channel, item);
								}
								// insert Item
								// insertItemDB(source_name, channel,
								// contentsFileURL, item);

							}

							// write to Jason file
							// writeTOJasonFile(source_name, channel,
							// feed.getItems());

						} else { // not the first time
									// get last update time for each
									// source/channel
							String last_update_from_table = getLastUpdateDate(source_name, channel);
							System.out.println("Data_Base_date  =" + last_update_from_table);
							if (last_update_from_table == null) {
								last_update_from_table = "";
							}

							String lastBuildDate = parser.getLastUpdateTime(link);
							System.out.println("last_build_date =" + lastBuildDate);
							if (lastBuildDate == null) {
								lastBuildDate = "";
							}

							System.out.println("source = " + source_name + ", channel = " + channel);

							String previoud_last_update = getPreviousLastUpdate(source_name, channel);

							// there is new update in the feed
							if (!last_update_from_table.equalsIgnoreCase(lastBuildDate)) {

								// List<String > titleList =
								// getAllCurrentTitles(source_name,channel);

								Feed feed = parser.readFeed(source_name, channel, link, previoud_last_update, false);

								updateFeedSourceDB(source_name, channel, lastBuildDate, feed.getPreviousLastUpdate());

								// if message has image, write to image DB
								for (FeedItem item : feed.getItems()) {

									// String contentsFileURL =
									// writeToHTML(item);

									if (item.isHas_image()) {
										// parser.saveImgToFile(item.getImage().getFullFIleName(),
										// item.getImage().getLink());
										// BufferedImage bimg = ImageIO.read(new
										// File(item.getImage().getFullFIleName()));
										// item.getImage().setWidth(bimg.getWidth());
										// item.getImage().setHeight(bimg.getHeight());
										URL imageURL = new URL(item.getImage().getLink());
										System.out.println("imageurl = " + imageURL);
										BufferedImage img = ImageIO.read(imageURL);
										item.getImage().setWidth(img.getWidth());
										item.getImage().setHeight(img.getHeight());

										InsertMessageWithImage(source_name, channel, item);
									} else {
										InsertMessageNoImage(source_name, channel, item);
									}
									// insert Item
									// insertItemDB(source_name, channel,
									// contentsFileURL, item);
								}
							} else {
								System.out.println("feed message is up to date, no DB update required for "
										+ source_name + " and " + channel);
							}
						}
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	private static String PostToServer(URL url, Map<String, Object> params) throws IOException {
		StringBuilder builder = new StringBuilder();

		StringBuilder postData = new StringBuilder();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			if (postData.length() != 0)
				postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
		}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

		String line;
		while ((line = in.readLine()) != null) {
			builder.append(line);
		}

		System.out.println(builder.toString());

		return builder.toString();

	}

	private static boolean isInSourceTable(String source_name, String channel) throws IOException {

		URL url = new URL(Const.IS_IN_SOURCE);

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("source_name", source_name);
		params.put("channel", channel);

		String results = PostToServer(url, params);

		if (results.contains("Error")) {
			return false;
		} else {
			return true;
		}

	}

	private static void insertToSourceTable(String source_name, String channel)
			throws ClassNotFoundException, SQLException, IOException {

		URL url = new URL(Const.INSERT_TO_SOURCE);

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("source_name", source_name);
		params.put("channel", channel);
		params.put("created_time", (String) Utils.formatTime(LocalDateTime.now()));

		String results = PostToServer(url, params);

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
			URL path = ParseFeed.class.getClassLoader().getResource(filePath);

			@SuppressWarnings("resource")
			// BufferedReader br = new BufferedReader(new FileReader(filePath));
			BufferedReader br = new BufferedReader(new InputStreamReader(path.openStream(), StandardCharsets.UTF_8));
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

	private static String getLastUpdateDate(String source_name, String channel) throws SQLException, IOException {

		URL url = new URL(Const.GET_LAST_UPDATE_TIME_PHP);

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("source_name", source_name);
		params.put("channel", channel);

		String results = PostToServer(url, params);

		return results;
	}

	private static String getPreviousLastUpdate(String source_name, String channel) throws SQLException, IOException {
		URL url = new URL(Const.GET_PREVIOUS_LAST_UPDATE);

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("source_name", source_name);
		params.put("channel", channel);

		String results = PostToServer(url, params);

		return results;

	}

	private static void updateFeedSourceDB(String source_name, String channel, String last_update_time,
			String previous_last_update) throws SQLException, IOException {

		URL url = new URL(Const.UPDATE_SOURCE);

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("source_name", source_name);
		params.put("channel", channel);
		params.put("last_update_time", last_update_time);
		params.put("previous_last_update", previous_last_update);

		String results = PostToServer(url, params);

	}

	private static void InsertMessageNoImage(String source_name, String channel, FeedItem item) throws IOException {

		URL url = new URL(Const.INSERT_TO_MESSAGE_NO_IMAGE);

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("source_name", source_name);
		params.put("channel", channel);
		params.put("title", item.getTitle());
		params.put("link", item.getLink());
		params.put("content", item.getContents());
		params.put("pub_date", item.getPubDate());
		params.put("day_created", item.getDayCreated());

		System.out.println("contents = " + item.getContents());
		String results = PostToServer(url, params);
	}

	private static void InsertMessageWithImage(String source_name, String channel, FeedItem item) throws IOException {

		URL url = new URL(Const.INSERT_TO_MESSAGE_WITH_IMAGE);

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("source_name", source_name);
		params.put("channel", channel);
		params.put("title", item.getTitle());
		params.put("link", item.getLink());
		params.put("content", item.getContents());
		params.put("has_image", 1);
		params.put("pub_date", item.getPubDate());
		params.put("day_created", item.getDayCreated());
		params.put("image_type", item.getImage().getImage_type());
		params.put("image_url", item.getImage().getLink());
		params.put("image_width", (int) item.getImage().getWidth());
		params.put("image_height", (int) item.getImage().getHeight());

		System.out.println("contents = " + item.getContents());
		// System.out.println("image_width" + item.getImage().getWidth());
		// System.out.println("image_height" + item.getImage().getHeight());

		String results = PostToServer(url, params);

	}

}
