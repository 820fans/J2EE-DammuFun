package com.yida.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yida.common.CommentItem;
import com.yida.common.MoreVideoItem;
import com.yida.common.UpperItem;
import com.yida.common.VideoItem;
import com.yida.entity.Collect;
import com.yida.entity.CommentZans;
import com.yida.entity.Concern;
import com.yida.entity.Danmu;
import com.yida.entity.UserInfo;
import com.yida.service.CommentService;
import com.yida.service.UserInfoService;
import com.yida.service.VideoService;


@Controller
public class DetailController {

	@Resource
	VideoService videoService;
	@Resource
	UserInfoService userService;
	@Resource
	CommentService commentService;

    ObjectMapper mapper = new ObjectMapper(); 

	@RequestMapping("detail")
	public String detailPage(HttpServletRequest request,@RequestParam String videoId){

		//判定阿婆主和当前用户是否是同一人，是否有关注关系
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		
		//获取视频介绍信息
		VideoItem item= videoService.getSingleVideoById(videoId);
		
		//获取视频的阿婆主信息
		UpperItem upper=userService.getUpperInfo(item.getUpperId());
		
		//获取视频阿婆主的更多视频信息
		//限制获取5个
		int size=5;
		List<MoreVideoItem> moreVideos=videoService.getMoreVideo(item.getUpperId(),size);
		
		//更多视频里面要去除当前正在播放的视频
		for(int i=0;i<moreVideos.size();i++){
			//当前正在播放的视频不需要推荐
			if(moreVideos.get(i).getId() == Integer.valueOf(videoId)){
				moreVideos.remove(i);
			}
		}
		
		//获取视频的评论信息
		List<CommentItem> comments=commentService.getCommentsById(videoId);
		//完善comments 设置是否是用户自己
		for(int i=0;i<comments.size();i++){
			//System.out.println("user"+comments.get(i).getUser_id()+comments.get(i).getId());
			if(user!=null){
				
				CommentZans zaned=(CommentZans) commentService.getCommentZanInfo(user.id,
						comments.get(i).getId());
				//已经赞过了
				if(zaned!=null){
					comments.get(i).setSelf("已");
				}
			}
			else{
				comments.get(i).setSelf("");
			}
		}

		//视频信息
		request.setAttribute("Item", item);
		//阿婆主信息
		request.setAttribute("upper", upper);
		//评论信息
		request.setAttribute("comments", comments);
		//更多视频信息
		request.setAttribute("moreVideos", moreVideos);

		//用户没有登录，
		if(user==null){
			//表示显示的是  +关注 按钮 
			request.setAttribute("concern", "true");
			//没有收藏信息
			request.setAttribute("favorite", "false");
		}
		//阿婆主就是在登录的这个人
		else if(user.id==upper.getId()){
			//显示的是进入个人空间
			request.setAttribute("concern", "space");
		}
		else{
			//用户登录了，需要判定是否关注过
			Concern concernInfo=(Concern) userService.getConcernInfo(user.id,upper.getId());

			//说明当前用户没有关注这个用户
			if(concernInfo==null){
				//表示显示的是  +关注 按钮 
				request.setAttribute("concern", "true");
			}
			else{
				//说明当前用户已经关注了这个用户
				//表示显示的是  -关注 按钮 
				request.setAttribute("concern", "false");
			}
		}

		//检查登录用户的收藏信息
		if(user!=null){
			//用户是否收藏过这个视频
			Collect collect = (Collect) videoService.getCollectInfo(user.id,Integer.valueOf(videoId).intValue());

			if(collect==null){
				//没有收藏信息
				request.setAttribute("favorite", "false");
			}
			else{
				request.setAttribute("favorite", "true");
			}
		}

		return "detail";
	}

	@RequestMapping(value="getDanmu")
	@ResponseBody
	public String getDanmu(HttpServletRequest request,HttpServletResponse response,@RequestParam String videoId) throws IOException{

		List<Danmu> list=videoService.getDanmusByVideoId(videoId);
		
        
		String result = mapper.writeValueAsString(list);

		return result;
	}

	@RequestMapping("setDanmu")
	@ResponseBody
	public String setDanmu(HttpServletRequest request,@RequestParam String text,
			@RequestParam String color,@RequestParam int position,@RequestParam int size,
			@RequestParam int time,@RequestParam String videoId){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}

		videoService.addDanmutoVideo(user.id,Integer.valueOf(videoId).intValue(),text,color,position,size,time);

		return "true";
	}

	@RequestMapping("collectVideo")
	@ResponseBody
	public String collectVideo(HttpServletRequest request,@RequestParam int videoId){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}

		//检查是否已经关注过
		Collect collect=(Collect)videoService.getCollectInfo(user.id, videoId);
		if(collect!=null){
			return "already";
		}

		videoService.collectVideoWithUser(user.id,videoId);

		return "success";
	}

	@RequestMapping("uncollectVideo")
	@ResponseBody
	public String uncollectVideo(HttpServletRequest request,@RequestParam int videoId){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}

		videoService.uncollectVideoWithUser(user.id,videoId);

		return "success";
	}

	/**
	 * 关注某人
	 * @param request
	 * @param friend_id
	 * @return
	 */
	@RequestMapping("concernHim")
	@ResponseBody
	public String concernHim(HttpServletRequest request,@RequestParam int friend_id){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}

		Concern concern=(Concern) userService.getConcernInfo(user.id, friend_id);
		if(concern!=null){
			return "already";
		}

		//没有关注的情况下，才可以关注
		userService.concernHim(user.id,friend_id);

		return "success";
	}

	/**
	 * 取消关注某人
	 * @param request
	 * @param friend_id
	 * @return
	 */
	@RequestMapping("unconcernHim")
	@ResponseBody
	public String unconcernHim(HttpServletRequest request,@RequestParam int friend_id){

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}

		userService.unconcernHim(user.id,friend_id);

		return "success";
	}

	@RequestMapping("addComment")
	@ResponseBody
	public String addComment(HttpServletRequest request,@RequestParam int videoId,
			@RequestParam String content) throws JsonProcessingException{

		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}

		String reply_id=request.getParameter("reply_id");

		//没有回复别人
		if(reply_id==null){
			commentService.addComment(user.id,videoId,content);
			
			//获取最新一条，由当前用户发起的评论
			CommentItem item=commentService.getMyLatestComment(user.id,videoId);
			
			String result = mapper.writeValueAsString(item);

			return result;

		}
		//这一条是回复评论
		else{

		}

		return "success";
	}
	
	@RequestMapping("zanComment")
	@ResponseBody
	public String zanComment(HttpServletRequest request,@RequestParam int comment_id){
		
		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}
		
		CommentZans zaned=(CommentZans) commentService.getCommentZanInfo(user.id,comment_id);
		//已经赞过了
		if(zaned!=null){
			return "already";
		}
		
		commentService.zanComment(user.id,comment_id);
		
		return "success";
	}
	
	@RequestMapping("unzanComment")
	@ResponseBody
	public String unzanComment(HttpServletRequest request,@RequestParam int comment_id){
		
		//判定用户合法性
		UserInfo user=(UserInfo) request.getSession().getAttribute("userInfo");
		//没有登录，不会插入到数据库
		if(user==null){
			return "false";
		}
		
		commentService.unzanComment(user.id,comment_id);
		
		return "success";
	}
	
	/**
	 * 增加播放记录
	 * @param request
	 * @param videoId
	 * @return
	 */
	@RequestMapping("viewVideo")
	@ResponseBody
	public String viewVideo(HttpServletRequest request,@RequestParam String videoId){

		//视频浏览量+1
		videoService.addViewRecord(videoId);
		
		return "success";
	}
}
