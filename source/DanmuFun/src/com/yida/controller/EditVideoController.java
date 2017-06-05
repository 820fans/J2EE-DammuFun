package com.yida.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.common.VideoItem;
import com.yida.entity.UserInfo;
import com.yida.service.VideoService;

@Controller
public class EditVideoController {
	
	@Resource
	VideoService videoService;
	
	@RequestMapping("editVideo")
	public String editPage(HttpServletRequest request,@RequestParam String videoId){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，重定向
		if(user==null){
			return "redirect:/login.html";
		}
		
		//获取到需要编辑的视频信息
		VideoItem video=videoService.getSingleVideoById(videoId);
		
		if(video==null){
			request.setAttribute("error", "您尚未上传此视频，或数据库视频已丢失");
		}
		else{
			request.setAttribute("title", video.getTitle());
			request.setAttribute("type", video.getType());
			request.setAttribute("brief", video.getBrief());
		}
		
		return "editVideo";
	}
	
	@RequestMapping("editVideoSubmit")
	@ResponseBody
	public String editVideoSubmit(HttpServletRequest request,@RequestParam int videoId,@RequestParam String title,
			@RequestParam String type,@RequestParam String brief){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，重定向
		if(user==null){
			return "false";
		}
		
		videoService.editVideo(user.id,videoId,title,type,brief);
		
		return "success";
	}
}
