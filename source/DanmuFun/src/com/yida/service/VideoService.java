package com.yida.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yida.common.MoreVideoItem;
import com.yida.common.MyVideoItem;
import com.yida.common.VideoItem;
import com.yida.dao.IVideoDao;
import com.yida.entity.Collect;
import com.yida.entity.Danmu;
import com.yida.entity.Video;

@Service
public class VideoService {
	
	@Resource
	IVideoDao dao;
	
	/**
	 * 获取当前可用的最小的video.id,用于视频插入
	 * @return
	 */
	public int getAvailableVId() {
		return dao.getVideoIncrementIndex();
		//return dao.getVideosCount();
	}
	
	/**
	 * 插入新的视频记录
	 * @param userId 用户名
	 * @param title 视频标题
	 * @param coverPath 视频封面
	 * @param videoPath 视频地址
	 * @param type 视频类型
	 * @param brief 视频简介
	 */
	public void insertVideo(int userId, String title, String coverPath,String videoPath,
			String type, String brief) {
		
		Video video=new Video(userId, title, coverPath, videoPath, type, brief);
		dao.insertVideo(video);
	}
	
	/**
	 * 按照orderType排序规则，搜索视频
	 * @param orderType
	 * @param searchValue 
	 * @return
	 */
	public List<VideoItem> getVideosInOrder(String orderType, String searchValue) {

		return dao.getVideosInOrder(orderType,searchValue);		
	}
	
	/**
	 * 根据视频的id获取到单个视频的VideoItem 对象
	 * @param videoId
	 * @return
	 */
	public VideoItem getSingleVideoById(String videoId) {
		if(videoId!=null)
			return dao.getSingleVideoById(videoId);
		
		return null;
	}
	
	/**
	 * 根据视频的Id 获取视频所有弹幕
	 * @param videoId
	 * @return
	 */
	public List<Danmu> getDanmusByVideoId(String videoId) {
		// TODO Auto-generated method stub
		return dao.getDanmusByVideoId(Integer.valueOf(videoId));
	}
	
	/**
	 * 视频浏览记录+1
	 * @param upperId
	 * @param videoId
	 */
	public void addViewRecord(String videoId) {
		
		dao.addViewRecord(Integer.valueOf(videoId).intValue());
		
		return;
	}
	
	/**
	 * 添加视频弹幕
	 * @param userId
	 * @param videoId
	 * @param text
	 * @param color
	 * @param position
	 * @param size
	 * @param time
	 */
	public void addDanmutoVideo(int userId, int videoId, String text,
			String color, int position, int size, int time) {
		//说明用户非法
		if(userId<=0){
			return;
		}
		
		Danmu danmu=new Danmu(userId, videoId, text, color, position, size, time);
		dao.addDanmutoVideo(danmu);
	}
	
	/**
	 * 获取用户收藏视频信息
	 * @param id
	 * @param videoId
	 */
	public Collect getCollectInfo(int id, int videoId) {
		return dao.getCollectInfo(id,videoId);
	}
	
	/**
	 * 用户收藏视频
	 * @param userId 用户名
	 * @param videoId  视频名
	 */
	public void collectVideoWithUser(int userId, int videoId) {
		
		//说明用户非法
		if(userId<=0){
			return;
		}
		
		dao.collectVideoWithUser(userId,videoId);
		
		return;
	}
	
	/**
	 * 取消收藏视频
	 * @param userId
	 * @param videoId
	 */
	public void uncollectVideoWithUser(int userId, int videoId) {

		//说明用户非法
		if(userId<=0){
			return;
		}
		
		dao.uncollectVideoWithUser(userId,videoId);
		
		return;
	}
	
	/**
	 * 获取阿婆主更多上传的视频
	 * @param upperId
	 * @return
	 */
	public List<MoreVideoItem> getMoreVideo(int upperId,int size) {
		
		return dao.getMoreVideo(upperId,size);
	}
	
	/**
	 * 获取用户上传的所有视频
	 * @param user_id
	 * @return
	 */
	public List<MyVideoItem> getMyVideos(int user_id) {
		return dao.getMyVideos(user_id);
	}
	
	/**
	 * 获取收藏过的视频
	 * @param id
	 * @return
	 */
	public List<VideoItem> getCollectedVideos(int user_id) {
		return dao.getCollectedVideos(user_id);
	}
	
	/**
	 * 编辑上传过的视频
	 * @param id
	 * @param videoId
	 * @param title
	 * @param type
	 * @param brief
	 */
	public void editVideo(int id, int videoId, String title, String type,
			String brief) {
		dao.editVideo(id,videoId,title,type,brief);
		
	}

}
