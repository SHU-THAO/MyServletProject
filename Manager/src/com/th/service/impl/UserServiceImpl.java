package com.th.service.impl;



import java.util.List;

import org.apache.log4j.Logger;

import com.th.dao.UserDao;
import com.th.dao.impl.UserDaoImpl;
import com.th.pojo.User;
import com.th.service.UserService;

public class UserServiceImpl implements UserService{
	/**
	 * 声明Dao层对象
	 * 用户登录
	 */
	UserDao ud = new UserDaoImpl();
	//声明日志对象
	Logger logger = Logger.getLogger(UserServiceImpl.class); 
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		// TODO Auto-generated method stub
		logger.debug(uname+"发起登录请求");
		User u = ud.checkUserLoginDao(uname, pwd);
		if(u!=null){
			logger.debug(uname+"登陆成功");
		}else{
			logger.debug("登陆失败");
		}
		return u;
	}
	
	@Override
	public int userChangepwdService(String newPwd, int uid) {
		logger.debug(uid+":发起密码修改请求");
		int index=ud.userChangeDao(newPwd,uid);
		if(index>0){
			logger.debug(uid+"密码修改成功");
		}else{
			logger.debug(uid+"密码修改失败");
		}
		return index;
	}
	
	@Override
	public List<User> userShowService() {
		List<User> lu = ud.userShowDao();
		logger.debug("显示所有用户信息");
		return lu;
	}
	//用户注册功能
	@Override
	public int userRegService(User u) {
		int index = ud.userRegDao(u);
		if(index>0){
			logger.debug(u.getUname()+"注册成功");
		}else{
			logger.debug(u.getUname()+"注册失败");
		}
		return ud.userRegDao(u);
	}
}
