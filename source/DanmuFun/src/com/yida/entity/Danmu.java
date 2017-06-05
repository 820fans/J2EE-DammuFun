package com.yida.entity;

/**
 * 弹幕实体类，用于存放弹幕信息
 *
 */
public class Danmu {
	
	private int id;
	
	private int user_id;//发送者的id
	
	private int video_id;//视频的id
	
	//弹幕发布的时间releaseTime不计入实体类，因为可以用now录入数据
	
	private String text;//弹幕内容
	
	private String color;//弹幕颜色
	
	private int position;//弹幕的位置
	
	private int size;//弹幕字体大小
	
	private int time;//弹幕经过屏幕的时间长度
	
	public Danmu(){
		
	}
	
	public Danmu(int user_id,int video_id,String text,String color,int position,int size,int time){
		this.user_id=user_id;
		this.video_id=video_id;
		this.text=text;
		this.color=color;
		this.position=position;
		this.size=size;
		this.time=time;
		
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	
	
}
