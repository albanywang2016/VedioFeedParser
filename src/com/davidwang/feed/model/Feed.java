package com.davidwang.feed.model;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	String title;
	String link;
	String description;
	String language;
	String copyright;
	String lastBuildDate;
	String creator;
	String publisher;
	String updatePeriod;
	int updateFrequency;
	String updateBase;

	
	List<FeedItem> items = new ArrayList<FeedItem>();

	public Feed(String title, String link, String description, Image image) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public Feed(String title, String link, String description, Image image, String language, String copyright,
			String lastBuildDate, List<FeedItem> items) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
		this.language = language;
		this.copyright = copyright;
		this.lastBuildDate = lastBuildDate;
		this.items = items;
	}

	public Feed(String title, String link, String description) {
		super();
		this.title = title;
		this.link = link;
		this.description = description;
	}

	public Feed() {
		super();
	}


	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getUpdatePeriod() {
		return updatePeriod;
	}

	public void setUpdatePeriod(String updatePeriod) {
		this.updatePeriod = updatePeriod;
	}

	public int getUpdateFrequency() {
		return updateFrequency;
	}

	public void setUpdateFrequency(int updateFrequency) {
		this.updateFrequency = updateFrequency;
	}

	public String getUpdateBase() {
		return updateBase;
	}

	public void setUpdateBase(String updateBase) {
		this.updateBase = updateBase;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setItems(List<FeedItem> items) {
		this.items = items;
	}


	public List<FeedItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Feed [title=" + title + ", link=" + link + ", description=" + description + ", language=" + language
				+ ", copyright=" + copyright + ", lastBuildDate=" + lastBuildDate + ", creator=" + creator
				+ ", publisher=" + publisher + ", updatePeriod=" + updatePeriod + ", updateFrequency=" + updateFrequency
				+ ", updateBase=" + updateBase + ", items=" + items + "]";
	}
	
	
}
