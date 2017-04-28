package com.it.entity;

import java.util.ArrayList;

public class ExtendMassage {
	
	private String text;
//	private String title;
	private Integer like_count;
	private String time;
	//private Double longitude;
	//private Double latitude;
	private String icon;
	private String name;
	private Integer comment_count;
	private ArrayList<ArrayList<String>> photos;
	
	public Integer getLike_count() {
		return like_count;
	}
	public void setLike_count(Integer like_count) {
		this.like_count = like_count;
	}
	public Integer getComment_count() {
		return comment_count;
	}
	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}
	
	public ArrayList<ArrayList<String>> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<ArrayList<String>> photos) {
		this.photos = photos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
		
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
}
