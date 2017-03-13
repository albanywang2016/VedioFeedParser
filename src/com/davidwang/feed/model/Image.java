package com.davidwang.feed.model;

public class Image {
	String image_id;
	String image_type;
	String image_name;
	String fullFIleName;
	String image_url;
	String link;
	int width;
	int height;
	

	public String getFullFIleName() {
		return fullFIleName;
	}
	public void setFullFIleName(String fullFIleName) {
		this.fullFIleName = fullFIleName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImage_id() {
		return image_id;
	}
	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
	public String getImage_type() {
		return image_type;
	}
	public void setImage_type(String image_type) {
		this.image_type = image_type;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}

	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
		return "Image [image_id=" + image_id + ", image_type=" + image_type + ", image_name=" + image_name
				+ ", fullFIleName=" + fullFIleName + ", image_url=" + image_url + ", link=" + link + ", width=" + width
				+ ", height=" + height + "]";
	}
	
}
