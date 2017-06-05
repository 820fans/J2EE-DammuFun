package com.yida.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentItem {
	
	private int id;//评论的id
	
	private int user_id;//评论者的id
	
	private String self;//是否是用户自己评论的
	
	private int video_id;//评论哪个视频的
	
	private String header;//评论者头像
	
	private String account;//评论者账户
	
	private String content;//评论的内容
	
	private Date time;//评论的时间
	
	private int zanNum;//评论总共有几条赞
	
	private int reply_id;//哪一条评论下面的回复

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

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	/**
	 * 获取评论时间 (精确到分钟)
	 * @return
	 */
	public String getCommentTime(){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		return format.format(time);
	}

	public int getZanNum() {
		return zanNum;
	}

	public void setZanNum(int zanNum) {
		this.zanNum = zanNum;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	
	
}
