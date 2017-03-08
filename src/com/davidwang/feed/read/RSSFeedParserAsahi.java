package com.davidwang.feed.read;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.davidwang.feed.model.Feed;
import com.davidwang.feed.model.FeedItem;
import com.davidwang.feed.model.Image;

public class RSSFeedParserAsahi {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String COPYRIGHT = "copyright";
	static final String RIGHTS = "rights";
	static final String LINK = "link";
	static final String AUTHOR = "suthor";
	static final String ITEMS = "items";
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
	static final String FOX_NEWS = "FOX News";
	static final String DC = "dc";
	static final String SUBJECT = "subject";
	static final String DATE = "date";
	static final String CREATOR = "creator";
	static final String JPG = "jpg";
	static final String IMG = "img";
	static final String IMAGE = "image";
	static final String ITEMPROP = "itemprop";
	static final String PROPERTY = "property";
	static final String ARTICLE_BODY = "ArticleBody";
	static final String ARTICLE_TEXT = "ArticleText";
	static final String ARTICLE_TITLE = "ArticleTitle";
	static final String ID = "id";
	static final String PAGE = "page";
	static final String CLASS = "class";

	static final String PUBLISHER = "publisher";
	static final String SYN = "syn";
	static final String UPDATE_PERIOD = "updatePeriod";
	static final String UPDATE_FREQUENCY = "updateFrequency";
	static final String UPDATE_BASE = "updateBase";
	static final String MAIN_INNER = "MainInner";
	static final String PR = "PR";
	static final String CNET = "CNET";
	static final String ZDNET = "ZDNet";
	static final String LEFT_BRAKET = "<";
	static final String TETSUDO = "鉄道コム";
	static final String SUB = "Sub";
	static final String LAST_UPDATED = "LastUpdated";
	static final String P = "p";
	static final String A = "a";
	static final String SRC = "src";
	static final String HTTP = "http:";
	static final String UNDERSCORE = "_";
	static final String DOT = ".";
	static final String rdf = "rdf";
	static final String RDF = "RDF";
	static final String BLANK = "blank";
	static final String IMAGESMOD = "ImagesMod";
	static final String DATE_PATTERN_YMD = "yyyy-MM-dd";
	static final String ROUNDED = "rounded";
	static final String PNG = "png";

	final URL url;

	public RSSFeedParserAsahi(String sURL) throws MalformedURLException {
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
		String publisher = "";
		String updatePeriod = "";
		String updateBase = "";
		int updateFrequency = 0;
		String date = "";
		List<FeedItem> items = null;

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
							items = new ArrayList<FeedItem>();
							event = eventReader.nextEvent();
							break;
						case TITLE:
							title = getData(event, eventReader);
							break;
						case LINK:
							link = getData(event, eventReader);
							break;
						case DESCRIPTION:
							description = getData(event, eventReader);
							break;
						case ITEM:
							if (isFeedHeader) {
								isFeedHeader = false;
								feed.setTitle(title);
								feed.setLink(link);
								feed.setDescription(description);
								feed.setLanguage(language);
								feed.setCopyright(copyright);
								feed.setCreator(creator);
								feed.setLastBuildDate(date);
								feed.setPublisher(publisher);
								feed.setUpdatePeriod(updatePeriod);
								feed.setUpdateFrequency(updateFrequency);
								feed.setUpdateBase(updateBase);
							}
							event = eventReader.nextEvent();
							break;

						}
					} else if (prefix.equalsIgnoreCase(DC)) {
						switch (localPart) {
						case LANGUAGE:
							language = getData(event, eventReader);
							break;
						case RIGHTS:
							copyright = getData(event, eventReader);
							break;
						case CREATOR:
							creator = getData(event, eventReader);
							break;
						case DATE:
							date = getData(event, eventReader);
							break;
						case PUBLISHER:
							publisher = getData(event, eventReader);
							break;

						}
					} else if (prefix.equalsIgnoreCase(SYN)) {
						switch (localPart) {
						case UPDATE_PERIOD:
							updatePeriod = getData(event, eventReader);
							break;
						case UPDATE_FREQUENCY:
							updateFrequency = tryParse(getData(event, eventReader));
							break;
						case UPDATE_BASE:
							updateBase = getData(event, eventReader);
							break;
						}
					}

				} else if (event.isEndElement()) {
					String prefix = event.asEndElement().getName().getPrefix();
					String localPart = event.asEndElement().getName().getLocalPart();

					if (prefix.isEmpty()) {
						if (localPart == (ITEM)) {
							System.out.println("title = " + title);
							FeedItem item = new FeedItem();
							if (title.isEmpty() || link.isEmpty() || title.contains(PR) || title.contains(CNET)
									|| title.contains(ZDNET) || title.contains(TETSUDO)) {
								// Do nothing
							} else {
								String dayCreated = formatTime(LocalDateTime.now());
								item = getItems(link, dayCreated);
								item.setTitle(title);
								item.setLink(link);
								// for Asahi that it does not have creator and
								// description on item field
								item.setCreator(source_name);
								item.setDescription(title);
								item.setPubDate(date);
								item.setDayCreated(dayCreated);
								items.add(item);
							}
							event = eventReader.nextEvent();
						}
					} else if (prefix == rdf) {
						if (localPart == RDF) {
							feed.setItems(items);
						}
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

	private String getData(XMLEvent event, XMLEventReader eventReader)
			throws XMLStreamException, UnsupportedEncodingException {
		String result = "";

		event = eventReader.nextEvent();
		if (event.isCharacters()) {
			String data = event.asCharacters().getData();
			byte ptext[] = data.getBytes("UTF-8");
			result = new String(ptext, "UTF-8");
			Document doc = Jsoup.parse(result);
			if (doc.isBlock()) {
				result = doc.text();
			}
			// System.out.println("result = " + result);
		}
		return result;

	}

	private FeedItem getItems(String link, String dayCreated) throws IOException, ParseException {
		FeedItem item = new FeedItem();
		URL url = new URL(link);

		long cTime = System.currentTimeMillis();
		item.setMessage_id(cTime);
		item.setTimestamp(String.valueOf(cTime));

		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		// get the article body
		Document doc = Jsoup.parse(sb.toString());

		Elements bodies = doc.getElementsByClass(IMAGESMOD);
		if (bodies != null && bodies.size() != 0) {

			item.setHas_image(true);
			// get all images
			Image image = new Image();
			Document doc2 = Jsoup.parse(bodies.toString());
			Element element = doc2.select(IMG).first();

			String imageURL = element.attr(SRC);
			if (!imageURL.contains(HTTP)) {
				imageURL = HTTP + imageURL;
			}
			image.setLink(imageURL);
			if (imageURL.contains(JPG)) {
				image.setImage_type(JPG);
			}

			// get the image name from system time
			String image_id = IMAGE + UNDERSCORE + String.valueOf(cTime);
			String fileName = image_id + DOT + JPG;

			String fileDir = IMAGE + "/" + dayCreated;
			File dir = new File(fileDir);
			dir.mkdir();

			String fullFIleName = fileDir + "/" + fileName;
			image.setImage_id(image_id);
			image.setImage_name(fileName);
			image.setImage_file_name(fullFIleName);

			// save the image to local file
			if (imageURL != null && !imageURL.isEmpty()) {
				// retrieve the image from URL and save it to local
				saveImgToFile(fullFIleName, imageURL);

				// get the width and height of the image
				BufferedImage bimg = ImageIO.read(new File(fullFIleName));
				image.setWidth(bimg.getWidth());
				image.setHeight(bimg.getHeight());

			}
			item.setImage(image);
		}

		// get article contents
		Elements articles = doc.getElementsByClass(ARTICLE_BODY);
//		Document doc3 = Jsoup.parse(articles.toString());
//		Elements articleTexts = doc3.getElementsByTag(P);
//		String contents = articleTexts.text();
		item.setContents(articles.html());

		br.close();
		is.close();

		return item;
	}

	private String formatTime(LocalDateTime now) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_PATTERN_YMD);
		return now.format(format);
	}

	private void saveImgToFile(String fileName, String link) throws IOException, ParseException {

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

	private void roundAnImage(String fullFIleName, String image_id, String fileDir) throws IOException {
		BufferedImage bi = ImageIO.read(new File(fullFIleName));
		int width = bi.getWidth();
		int height = bi.getHeight();

		BufferedImage bi2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		// BufferedImage background = applyShadow(bi2, 10, Color.gray, 1f);

		// create Graphics2D object
		Graphics2D g2 = bi2.createGraphics();

		// use rendering hints to smooth the edge
		RenderingHints rHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		rHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		rHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		rHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHints(rHints);

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

		// now clip the image
		g2.setClip(new RoundRectangle2D.Double(0, 0, width, height, width / 20, height / 20));

		// now draw the image
		// int x = (width - background.getWidth())/2;
		// int y = (height - background.getHeight())/2;
		// g2.drawImage(background, x, y, null);
		g2.drawImage(bi, 0, 0, null);

		// dispose it after done
		g2.dispose();

		// write to a new image file
		ImageIO.write(bi2, "PNG", new File(fileDir + "/" + image_id + UNDERSCORE + ROUNDED + DOT + PNG));

	}

	private BufferedImage applyShadow(BufferedImage imgSource, int size, Color color, float alpha) {
		BufferedImage result = createCompatibleImage(imgSource, imgSource.getWidth() + (size * 2),
				imgSource.getHeight() + (size * 2));
		Graphics2D g2d = result.createGraphics();
		g2d.drawImage(generateShadow(imgSource, size, color, alpha), size, size, null);
		g2d.drawImage(imgSource, 0, 0, null);
		g2d.dispose();

		return result;
	}

	public BufferedImage createCompatibleImage(int width, int height) {
		return createCompatibleImage(width, height, Transparency.TRANSLUCENT);
	}

	public BufferedImage createCompatibleImage(int width, int height, int transparency) {
		BufferedImage image = getGraphicsConfiguration().createCompatibleImage(width, height, transparency);
		image.coerceData(true);
		return image;
	}

	public BufferedImage createCompatibleImage(BufferedImage image) {
		return createCompatibleImage(image, image.getWidth(), image.getHeight());
	}

	private BufferedImage createCompatibleImage(BufferedImage image, int width, int height) {
		return getGraphicsConfiguration().createCompatibleImage(width, height, image.getTransparency());
	}

	private GraphicsConfiguration getGraphicsConfiguration() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
	}

	private BufferedImage generateShadow(BufferedImage imgSource, int size, Color color, float alpha) {
		int imgWidth = imgSource.getWidth() + (size * 2);
		int imgHeight = imgSource.getHeight() + (size * 2);

		BufferedImage imgMask = createCompatibleImage(imgWidth, imgHeight);
		Graphics2D g2 = imgMask.createGraphics();
		applyQualityRenderingHints(g2);

		int x = Math.round((imgWidth - imgSource.getWidth()) / 2f);
		int y = Math.round((imgHeight - imgSource.getHeight()) / 2f);
		g2.drawImage(imgSource, x, y, null);
		g2.dispose();

		// ---- Blur here ---

		BufferedImage imgGlow = generateBlur(imgMask, (size * 2), color, alpha);

		return imgGlow;
	}

	public void applyQualityRenderingHints(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	}

	public BufferedImage generateBlur(BufferedImage imgSource, int size, Color color, float alpha) {
		BufferedImageOp filter = new AffineTransformOp(new AffineTransform(), AffineTransformOp.TYPE_BICUBIC);

		int imgWidth = imgSource.getWidth();
		int imgHeight = imgSource.getHeight();

		BufferedImage imgBlur = createCompatibleImage(imgWidth, imgHeight);
		Graphics2D g2 = imgBlur.createGraphics();
		applyQualityRenderingHints(g2);

		g2.drawImage(imgSource, 0, 0, null);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_IN, alpha));
		g2.setColor(color);

		g2.fillRect(0, 0, imgSource.getWidth(), imgSource.getHeight());
		g2.dispose();

		imgBlur = filter.filter(imgBlur, null);

		return imgBlur;
	}
}
