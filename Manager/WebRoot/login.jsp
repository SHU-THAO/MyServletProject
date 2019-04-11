<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>      
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
      
     
    <div class="loginbox loginbox1">
    <form action="user" method="post" >
    	<input type="hidden" name="oper" value="login"/>
        <ul>
        <%
    	//声明JAVA代码块进行错误提示、逻辑校验、密码修改提示
    	Object obj = request.getAttribute("flag");
        Object obj2 = session.getAttribute("pwd");
        Object obj3 = session.getAttribute("reg");
        if(obj3!=null){
        %>
    		<li><input name="uname" type="text" placeholder="注册成功" class="loginuser" /></li>	
    	<%
    		//获取session对象
    		HttpSession hs=request.getSession();
    		//强制销毁session
    		hs.invalidate();
    	}
    	if(obj2!=null){
    	%>
    		<li><input name="uname" type="text" placeholder="密码修改成功" class="loginuser" /></li>	
    	<%
    		//获取session对象
    		HttpSession hs=request.getSession();
    		//强制销毁session
    		hs.invalidate();
    	}else if(obj2==null&&obj!=null){
    	%>
    		<li><input name="uname" type="text" placeholder="用户名或密码错误" class="loginuser" /></li>	
    	<%}else if(obj2==null&&obj==null&&obj3==null){
    	%>
    		<li><input name="uname" type="text" placeholder="用户名" class="loginuser" /></li>
    	<%}%>
	    
	    
	    <li><input name="pwd" type="text" placeholder="密码" class="loginpwd"/></li>
	    <li class="yzm">
	    <span><input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite> 
	    </li>
	    <li><input name="" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location='main.html'"  /><label><a href="user/reg.jsp">注册</a></label><label><a href="#">忘记密码</a></label></li>
	    </ul>
    </form>

    
    
    </div>
    
    </div>
    
    
    <div class="loginbm">Web学习 <a href="http://www.uimaker.com">uimaker.com</a>  学习交流</div>
	
    
</body>

</html>
