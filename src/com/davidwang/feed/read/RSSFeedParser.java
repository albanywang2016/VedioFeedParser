package com.davidwang.feed.read;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.davidwang.feed.model.Feed;
import com.davidwang.feed.model.FeedItem;
import com.davidwang.feed.model.Thumbnail;

public class RSSFeedParser {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "suthor";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String GUID = "guid";
	static final String CATEGORY = "category";
	static final String MEDIA = "media";
	static final String THUMBNAIL = "thumbnail";
	static final String MEDIA_CONTENT = "content";
	static final String URL = "url";
	static final String CREDIT = "credit";
	static final String WIDTH = "width";
	static final String HEIGHT = "height";
	static final String MEDIUM = "medium";
	static final String LAST_BUILD_DATE = "lastBuildDate";
	static final String LANGUAGE = "language";
	static final String LA_TIMES = "Los Angeles Times";
	static final String BBC_NEWS = "BBC News";
	static final String FOX_NEWS= "FOX News";
	static final String DC = "dc";
	static final String CREATOR = "creator";
	static final String JPG = "jpg";
	static final String IMG = "img";
	static final String IMAGE = "image";
	static final String ITEMPROP = "itemprop";
	static final String PROPERTY = "property";
	static final String ARTICLE_BODY = "articleBody";
	static final String ID = "id";
	static final String PAGE = "page";
	static final String CLASS = "class";
	static final String ARTICLE_TEXT ="article-text";
	

	final URL url;

	public RSSFeedParser(String sURL) throws MalformedURLException {
		super();
		this.url = new URL(sURL);

	}

	public Feed readFeed(String source_name, String channel) throws IOException, ParseException {
		InputStream in;
		boolean isFeedHeader = true;
		String title = "";
		String link = "";
		String description = "";
		String guid = "";
		String pubDate = "";
		String medium = "";
		String lastBuildDate = "";
		String language = "";
		String copyright = "";
		String contents = "";
		String creator = source_name;
		
		List<Thumbnail> thumbnailList = new ArrayList<Thumbnail>();

		Feed feed = null;

		try {
			in = url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		inputFactory.setProperty("javax.xml.stream.isCoalescing", true);
		try {

			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					String prefix = event.asStartElement().getName().getPrefix();
					String localPart = event.asStartElement().getName().getLocalPart();
					// System.out.println("event asStartElement = " +
					// event.asStartElement().toString());

					if (prefix.isEmpty()) {
						// when prefix is empty
						switch (localPart) {
						case CHANNEL:
							feed = new Feed();
							event = eventReader.nextEvent();
							break;
						case ITEM:
							if (isFeedHeader) {
								isFeedHeader = false;
								feed.setTitle(title);
								feed.setLink(link);
								feed.setDescription(description);
								feed.setLanguage(language);
								feed.setCopyright(copyright);
								feed.setLastBuildDate(lastBuildDate);
							}
							event = eventReader.nextEvent();
							break;
						case TITLE:
							title = getData(event, eventReader);
							break;
						case LINK:
							link = getData(event,eventReader);
							break;
						case DESCRIPTION:
							description = getData(event,eventReader);
							break;
						case LANGUAGE:
							language = getData(event, eventReader);
							break;
						case COPYRIGHT:
							copyright = getData(event, eventReader);
							break;
						case LAST_BUILD_DATE:
							lastBuildDate = getData(event, eventReader);
							break;
						case PUB_DATE:
							pubDate = getData(event, eventReader);
							break;
						case AUTHOR:
							creator = getData(event, eventReader);
							break;
						}
					} else if(prefix.equalsIgnoreCase(DC)){
						switch(localPart){
						case CREATOR:
							creator = getData(event,eventReader);
							break;
						}
					} else if (prefix.equalsIgnoreCase(MEDIA)) {
						switch (localPart) {
						case THUMBNAIL:
							// thumbnailList =
							// getThumbnailList(event,eventReader);
							@SuppressWarnings("unchecked")
							Iterator<Attribute> iterator = event.asStartElement().getAttributes();
							String url = "";
							String credit = "";
							int width = 0;
							int height = 0;
							String value = "";
							String fileName = "";

							while (iterator.hasNext()) {
								Attribute attribute = (Attribute) iterator.next();
								value = attribute.getValue();
								if (value == null || value.equals("null")) {
									// System.out.println("name = " +
									// attribute.getName() + " value = " +
									// value);
									break;
								}
								switch (attribute.getName().toString()) {
								case URL:
									url = attribute.getValue();
									break;
								case MEDIUM:
									medium = getData(event, eventReader);
									break;
								case CREDIT:
									credit = attribute.getValue();
									break;
								case WIDTH:
									width = tryParse(value);
									break;
								case HEIGHT:
									height = tryParse(value);
									break;
								}
							}
							Thumbnail tn = new Thumbnail();
							tn.setURL(url);
							tn.setCredit(credit);
							// it is hardcode here for Los Angeles Times
							if (source_name.equalsIgnoreCase(LA_TIMES)) {
								height = 600;
								width = 441;
								medium = IMG;
							}
							tn.setHeight(height);
							tn.setWidth(width);
							if(medium.isEmpty()){
								if(url.contains(JPG)){
									medium = JPG;
								}
								if(url.contains(IMG)){
									medium = IMG;
								}
							}
							
							if(!url.isEmpty()){
								int index = 0;
								if(source_name.equalsIgnoreCase(LA_TIMES)){
									index = url.lastIndexOf('/');
									String str = url.substring(0,index);
									index = str.lastIndexOf('/');									
									fileName = IMAGE + url.substring(index, str.length()) + ".jpg";
									
								}else{
									index = url.lastIndexOf('/');									 
									fileName = url.substring(index, url.length());
									fileName = IMAGE + fileName; 
								}
								
								saveImgToFile(fileName, url);
							}
							tn.setFilename(fileName);
							tn.setMedium(medium);
							thumbnailList.add(tn);
							break;
						}
					}

				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						List<Thumbnail> tList = null;

						FeedItem item = new FeedItem();
						item.setTitle(title);
						item.setCreator(creator);
						item.setLink(link);
						item.setDescription(description);
						item.setGuid(guid);
						item.setPubDate(pubDate);
//						tList = new ArrayList<Thumbnail>(thumbnailList);
//						item.setThumbnailList(tList);

						if (!link.isEmpty()) {
							contents = getContents(source_name, link);
						}
						item.setContents(contents);
						feed.getItems().add(item);

						event = eventReader.nextEvent();
						thumbnailList.clear();
						continue;
					}
				}
			}

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return feed;
	}


	private Integer tryParse(String value) {
		try {
			return value.isEmpty() ? 0 : Integer.parseInt(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		
		event = eventReader.nextEvent();
		if(event.isCharacters()){
			result = event.asCharacters().getData();
		}		
		return result;
		
	}

	private String getDescription(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		StringBuffer sb = new StringBuffer();

		event = eventReader.nextEvent();
		if (event.isCharacters()) {
			String rawData = event.asCharacters().getData();
			Document doc = Jsoup.parse(rawData);
			Elements elements = doc.getAllElements();
			for (Element element : elements) {
				if (element.isBlock()) {
					sb.append(element.text());
				}
			}
		}
		return sb.toString();
	}


	public String getContents(String source_name, String link) {
		StringBuilder results = null;
		URL url = null;
		InputStream is = null;
		BufferedReader br = null;
		StringBuilder sb = null;
		String line = "";
		Document doc = null;
		Elements elements = null;
		
		try {
			url = new URL(link);
			results = new StringBuilder();

			is = url.openStream();
			br = new BufferedReader(new InputStreamReader(is));
			sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			doc = Jsoup.parse(sb.toString());
			try{
				switch(source_name){
				case LA_TIMES:
					elements = doc.getElementsByAttributeValue(ITEMPROP, ARTICLE_BODY);
					break;
				case BBC_NEWS:
					elements = doc.getElementsByAttributeValue(ID, PAGE);
					elements = doc.getElementsByAttributeValue(PROPERTY, ARTICLE_BODY);
					break;
				case FOX_NEWS:
					elements = doc.getElementsByAttributeValue(CLASS, ARTICLE_BODY);
					break;
				}
					
			}catch(Exception exce){
				
			}
			

		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return elements.toString();
	}


	private void saveImgToFile(String fileName, String link) throws IOException, ParseException {

			URL url = new URL(link);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(fileName);
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) != -1){
				os.write(b, 0, length);
			}
			
			is.close();
			os.close();
		
	}
	
}
