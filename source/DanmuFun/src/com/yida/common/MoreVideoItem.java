package com.yida.common;

public class MoreVideoItem {
	
	private int id;//视频的id
	
	private int upperId;//阿婆主的id
	
	private String coverPath;//视频封面
	
	private String title;//视频名称
	
	private String type;//视频的类型，以【游戏】title的形式放出

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

	public String getCoverPath() {
		return coverPath;
	}

	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
