package com.davidwang.feed.model;

import java.util.ArrayList;
import java.util.List;

public class FeedItem {

	String message_id;
	String title;
	String creator;
	String link;
	String guid;
	String description;
	String contents;
	String pubDate;
	String timestamp;
	Image image;
	String dayCreated;
	boolean has_image;
	
		
	public boolean isHas_image() {
		return has_image;
	}
	public void setHas_image(boolean has_image) {
		this.has_image = has_image;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getMessage_id() {
		return message_id;
	}
	public void setMessage_id(String message_id) {
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
		return "FeedItem [message_id=" + message_id + ", title=" + title + ", creator=" + creator + ", link=" + link
				+ ", guid=" + guid + ", description=" + description + ", contents=" + contents + ", pubDate=" + pubDate
				+ ", timestamp=" + timestamp + ", image=" + image + ", dayCreated=" + dayCreated + ", has_image="
				+ has_image + "]";
	}
	
	
	
}
