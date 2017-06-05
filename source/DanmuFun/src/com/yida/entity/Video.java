package com.yida.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class Video {
	
	private int id;
	
	private int upperId;
	
	private String title;
	
	private String coverPath;
	
	private String videoPath;
	
	private String type;
	
	private String brief;
	
//	private List<Danmu> danmus;//弹幕池
//	
//	private List<Collect> collects;//收藏列表
//	
//	private List<ViewRecord> viewRecords;//播放列表
	
	public Video(){
		
	}
	
	public Video(int upperId,String title,String coverPath,String videoPath,String type,String brief){
		this.upperId=upperId;
		this.title=title;
		this.coverPath=coverPath;
		this.videoPath=videoPath;
		this.type=type;
		this.brief=brief;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUpperId() {
		return upperId;
	}

	public void setUpperId(int upperId) {
		this.upperId = upperId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

//	public List<Danmu> getDanmus() {
//		return danmus;
//	}
//
//	public void setDanmus(List<Danmu> danmus) {
//		this.danmus = danmus;
//	}
//
//	public List<Collect> getCollects() {
//		return collects;
//	}
//
//	public void setCollects(List<Collect> collects) {
//		this.collects = collects;
//	}
//
//	public List<ViewRecord> getViewRecords() {
//		return viewRecords;
//	}
//
//	public void setViewRecords(List<ViewRecord> viewRecords) {
//		this.viewRecords = viewRecords;
//	}
	
	
}
