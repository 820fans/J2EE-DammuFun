package com.yida.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoItem {
	
	private int id;//视频在整个数据库中的唯一ID
	
	private int upperId;//阿婆主的id
	
	private String videoPath;//视频的路径
	
	private String coverPath;//封面图片
	
	private String type;//类型
	
	private String title;//标题
	
	private String brief;//简介
	
	private int viewNum;//播放量
	
	private int collectNum;//收藏量
	
	private int danmuNum;//弹幕数量
	
	private Date uploadTime;//上传时间
	
	private String upperName;//阿婆主的名字
	
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
	
	/**
	 * 高亮字符串
	 * @param value 需要匹配的字符串
	 * @return
	 */
	public String getHighLightTitle(String value){

		//高亮处理
		String highLight="<span style='color: #f25d8e'>"+value+"</span>";
		String aimTitle=this.title.replace(value, highLight);
		
		return aimTitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getDanmuNum() {
		return danmuNum;
	}

	public void setDanmuNum(int danmuNum) {
		this.danmuNum = danmuNum;
	}

	public String getUploadTime() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		return format.format(uploadTime);
	}
	
	public String getUploadDetailTime(){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		return format.format(uploadTime);
	}

	public void setUploadTime(Date uploadTime) {

		this.uploadTime =  uploadTime;
	}

	public String getUpperName() {
		return upperName;
	}

	public void setUpperName(String upperName) {
		this.upperName = upperName;
	}
}
