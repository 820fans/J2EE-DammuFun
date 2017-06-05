package com.yida.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yida.common.MoreVideoItem;
import com.yida.common.MyVideoItem;
import com.yida.common.VideoItem;
import com.yida.entity.Collect;
import com.yida.entity.Danmu;
import com.yida.entity.Video;

@Repository
public interface IVideoDao {
	
	/**
	 *  获取数据库已经存在的视频总数
	 * @return
	 */
	public int getVideosCount();
	
	/**
	 * 插入新的视频
	 * @param video
	 */
	public void insertVideo(@Param("video")Video video);
	
	/**
	 * 按照一定的排序规则，获取视频
	 * @param orderType
	 * @param searchValue
	 * @return
	 */
	public List<VideoItem> getVideosInOrder(@Param("orderType")String orderType, @Param("searchValue")String searchValue);
	
	/**
	 * 获取单个视频信息
	 * @param videoId
	 * @return
	 */
	public VideoItem getSingleVideoById(@Param("videoId")String videoId);
	
	/**
	 * 获取单个视频的弹幕
	 * @param videoId
	 * @return
	 */
	public List<Danmu> getDanmusByVideoId(@Param("videoId")int videoId);
	
	/**
	 * 添加视频观看记录
	 * @param videoId
	 */
	public void addViewRecord(@Param("videoId")int videoId);
	
	/**
	 * 添加弹幕
	 * @param danmu
	 */
	public void addDanmutoVideo(@Param("danmu")Danmu danmu);
	
	/**
	 * 获取收藏信息
	 * @param id
	 * @param videoId
	 * @return
	 */
	public Collect getCollectInfo(@Param("user_id")int id,@Param("video_id") int videoId);
	
	/**
	 * 用户收藏视频
	 * @param userId
	 * @param videoId
	 */
	public void collectVideoWithUser(@Param("user_id")int userId,@Param("video_id") int videoId);
	
	/**
	 * 用户取消收藏视频
	 * @param userId
	 * @param videoId
	 */
	public void uncollectVideoWithUser(@Param("user_id") int userId, @Param("video_id") int videoId);
	
	/**
	 * 视频详情页面，获取更多视频
	 * @param upperId
	 * @param size
	 * @return
	 */
	public List<MoreVideoItem> getMoreVideo(@Param("upperId")int upperId, @Param("size")int size);
	
	/**
	 * 获取阿婆主自己已经上传的全部视频
	 * @param upperId
	 * @return
	 */
	public List<MyVideoItem> getMyVideos(@Param("upperId")int upperId);
	
	/**
	 * 获取收藏过的视频
	 * @param user_id
	 * @return
	 */
	public List<VideoItem> getCollectedVideos(@Param("user_id") int user_id);
	
	/**
	 * 编辑用户上传过的视频
	 * @param id
	 * @param videoId
	 * @param title
	 * @param type
	 * @param brief
	 */
	public void editVideo(@Param("upperId")int upperId,@Param("videoId") int videoId,
			@Param("title")String title,@Param("type") String type,@Param("brief") String brief);
	
	/**
	 * 获取下一条插入的视频的auto_increment
	 * @return
	 */
	public int getVideoIncrementIndex();

}
