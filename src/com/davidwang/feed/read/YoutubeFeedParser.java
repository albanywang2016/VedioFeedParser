package com.davidwang.feed.read;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.davidwang.feed.model.Feed;
import com.davidwang.feed.model.FeedItem;
import com.davidwang.feed.model.Image;
import com.davidwang.feed.utils.Const;

public class YoutubeFeedParser {

	private boolean pubDateFound = false;


	public Feed readFeed(String source_name, String channel, String linkURL, String previousLastUpdate,
			String last_item) throws IOException, ParseException {
		InputStream in = null;
		List<FeedItem> items = null;
		Feed feed = null;
		URL url = null;

		try {
			feed = new Feed();
			items = new ArrayList<FeedItem>();
			url = new URL(linkURL);
			in = url.openStream();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			Document doc = Jsoup.parse(sb.toString());
			//lastBuildDate = doc.select("div[class=period]").text();
			//feed.setLastBuildDate(lastBuildDate);
			//feed.setPreviousLastUpdate(lastBuildDate);
			
			Elements elements = doc.select("li[class=channels-content-item yt-shelf-grid-item]");
			
			for(Element element : elements){
				//System.out.println(element.toString());
				FeedItem item = new FeedItem();
				Image image = new Image();
				String itemLink = "";
				String title = "";
				String imageUrl = "";
				String lastBuildDate = "";
				
				itemLink = Const.YOUTUBE + element.select("h3[class=yt-lockup-title ]").select("a").first().attr("href");
				item.setLink(itemLink);
				
				title = element.select("h3[class=yt-lockup-title ]").select("a").attr("title");
				item.setTitle(title);
				
				lastBuildDate = element.select("ul[class=yt-lockup-meta-info]").text();
				lastBuildDate = lastBuildDate.substring(lastBuildDate.indexOf("views") + 5, lastBuildDate.length());
				if(lastBuildDate.contains("week") || lastBuildDate.contains("month") || lastBuildDate.contains("year")) break;
				
				String vedio_time = element.select("span[class=video-time]").text();
				
				item.setPubDate(vedio_time);
				
				item.setHas_image(true);
				imageUrl = element.select("span[class=yt-thumb-clip]").select("img").attr("src");
				image.setLink(imageUrl);
				
				image.setImage_type(Const.JPG);
				item.setImage(image);
				
				if(title.equalsIgnoreCase(last_item)){
					break;
				}
				
				items.add(item);
					
					if(!pubDateFound){
						feed.setLastBuildDate(lastBuildDate);
						feed.setPreviousLastUpdate(lastBuildDate);
						pubDateFound = true;
					}
					

			}

			feed.setItems(items);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return feed;
	}


	public void saveImgToFile(String fileName, String link) throws IOException, ParseException {

		URL url = new URL(link);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(fileName);
		byte[] b = new byte[2048];
		int length;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}


}
