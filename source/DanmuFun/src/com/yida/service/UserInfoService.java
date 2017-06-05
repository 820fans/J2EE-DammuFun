package com.yida.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yida.common.SearchUpperItem;
import com.yida.common.UpperItem;
import com.yida.dao.IUserInfoDao;
import com.yida.entity.Concern;
import com.yida.entity.UserInfo;

@Service
public class UserInfoService {

	@Resource
	IUserInfoDao dao;
	
	public UserInfo CheckLogin(){
		
		return dao.IsLogin();
	}
	
	
	/**
	 * 获取已登录用户的用户名
	 * @return
	 */
	public String GetUserAccount(int id){
		
		return dao.GetUserName(id);
	}
	
	/**
	 * 尝试登录
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public UserInfo doLogin(String account, String password) throws Exception {
		
		if (account == null || "".equals(account)) {
			throw new Exception("账户不能为空");
		}
		if (password == null || "".equals(password)) {
			throw new Exception("密码不能为空");
		}		
		
		UserInfo user = dao.selectByAccount(account);
		if (user == null) {
			throw new Exception("账户不存在");
		}
		
		if (!user.getPassword().equals(password)) {
			throw new Exception("密码错误");
		}
		
		return user ;
	}
	
	/**
	 * 尝试注册
	 * @param account
	 * @param password
	 * @param introduce
	 * @throws Exception
	 */
	public boolean doRegister(String account, String password, String introduce) throws Exception {

		if (account == null || "".equals(account)) {
			throw new Exception("账户不能为空");
		}
		if (password == null || "".equals(password)) {
			throw new Exception("密码不能为空");
		}
		
		UserInfo user=dao.selectByAccount(account);
		if(user!=null){
			throw new Exception("账户已存在");
		}
		
		String header = null;
		if(introduce.equals("")) introduce=null;
		UserInfo newUser=new UserInfo(account,password,introduce,header);
		int createAccount=dao.createUser(newUser);
		
		//检验已经插入数据库
//		UserInfo user1=dao.selectByAccount(account);
//		if(user1==null){
//			throw new Exception("插入数据库出错");
//		}
//		
		return true;
	}
	
	/**
	 * 更新头像
	 * @param id 
	 * @param string
	 */
	public void updateHeader(String str, int userId) {
		if(str!=null){
			dao.updateHeader(str,userId);
		}
	}
	
	/**
	 * 获取用户头像
	 * @param id
	 * @return
	 */
	public String getHeaderById(int id) {
		// TODO Auto-generated method stub
		if(id>0){
			return dao.getHeaderById(id);
		}
		return null;
	}
	
	/**
	 * 检测头像是否真正存在
	 * @param header 
	 * @return
	 */
	public boolean checkHeader(String header) {
		
		File file=new File(header);
		if(!file.exists()){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 获取阿婆主的信息
	 * @param id
	 * @return
	 */
	public UpperItem getUpperInfo(int id) {
		return dao.getUpperInfo(id);
	}
	
	/**
	 * 获取关注信息,同时用于检查是否关注过
	 * @param user_id
	 * @param friend_id
	 * @return
	 */
	public Concern getConcernInfo(int user_id, int friend_id) {

		return dao.getConcernInfo(user_id,friend_id);
	}
	
	/**
	 * 关注某人
	 * @param user_id
	 * @param friend_id
	 */
	public void concernHim(int user_id, int friend_id) {
		dao.concernHim(user_id,friend_id);
	}
	
	/**
	 * 取消关注某人
	 * @param user_id
	 * @param friend_id
	 */
	public void unconcernHim(int user_id, int friend_id) {
		dao.unconcernHim(user_id,friend_id);
	}
	
	/**
	 * 搜索阿婆主
	 * @param orderType 排序方式
	 * @param value 阿婆主姓名、或者简介 关键字
	 * @return
	 */
	public List<SearchUpperItem> getSearchUpperItem(String orderType,
			String value) {
		return dao.getSearchUpperItem(orderType,value);
	}
	
	/**
	 * 获取关注过的用户的信息
	 * @param id
	 * @return
	 */
	public List<SearchUpperItem> getConcernedUpperItem(int user_id) {
		return dao.getConcernedUpperInfo(user_id);
	}
	
	/**
	 * 编辑用户的密码
	 * @param originalPsw
	 * @param newPsw
	 */
	public String editPassword(int id,String originalPsw, String newPsw) {
		
		UserInfo user=dao.checkPassword(id,originalPsw);
		
		//密码错误
		if(user==null){
			return "wrongPsw";
		}
		
		//密码太弱
		if(newPsw.length()<6){
			return "weak";
		}
		
		dao.editPassword(id,newPsw);
		
		return "success";
	}
	
	/**
	 * 修改简介
	 * @param id
	 * @param introduce
	 */
	public void editIntroduce(int id, String introduce) {
		
		dao.editIntroduce(id,introduce);
	}

}
