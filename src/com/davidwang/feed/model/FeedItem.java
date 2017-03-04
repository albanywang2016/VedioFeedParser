package com.davidwang.feed.model;

import java.util.ArrayList;
import java.util.List;

public class FeedItem {

	long message_id;
	String title;
	String creator;
	String link;
	String guid;
	String description;
	String contents;
	String pubDate;
	String timestamp;
	int numberOfImages;
	List<Image> imageList;
	String dayCreated;
	
		
	public long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}
	public String getDayCreated() {
		return dayCreated;
	}
	public void setDayCreated(String dayCreated) {
		this.dayCreated = dayCreated;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getNumberOfImages() {
		return numberOfImages;
	}
	public void setNumberOfImages(int numberOfImages) {
		this.numberOfImages = numberOfImages;
	}
	public List<Image> getImageList() {
		return imageList;
	}
	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}
	public FeedItem(String title, String link, String guid, String description, String pubDate) {
		super();
		this.title = title;
		this.link = link;
		this.guid = guid;
		this.description = description;
		this.pubDate = pubDate;
	}
	public FeedItem() {
		super();
	}
	public FeedItem(List<Thumbnail> thumbnailList) {
		super();
		this.imageList = new ArrayList<Image>();

	}
	
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "FeedItem [title=" + title + ", creator=" + creator + ", link=" + link + ", guid=" + guid
				+ ", description=" + description + ", contents=" + contents + ", pubDate=" + pubDate + ", timestamp="
				+ timestamp + ", numberOfImages=" + numberOfImages + ", imageList=" + imageList + ", dayCreated="
				+ dayCreated + "]";
	}
	
	
	
}
