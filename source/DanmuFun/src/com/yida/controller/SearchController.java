package com.yida.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yida.common.MoreVideoItem;
import com.yida.common.SearchUpperItem;
import com.yida.common.VideoItem;
import com.yida.entity.UserInfo;
import com.yida.service.UserInfoService;
import com.yida.service.VideoService;

@Controller
public class SearchController {
	
	@Resource
	VideoService videoService;
	@Resource
	UserInfoService userService;
	
	/**
	 * 搜索页面
	 * @param type 请求的是视频还是好友
	 * @param orderType 请求的视频，按何种方式排序
	 * @return 返回搜索页面
	 */
	@RequestMapping("search")
	public String searchPage(HttpServletRequest request,String type,
			String orderType,String value){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		
		type=request.getParameter("type");
		orderType=request.getParameter("orderType");
		value=request.getParameter("value");
		
		if(type==null) type="video";
		if(orderType==null) orderType="default";
		
		System.out.println(type+orderType);
		
		//用户搜索的是视频
		if(type.equals("video")){
			
			//获取按照某种顺序排列的小列表
			//列表的元素是VideoItem 表示需要展示的单个视频信息
			List<VideoItem> list=videoService.getVideosInOrder(orderType,value);
			
			request.setAttribute("VideoList",list);
			request.setAttribute("searchValue", value);
		}
		//找用户的
		else if(type.equals("upper")){
			List<SearchUpperItem> uppers=userService.getSearchUpperItem(orderType,value);
			
			int size=3;
			
			for(int i=0;i<uppers.size();i++){
				SearchUpperItem item=uppers.get(i);
				List<MoreVideoItem> moreVideos=videoService.getMoreVideo(item.getUpperId(),size);
				uppers.get(i).setMyVideos(moreVideos);
				
				if(user!=null){
					//去除用户自己
					if(uppers.get(i).getUpperId() == user.id){
						uppers.remove(i);
						
					}
				}
			}
			
			request.setAttribute("UpperList",uppers);
			request.setAttribute("searchValue", value);
			
		}
		
		return "search";
	}
}
