package com.davidwang.feed.model;

public class Image {
	String image_id;
	String image_type;
	String image_name;
	String image_file_name;
	String link;
	int width;
	int height;
	

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
	public String getImage_file_name() {
		return image_file_name;
	}
	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
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
				+ ", image_file_name=" + image_file_name + ", link=" + link + ", width=" + width + ", height=" + height
				+ "]";
	}
	
}
