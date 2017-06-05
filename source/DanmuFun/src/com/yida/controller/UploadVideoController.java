package com.yida.controller;

import java.io.*;  

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;  

import org.apache.commons.fileupload.FileItemIterator;   
import org.apache.commons.fileupload.FileItemStream;   
import org.apache.commons.fileupload.servlet.ServletFileUpload;   
import org.apache.commons.fileupload.util.Streams; 

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yida.entity.UserInfo;
import com.yida.service.VideoService;

@Controller
public class UploadVideoController {
	
	@Resource
	VideoService service;

	private boolean exception=false;
	
	@RequestMapping("uploadVideo")
	public String uploadVideoPage(HttpServletRequest request){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，重定向
		if(user==null){
			return "redirect:/login.html";
		}
		
		String videoId=request.getParameter("editId");
		if(videoId!=null){
			return "editVideo";
		}
		
		return "uploadVideo";
	}
	
	/**
	 * 保存视频封面 视频 到服务器
	 * @param request
	 * @param file
	 * @param name 保存为视频的内容
	 */
	public void SaveSingleFileasNametoPath(HttpServletRequest request, MultipartFile file,String targetName){
		String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
		
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File targetFile = new File(uploadUrl + targetName);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				//错误标记
				exception=true;
				e.printStackTrace();
			}
		}

		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			//错误标记
			exception=true;
			e.printStackTrace();
		} catch (IOException e) {
			//错误标记
			exception=true;
			e.printStackTrace();
		}
	}

	/**
	 * 视频投稿
	 * @param request HTTP请求
	 * @param cover 封面图片
	 * @param title 视频标题
	 * @param type 视频类型
	 * @param brief 视频简介
	 * @param videofile 视频内容
	 * @return 重定向位置
	 */
	@RequestMapping("videoUpload")
	public String videoUpload(HttpServletRequest request, @RequestParam MultipartFile cover,
			@RequestParam String title,@RequestParam String type,
			@RequestParam String brief,@RequestParam MultipartFile videofile){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，重定向
		if(user==null){
			return "redirect:/login.html";
		}
		
		//错误标记
		exception=false;
		
		//从数据库获取当前应该赋值的大小，例如，数据库只有1条数据
		//那封面就叫cover2，视频就叫video2
		//获取当前可用的编号
		
		//改为获取Auto_Increment的代号
		int availableId=service.getAvailableVId();
		
		String coverName="cover"+availableId;
		String videoName="video"+availableId;
		
		//获取视频和图片的后缀
		String filename1 = cover.getOriginalFilename();
		String suffix1=filename1.substring(filename1.lastIndexOf("."));
		String filename2 = videofile.getOriginalFilename();
		String suffix2=filename2.substring(filename2.lastIndexOf("."));
		
		//生成新的文件名
		coverName=coverName+suffix1;
		videoName=videoName+suffix2;
		
		//保存封面
		SaveSingleFileasNametoPath(request,cover,coverName);
		//保存视频
		SaveSingleFileasNametoPath(request,videofile,videoName);
		
		//上传出错，返回原页面
		if(exception){
			return "redirect:/uploadVideo.html";
		}
		
		//上传正常，则写入数据库
		//封面的路径
		String coverPath="upload/"+coverName;
		//视频路径
		String videoPath="upload/"+videoName;
		
		service.insertVideo(user.id,title,coverPath,videoPath,type,brief);
		
		return "redirect:/uploadVideo.html";
	}
	
}
