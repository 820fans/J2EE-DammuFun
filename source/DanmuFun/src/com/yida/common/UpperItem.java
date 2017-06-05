package com.yida.common;

public class UpperItem {
	
	private int id;//用户id
	
	private String header;//用户头像
	
	private String account;//用户的账户名
	
	private int uploadNum;//投稿量
	
	private int concernNum;//他关注了谁
	
	private int fansNum;//谁关注了他

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getUploadNum() {
		return uploadNum;
	}

	public void setUploadNum(int uploadNum) {
		this.uploadNum = uploadNum;
	}

	public int getConcernNum() {
		return concernNum;
	}

	public void setConcernNum(int concernNum) {
		this.concernNum = concernNum;
	}

	public int getFansNum() {
		return fansNum;
	}

	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}
	
}
