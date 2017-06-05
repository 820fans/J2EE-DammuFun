package com.yida.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//视频管理页面
public class MyVideoItem {
	
	private int id;//视频的id
	
	private int upperId;//上传者的id，也就是当前用户的id
	
	private String videoPath;//视频地址
	
	private String coverPath;//视频封面
	
	private String type;//视频类型
	
	private String title;//视频标题
	
	private Date uploadTime;//上传时间
	
	private int viewNum;//观看次数
	
	private int danmuNum;//弹幕数量
	
	private int collectNum;//收藏数量
	
	private int commentNum;//评论数量

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

	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getUploadTime() {
		return uploadTime;
	}
	
	public String getUploadDetailTime(){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return format.format(uploadTime);
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public int getDanmuNum() {
		return danmuNum;
	}

	public void setDanmuNum(int danmuNum) {
		this.danmuNum = danmuNum;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
}
