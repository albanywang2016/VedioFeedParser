package com.davidwang.feed.model;

public class FeedSource {
	String companyName;
	String channel;
	String link;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String name) {
		this.companyName = name;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	@Override
	public String toString() {
		return "FeedSource [name=" + companyName + ", channel=" + channel + ", link=" + link + "]";
	}
	
	
	
}
