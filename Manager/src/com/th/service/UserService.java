package com.th.service;

import java.util.List;

import com.th.pojo.User;

public interface UserService {
	//校验用户登录，返回查询到的信息
	User checkUserLoginService(String uname,String pwd);
	//修改用户密码
	int userChangepwdService(String newPwd, int uid);
	//显示所有用户信息
	List<User> userShowService();
	//用户注册
	int userRegService(User u);
	
}
