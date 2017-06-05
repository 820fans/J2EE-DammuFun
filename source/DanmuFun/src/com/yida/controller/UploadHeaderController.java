package com.yida.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yida.common.Base64Decode;
import com.yida.entity.UserInfo;
import com.yida.service.UserInfoService;

@Controller
public class UploadHeaderController {

	@Resource
	UserInfoService service;
	
	@RequestMapping(value = "/uploadHeader", method = RequestMethod.POST)
	@ResponseBody
	public  String uploadHeader(HttpServletRequest request) throws Exception {
		
		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，重定向
		if(user==null){
			return "false";
		}
		
		String data=null;
		try {
			//解码web编码，并提取Base64数据部分
			data = URLDecoder.decode(request.getParameter("data"),"UTF-8").split("base64,")[1];
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		boolean result = Base64Decode.Decode(data,request.getServletContext().getRealPath("/") + "img/user",user.id+".png");
		//成功保存到服务器,更新数据库
		if(result){
			service.updateHeader("img/user/"+user.id+".png",user.id);
			
			//刷新session
			UserInfo u=service.doLogin(user.account, user.getPassword());
			request.getSession().removeAttribute("userInfo");
			request.getSession().setAttribute("userInfo", u);
		}
		return "true";
	} 
}
