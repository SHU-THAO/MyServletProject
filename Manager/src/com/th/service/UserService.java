package com.th.service;

import java.util.List;

import com.th.pojo.User;

public interface UserService {
	//У���û���¼�����ز�ѯ������Ϣ
	User checkUserLoginService(String uname,String pwd);
	//�޸��û�����
	int userChangepwdService(String newPwd, int uid);
	//��ʾ�����û���Ϣ
	List<User> userShowService();
	//�û�ע��
	int userRegService(User u);
	
}
