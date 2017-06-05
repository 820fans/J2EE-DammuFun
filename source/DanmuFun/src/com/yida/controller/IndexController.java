package com.yida.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yida.service.UserInfoService;

@Controller
public class IndexController {

	@Resource
	UserInfoService service;
	
	/**
	 * 将首页重定向到搜索页面
	 * @param request
	 * @return
	 */
	@RequestMapping("index")
	public String IndexPage(HttpServletRequest request){
		
		return "redirect:/search.html?value=&type=video&orderType=default";
	}
}
