package com.yida.common;

import java.util.List;

//排序方式：默认排序、投稿量、粉丝量
public class SearchUpperItem {
	
	private int upperId;//用户id
	
	private String header;//用户头像
	
	private String account;//用户的账户名
	
	private String introduce;//用户简介
	
	private int uploadNum;//投稿量
	
	private int fansNum;//谁关注了他，粉丝数量
	
	private List<MoreVideoItem> myVideos;

	public int getUpperId() {
		return upperId;
	}

	public void setUpperId(int upperId) {
		this.upperId = upperId;
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
	
	/**
	 * 高亮字符串
	 * @param value 需要匹配的字符串
	 * @return
	 */
	public String getHighLightAccount(String value){

		//高亮处理
		String highLight="<span style='color: #f25d8e'>"+value+"</span>";
		String aimAccount=this.account.replace(value, highLight);
		
		return aimAccount;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}

	public String getIntroduce() {
		return introduce;
	}
	
	/**
	 * 高亮字符串
	 * @param value 需要匹配的字符串
	 * @return
	 */
	public String getHighLightIntroduce(String value){

		//高亮处理
		String highLight="<span style='color: #f25d8e'>"+value+"</span>";
		String aimIntroduce=this.introduce.replace(value, highLight);
		
		return aimIntroduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getUploadNum() {
		return uploadNum;
	}

	public void setUploadNum(int uploadNum) {
		this.uploadNum = uploadNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}
	
	public int getMyVideosSize(){
		return myVideos.size();
	}
	
	public List<MoreVideoItem> getMyVideos() {
		return myVideos;
	}

	public void setMyVideos(List<MoreVideoItem> myVideos) {
		this.myVideos = myVideos;
	}
	
}
