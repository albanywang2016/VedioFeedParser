package com.davidwang.feed.model;

import java.util.HashMap;
import java.util.Map;

public class FeedSource {
	long message_id;
	String companyName;
	String channel;
	String link;
	
	
	public long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(long l) {
		this.message_id = l;
	}
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
		return "FeedSource [message_id=" + message_id + ", companyName=" + companyName + ", channel=" + channel
				+ ", link=" + link + "]";
	}
	
	
	
}
