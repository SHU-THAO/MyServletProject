package com.th.dao;

import java.util.List;

import com.th.pojo.User;

public interface UserDao {
	/**
	 * 根据用户名和密码查询用户信息
	 */
	User checkUserLoginDao(String uname,String pwd);
	/**
	 * 根据用户id修改用户密码
	 */
	int userChangeDao(String newPwd, int uid);
	/**
	 * 从数据库中调用所有的用户信息
	 */
	List<User> userShowDao();
	/*
	 * 用户注册
	 */
	int userRegDao(User u);
}
