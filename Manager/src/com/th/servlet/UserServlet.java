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
		//设置请求编码格式
		req.setCharacterEncoding("UTF-8");
		//设置响应编码格式
		resp.setCharacterEncoding("UTF-8");
		//获取操作符
		String oper = req.getParameter("oper");
		//调用请求处理方法
		if("login".equals(oper)){
			//调用登陆处理方法
			checkUserLogin(req,resp);
		}else if("out".equals(oper)){
			//调用退出方法
			userOut(req,resp);
		}else if("pwd".equals(oper)){
			//调用修改密码的方法
			userChangepwd(req,resp);
		}else if("show".equals(oper)){
			//调用显示所有用户的功能
			userShow(req,resp);
		}else if("reg".equals(oper)){
			//调用注册方法
			userReg(req,resp);
		}else{
			logger.debug("没有找到对应的操作符："+oper);
		}
	}
	//注册用户
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求信息
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
		//处理请求信息
		//调用业务层处理
		int index = us.userRegService(u);
		//响应处理结果
		if(index!=0){
			//获取session对象,做标记
			HttpSession hs = req.getSession();
			hs.setAttribute("reg","true");
			//重定向
			resp.sendRedirect("/mg/login.jsp");
		}
	}
	//显示所有的用户信息
	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//处理请求
			//调用service
		List<User> lu = us.userShowService();
		//判断
		if(lu!=null){
			//将查询的用户数据存储到request
			req.setAttribute("lu", lu);
			//请求转发
			req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
			return;
		}
	}
	//用户修改密码
	private void userChangepwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取新密码数据
		String newPwd = req.getParameter("newPwd");
		//获取用户的id信息用于数据库校验
		User u = (User)req.getSession().getAttribute("user");
		int uid=u.getUid();
		//处理请求
			//调用service处理
		int index = us.userChangepwdService(newPwd,uid);
		if(index>0){
			//获取session对象,做标记
			HttpSession hs = req.getSession();
			hs.setAttribute("pwd","true");
			//重定向到登陆页面
			resp.sendRedirect("/mg/login.jsp");
		}
	}

	//用户退出
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取session对象
		HttpSession hs=req.getSession();
		//强制销毁session
		hs.invalidate();
		//重定向到登陆页面
		resp.sendRedirect("/mg/login.jsp");
	}

	//处理登陆
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		//获取请求信息
		String uname=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		//处理请求信息
			//获取service层对象 us 在全局中定义
			//校验
		User u=us.checkUserLoginService(uname, pwd);
		if(u!=null){
			//获取session对象
			HttpSession hs = req.getSession();
			//将用户数据存在session中
			hs.setAttribute("user", u);
			//重定向，其中地址栏的第一个/表示服务器根目录
			resp.sendRedirect("/mg/main/Main.jsp");
			return;
		}else{
			//添加标识符到request中
			req.setAttribute("flag",0);
			//请求转发,效率较高，定向的过程使用的是同一个request，请求转发中地址栏第一个/表示项目根目录
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return;
		}
		//响应处理结果
			//直接响应
			//请求转发
			//重定向
	}
	
}
