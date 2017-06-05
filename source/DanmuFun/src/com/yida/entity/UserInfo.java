package com.yida.entity;

import javax.annotation.Resource;

import com.yida.service.UserInfoService;

public class UserInfo {
	
	@Resource
	UserInfoService service;
	
	public int id;
	
	public String account;
	
	private String password;
	
	private String introduce;

	private String header;
	
	public UserInfo(){
		
	}
	
	public UserInfo(String account,String password,String introduce,String header){
		this.account=account;
		this.password=password;
		this.introduce=introduce;
		this.header=header;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getHeader() {
		
//		if(service.checkHeader(header)){
//			return header;
//		}
//		else{
//			return "img/noface.gif";
//		}
		return header;
		 
		
	}

	public void setHeader(String header) {
		this.header = header;
	}
	

}
