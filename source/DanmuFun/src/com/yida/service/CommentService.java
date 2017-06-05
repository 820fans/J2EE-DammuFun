package com.yida.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yida.common.CommentItem;
import com.yida.dao.ICommentDao;
import com.yida.entity.CommentZans;

@Service
public class CommentService {
	
	@Resource
	ICommentDao dao;
	
	/**
	 * 向数据库插入评论
	 * @param userId
	 * @param videoId
	 * @param content
	 */
	public void addComment(int userId, int videoId, String content) {
		dao.addComment(userId,videoId,content);
	}
	
	/**
	 * 从数据库里取出当前视频所对应的所有评论
	 * @param videoId
	 * @return
	 */
	public List<CommentItem> getCommentsById(String videoId) {
		return dao.getCommentsById(videoId);
	}
	
	/**
	 * 对评论进行点赞
	 * @param id
	 * @param comment_id
	 */
	public void zanComment(int user_id, int comment_id) {
		
		if(user_id>0){
			dao.zanComment(user_id,comment_id);
		}
		
	}
	
	/**
	 * 获取某个用户是否已经重复赞过
	 * @param user_id
	 * @param comment_id
	 * @return
	 */
	public CommentZans getCommentZanInfo(int user_id, int comment_id) {
		return dao.getCommentZanInfo(user_id,comment_id);
	}
	
	/**
	 * 对评论取消赞
	 * @param id
	 * @param comment_id
	 */
	public void unzanComment(int user_id, int comment_id) {
		
		if(user_id>0){
			dao.unzanComment(user_id,comment_id);
		}
		
	}
	
	/**
	 * 获取当前用户发布的最新的一条评论
	 * @param id
	 * @param videoId
	 * @return
	 */
	public CommentItem getMyLatestComment(int id, int videoId) {
		return dao.getMyLatestComment(id,videoId);
	}
}
