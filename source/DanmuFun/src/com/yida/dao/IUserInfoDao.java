package com.yida.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yida.common.SearchUpperItem;
import com.yida.common.UpperItem;
import com.yida.entity.Concern;
import com.yida.entity.UserInfo;

@Repository
public interface IUserInfoDao {
	
	/**
	 * 判断用户是否已经登录
	 * @return
	 */
	public UserInfo IsLogin();

	/**
	 * 获取用户名
	 * @return
	 */
	public String GetUserName(@Param("userId")int userId);
	
	/**
	 * 账号判重
	 * @param account
	 * @return
	 */
	public UserInfo selectByAccount(@Param("account")String account);
	
	/**
	 * 新建用户
	 * @param newUser
	 * @return
	 */
	public int createUser(@Param("newUser")UserInfo newUser);
	
	/**
	 * 更新用户头像
	 * @param str
	 * @param userId
	 */
	public void updateHeader(@Param("header")String str,@Param("userId") int userId);
	
	/**
	 * 获取用户头像
	 * @param userId
	 * @return
	 */
	public String getHeaderById(@Param("userId")int userId);
	
	/**
	 * 视频详情页面，获取阿婆主的信息
	 * @param userId
	 * @return
	 */
	public UpperItem getUpperInfo(@Param("userId")int userId);
	
	/**
	 * 获取关注信息
	 * @param user_id
	 * @param friend_id
	 * @return
	 */
	public Concern getConcernInfo(@Param("user_id")int user_id,@Param("friend_id") int friend_id);
	
	/**
	 * 关注用户
	 * @param user_id
	 * @param friend_id
	 */
	public void concernHim(@Param("user_id")int user_id,@Param("friend_id")int friend_id);
	
	/**
	 * 取消关注用户
	 * @param user_id
	 * @param friend_id
	 */
	public void unconcernHim(@Param("user_id")int user_id, @Param("friend_id")int friend_id);
	
	/**
	 * 寻找阿婆主
	 * @param orderType
	 * @param value
	 * @return
	 */
	public List<SearchUpperItem> getSearchUpperItem(@Param("orderType")String orderType,
			@Param("value")String value);
	
	/**
	 * 获取关注过的阿婆主的信息
	 * @param user_id
	 * @return
	 */
	public List<SearchUpperItem> getConcernedUpperInfo(@Param("user_id") int user_id);
	
	/**
	 * 检查用户密码是否正确
	 * @param id
	 * @param originalPsw
	 * @return
	 */
	public UserInfo checkPassword(@Param("id")int id,@Param("password") String originalPsw);
	
	/**
	 * 修改用户密码
	 * @param id
	 * @param newPsw
	 */
	public void editPassword(@Param("id")int id,@Param("password") String newPsw);
	
	/**
	 * 修改用户简介
	 * @param id
	 * @param introduce
	 * @return
	 */
	public void editIntroduce(@Param("id")int id, @Param("introduce")String introduce);

}
