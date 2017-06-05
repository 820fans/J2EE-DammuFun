package com.yida.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.yida.common.RandomValidateCode;
import com.yida.entity.UserInfo;
import com.yida.service.UserInfoService;

@Controller
@SessionAttributes("userInfo")
public class LoginController {

	@Resource
	UserInfoService service;

	@RequestMapping("login")
	public ModelAndView LoginPageHeader(HttpServletRequest request){

		ModelAndView loginPage = new ModelAndView("login");
		
		//loginPage.addObject("view", "here");
		return loginPage;

	}

	@Resource
	RandomValidateCode code;

	@RequestMapping("vcode")
	public void vcode(HttpServletRequest request, HttpServletResponse response){
		code.getRandcode(request, response);
	}	

	@RequestMapping(value = "toIndex", method = RequestMethod.POST)
	public String toIndex(@RequestParam String account , @RequestParam String password, ModelMap map ,
			HttpServletRequest request,  HttpServletResponse response, @RequestParam String vcode){
		UserInfo userInfo;
		try {
			userInfo=service.doLogin(account,password);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			return "login";
		}
		
		//获取session中的验证码字符
		String sessionCode = (String)request.getSession().getAttribute(RandomValidateCode.RANDOM_CODE_KEY);
		vcode=vcode.toLowerCase();
		if (vcode.equals(sessionCode)) {
			//存入session
			//map.put("userInfo", userInfo);
			request.getSession().setAttribute("userInfo", userInfo);
			
			//转到主页面
			return "redirect:/index.html";
		}else {
			request.setAttribute("error", "验证码错误");
			return "login";
		}
	}
	

	@RequestMapping("toLogout")
	public String toLogout(SessionStatus status){
		status.setComplete();
		return "redirect:/index.html";
	}

}
