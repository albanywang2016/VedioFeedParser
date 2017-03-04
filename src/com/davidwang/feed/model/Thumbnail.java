package com.davidwang.feed.model;

public class Thumbnail {
	String URL;
	String credit;
	String medium;
	int width;
	int height;
	String filename;
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "Thumbnail [URL=" + URL + ", credit=" + credit + ", medium=" + medium + ", width=" + width + ", height="
				+ height + ", filename=" + filename + "]";
	}
	
	
}
