package com.yida.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.yida.common.MyVideoItem;
import com.yida.common.SearchUpperItem;
import com.yida.common.VideoItem;
import com.yida.entity.UserInfo;
import com.yida.service.UserInfoService;
import com.yida.service.VideoService;

@Controller
public class AccountController {

	@Resource
	UserInfoService userService;
	@Resource
	VideoService videoService;

	@RequestMapping("space")
	public String toAccount(HttpServletRequest request,@RequestParam("category")String category,
			@RequestParam("type")String type){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会继续操作
		if(user==null){
			return "login";
		}
		
		if(category.equals("video")){
			//视频管理界面
			if(type.equals("video")){
				//获取当前用户上传过的所有视频
				List<MyVideoItem> myVideos=videoService.getMyVideos(user.id);
				
				request.setAttribute("MyVideoList", myVideos);
			}
			//我的收藏界面
			else if(type.equals("favorite")){

				System.out.println(";sd");
				//列表的元素是VideoItem 表示需要展示的单个视频信息
				List<VideoItem> list=videoService.getCollectedVideos(user.id);
				
				request.setAttribute("VideoList",list);
			}
		}
		else if(category.equals("account")){
			
			if(type.equals("concern")){
				
				List<SearchUpperItem> uppers=userService.getConcernedUpperItem(user.id);
				
				request.setAttribute("UpperList",uppers);
				
			}
		}
		
		
		return "space";
	}
	
	/**
	 * 修改用户密码
	 * @param request
	 * @param originalPsw
	 * @param newPsw
	 * @return
	 */
	@RequestMapping("editPassword")
	@ResponseBody
	public String eiditPassword(HttpServletRequest request,@RequestParam String originalPsw,@RequestParam String newPsw){
		

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会继续操作
		if(user==null){
			return "false";
		}
		
		//返回
		String result=userService.editPassword(user.id,originalPsw,newPsw);
		if(result.equals("success")){

			request.getSession().removeAttribute("userInfo");
		}
		
		return result;
	}
	
	
	@RequestMapping("editIntroduce")
	@ResponseBody
	public String editIntroduce(HttpServletRequest request,@RequestParam String introduce) throws Exception{

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会继续操作
		if(user==null){
			return "false";
		}
		
		userService.editIntroduce(user.id,introduce);
		
		//刷新session
		UserInfo u=userService.doLogin(user.account, user.getPassword());
		request.getSession().removeAttribute("userInfo");
		request.getSession().setAttribute("userInfo", u);
		
		
		return "success";
	}
	
	/**
	 * 获取当前头像
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("getHeader")
	public String getHeaderById(HttpServletRequest request,HttpServletResponse response) throws IOException{

		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		if(user==null){
			response.sendRedirect("login.html");
		}
		
		String header=userService.getHeaderById(user.id);
		
		return header;
	}
	
	/**
	 * 下载经由自己上传的，名为fileName的视频
	 * @param fileName 下载的视频路径名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/download")
	public String download(@RequestParam String fileName , HttpServletRequest request, HttpServletResponse response){
		
		//设置响应头，读取文件名信息
		response.setContentType("text/html;charset=utf-8");
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		//获取真正的下载路径
		String ctxPath = request.getSession().getServletContext().getRealPath("/") ;
		String downLoadPath = ctxPath + fileName;
		
		//System.out.println(downLoadPath);
		try {
			long fileLength = new File(downLoadPath).length();
			
			//设置响应头，以响应下载
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			
			//按位写，将文件从服务器传输到客户端
			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			
			byte[] buff = new byte[2048];
			int bytesRead;
			
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭读写流
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	
	}
}
