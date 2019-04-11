package com.th.service.impl;



import java.util.List;

import org.apache.log4j.Logger;

import com.th.dao.UserDao;
import com.th.dao.impl.UserDaoImpl;
import com.th.pojo.User;
import com.th.service.UserService;

public class UserServiceImpl implements UserService{
	/**
	 * ����Dao�����
	 * �û���¼
	 */
	UserDao ud = new UserDaoImpl();
	//������־����
	Logger logger = Logger.getLogger(UserServiceImpl.class); 
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		// TODO Auto-generated method stub
		logger.debug(uname+"�����¼����");
		User u = ud.checkUserLoginDao(uname, pwd);
		if(u!=null){
			logger.debug(uname+"��½�ɹ�");
		}else{
			logger.debug("��½ʧ��");
		}
		return u;
	}
	
	@Override
	public int userChangepwdService(String newPwd, int uid) {
		logger.debug(uid+":���������޸�����");
		int index=ud.userChangeDao(newPwd,uid);
		if(index>0){
			logger.debug(uid+"�����޸ĳɹ�");
		}else{
			logger.debug(uid+"�����޸�ʧ��");
		}
		return index;
	}
	
	@Override
	public List<User> userShowService() {
		List<User> lu = ud.userShowDao();
		logger.debug("��ʾ�����û���Ϣ");
		return lu;
	}
	//�û�ע�Ṧ��
	@Override
	public int userRegService(User u) {
		int index = ud.userRegDao(u);
		if(index>0){
			logger.debug(u.getUname()+"ע��ɹ�");
		}else{
			logger.debug(u.getUname()+"ע��ʧ��");
		}
		return ud.userRegDao(u);
	}
}
