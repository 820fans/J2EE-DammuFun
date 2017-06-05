package com.yida.entity;

public class ViewRecord {
	
	private int id;
	
	private int video_id;

	//观看的时间不放入实体类，因为插入数据的时候可以用now()
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
}
