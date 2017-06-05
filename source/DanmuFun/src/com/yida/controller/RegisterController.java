package com.yida.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yida.service.UserInfoService;

@Controller
public class RegisterController {
	
	@Resource
	UserInfoService service;
	
	@RequestMapping("register")
	public ModelAndView RegisterPage(){
		
		ModelAndView mav=new ModelAndView("register");
		return mav;
	}
	
	@RequestMapping(value="toRegister")
	public String toRegister(@RequestParam String account , @RequestParam String password,@RequestParam String introduce,HttpServletRequest request){
		
		boolean result=false;
		try {
			result=service.doRegister(account,password,introduce);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			return "register";
		}
		
		//正确返回了值，说明成功插入数据库
		if(result){
			//重定向到主页
			return "redirect:/index.html";
		}
		
		return "register";
	}
}
