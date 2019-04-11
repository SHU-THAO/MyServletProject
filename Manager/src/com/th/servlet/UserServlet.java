package com.th.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.th.pojo.User;
import com.th.service.UserService;
import com.th.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	
	UserService us = new UserServiceImpl();
	
	Logger logger = Logger.getLogger(UserServlet.class);
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//������������ʽ
		req.setCharacterEncoding("UTF-8");
		//������Ӧ�����ʽ
		resp.setCharacterEncoding("UTF-8");
		//��ȡ������
		String oper = req.getParameter("oper");
		//������������
		if("login".equals(oper)){
			//���õ�½������
			checkUserLogin(req,resp);
		}else if("out".equals(oper)){
			//�����˳�����
			userOut(req,resp);
		}else if("pwd".equals(oper)){
			//�����޸�����ķ���
			userChangepwd(req,resp);
		}else if("show".equals(oper)){
			//������ʾ�����û��Ĺ���
			userShow(req,resp);
		}else if("reg".equals(oper)){
			//����ע�᷽��
			userReg(req,resp);
		}else{
			logger.debug("û���ҵ���Ӧ�Ĳ�������"+oper);
		}
	}
	//ע���û�
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡ������Ϣ
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		String ageS = req.getParameter("age");
		int age = ageS!=""?Integer.parseInt(ageS):-1;
		String birth = req.getParameter("birth");
		String bs[]=null;
		if(birth!=null){
			bs=birth.split("/");
			birth=bs[2]+"-"+bs[0]+"-"+bs[1];
		}
		User u = new User(0, uname, pwd, sex, age, birth);
		//����������Ϣ
		//����ҵ��㴦��
		int index = us.userRegService(u);
		//��Ӧ������
		if(index!=0){
			//��ȡsession����,�����
			HttpSession hs = req.getSession();
			hs.setAttribute("reg","true");
			//�ض���
			resp.sendRedirect("/mg/login.jsp");
		}
	}
	//��ʾ���е��û���Ϣ
	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��������
			//����service
		List<User> lu = us.userShowService();
		//�ж�
		if(lu!=null){
			//����ѯ���û����ݴ洢��request
			req.setAttribute("lu", lu);
			//����ת��
			req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
			return;
		}
	}
	//�û��޸�����
	private void userChangepwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡ����������
		String newPwd = req.getParameter("newPwd");
		//��ȡ�û���id��Ϣ�������ݿ�У��
		User u = (User)req.getSession().getAttribute("user");
		int uid=u.getUid();
		//��������
			//����service����
		int index = us.userChangepwdService(newPwd,uid);
		if(index>0){
			//��ȡsession����,�����
			HttpSession hs = req.getSession();
			hs.setAttribute("pwd","true");
			//�ض��򵽵�½ҳ��
			resp.sendRedirect("/mg/login.jsp");
		}
	}

	//�û��˳�
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//��ȡsession����
		HttpSession hs=req.getSession();
		//ǿ������session
		hs.invalidate();
		//�ض��򵽵�½ҳ��
		resp.sendRedirect("/mg/login.jsp");
	}

	//�����½
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//��ȡ������Ϣ
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		//����������Ϣ
			//��ȡservice����� us ��ȫ���ж���
			//У��
		User u=us.checkUserLoginService(uname, pwd);
		if(u!=null){
			//��ȡsession����
			HttpSession hs = req.getSession();
			//���û����ݴ���session��
			hs.setAttribute("user", u);
			//�ض������е�ַ���ĵ�һ��/��ʾ��������Ŀ¼
			resp.sendRedirect("/mg/main/Main.jsp");
			return;
		}else{
			//��ӱ�ʶ����request��
			req.setAttribute("flag",0);
			//����ת��,Ч�ʽϸߣ�����Ĺ���ʹ�õ���ͬһ��request������ת���е�ַ����һ��/��ʾ��Ŀ��Ŀ¼
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
		//��Ӧ������
			//ֱ����Ӧ
			//����ת��
			//�ض���
	}
	
}
