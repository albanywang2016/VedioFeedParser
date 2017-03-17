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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
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
import com.davidwang.feed.utils.Const;
import com.davidwang.feed.utils.Utils;

public class RSSFeedParserYahoo {

	private boolean pubDateFound = false;

	public String getLastUpdateTime(String link) throws UnsupportedEncodingException, MalformedURLException {
		String lastBuildDate = "";
		InputStream in;

		URL url = new URL(link);

		try {
			in = url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		inputFactory.setProperty(Const.IS_COALESCING, true);
		try {

			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					String prefix = event.asStartElement().getName().getPrefix();
					String localPart = event.asStartElement().getName().getLocalPart();

					if (prefix.isEmpty()) {
						switch (localPart) {
						case Const.LAST_BUILD_DATE:
							lastBuildDate = getData(event, eventReader);
							return lastBuildDate;
						}
					}
				}
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return lastBuildDate;

	}

	public Feed readFeed(String source_name, String channel, String linkURL, String previousLastUpdate,
			boolean first_time_retrieve) throws IOException, ParseException {
		InputStream in;
		boolean isFeedHeader = true;
		String title = "";
		String link = "";
		String description = "";
		String language = "";
		String copyright = "";
		String lastBuildDate = "";
		String pubDate = "";
		List<FeedItem> items = null;

		Feed feed = null;

		URL url = new URL(linkURL);
		try {
			in = url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		inputFactory.setProperty(Const.IS_COALESCING, true);
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
						case Const.CHANNEL:
							feed = new Feed();
							items = new ArrayList<FeedItem>();
							event = eventReader.nextEvent();
							break;
						case Const.TITLE:
							title = getData(event, eventReader);
							if (title.contains(source_name)) {
								if (title.indexOf(source_name) > 0) {
									title = title.substring(0, title.indexOf(source_name) - 1);
								}

							}
							break;
						case Const.LINK:
							link = getData(event, eventReader);
							break;
						case Const.DESCRIPTION:
							description = getData(event, eventReader);
							break;
						case Const.LANGUAGE:
							language = getData(event, eventReader);
							break;
						case Const.COPYRIGHT:
							copyright = getData(event, eventReader);
							break;
						case Const.LAST_BUILD_DATE:
							lastBuildDate = getData(event, eventReader);
							break;
						case Const.PUB_DATE:
							pubDate = getData(event, eventReader);
							if (!pubDateFound) {
								feed.setPreviousLastUpdate(pubDate);
							}
							pubDateFound = true;

							if (!first_time_retrieve) {
								if (pubDate.equalsIgnoreCase(previousLastUpdate)) {
									feed.setItems(items);
									return feed;
								}
							}
							break;
						case Const.ITEM:
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
						}
					}
				} else if (event.isEndElement()) {
					String prefix = event.asEndElement().getName().getPrefix();
					String localPart = event.asEndElement().getName().getLocalPart();

					if (prefix.isEmpty()) {
						if (localPart == (Const.ITEM)) {
							System.out.println("title = " + title);
							FeedItem item = new FeedItem();
							if (title.isEmpty() || link.isEmpty()) {
								// Do nothing
							} else {
								String dayCreated = Utils.formatTime(LocalDateTime.now());
								String timestamp = String.valueOf(System.currentTimeMillis());

								String fileDir = Const.SAVED_FOLDER + dayCreated;
								File dir = new File(fileDir);
								dir.mkdir();

								item.setTitle(title);
								item.setPubDate(pubDate);
								item.setTimestamp(timestamp);
								item.setDayCreated(dayCreated);

								String finalURL = getFinalURL(link);
								item.setLink(finalURL);
								item.setContents(RetrieveContents(finalURL));
								Image image = RetrieveImage(finalURL, dayCreated, timestamp, fileDir);
								if (image != null) {
									item.setImage(image);
									item.setHas_image(true);
								}
								items.add(item);
							}
							event = eventReader.nextEvent();
						} else if (localPart.equalsIgnoreCase(Const.CHANNEL)) {
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

	private String RetrieveContents(String link) throws IOException {
		URL url = new URL(link);
		Document doc;
		String results = "";

		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		doc = Jsoup.parse(sb.toString());
		results = doc.getElementsByClass(Const.ARTICLE).toString();

		return results;
	}

	public static String getFinalURL(String url) throws IOException {
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		con.setInstanceFollowRedirects(false);
		con.connect();
		con.getInputStream();

		if (con.getResponseCode() == HttpURLConnection.HTTP_MOVED_PERM
				|| con.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
			String redirectUrl = con.getHeaderField("Location");
			return getFinalURL(redirectUrl);
		}
		return url;
	}

	private Image RetrieveImage(String link, String dayCreated, String timestamp, String fileDir) throws IOException {

		URL url = new URL(link);
		Image image = null;
		InputStream is = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		// get the image
		Document doc = Jsoup.parse(sb.toString());

		Elements bodies = doc.getElementsByClass(Const.THUMB);
		if (bodies != null && bodies.size() != 0) {
			image = new Image();
			image.setTime_stamp(timestamp);

			Document doc2 = Jsoup.parse(bodies.toString());
			Element element = doc2.select(Const.IMG).first();

			String imageURL = element.attr(Const.SRC);

			image.setLink(imageURL);
			if (imageURL.contains(Const.JPG)) {
				image.setImage_type(Const.JPG);
			}

			// get the image name from system time

			String fileName = Const.IMAGE + Const.UNDERSCORE + timestamp + Const.DOT + Const.JPG;
			image.setImage_name(fileName);

			String fullFIleName = fileDir + "/" + fileName;
			image.setFullFIleName(fullFIleName);

			String image_url = Const.ANDROID_CONNECT + dayCreated + "/" + fileName;
			image.setImage_url(image_url);

		}

		br.close();
		is.close();

		return image;
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
		ImageIO.write(bi2, "PNG", new File(fileDir + "/" + image_id + Const.UNDERSCORE + Const.DOT + Const.PNG));

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
