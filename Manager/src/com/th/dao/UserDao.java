package com.th.dao;

import java.util.List;

import com.th.pojo.User;

public interface UserDao {
	/**
	 * �����û����������ѯ�û���Ϣ
	 */
	User checkUserLoginDao(String uname,String pwd);
	/**
	 * �����û�id�޸��û�����
	 */
	int userChangeDao(String newPwd, int uid);
	/**
	 * �����ݿ��е������е��û���Ϣ
	 */
	List<User> userShowDao();
	/*
	 * �û�ע��
	 */
	int userRegDao(User u);
}
