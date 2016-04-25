<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/plane.ico">
<title>Plane网上超市</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/reg.css" rel="stylesheet">
</head>
<body>
<div class="signup container">
<p class="headtitle">注册新的账号
</p><a class="denglu" href="${pageContext.request.contextPath}/account/login.jsp"><span>已有账号</span>在此登录</a>
<div class="form">
	 <form name="formset" action="${pageContext.request.contextPath}/user/reg"  method="post">
		<input type="text"  class="username" name="username" value="${user.username}" placeholder="请填写账号"  onblur="checkphone()" required/><br>
		<span class="tips" id="divname" style="margin-left:40px;">字母开头且不小于6位</span>
		
		<input type="text" class="name" name="name" value="${user.name}" placeholder="个性昵称" onblur="checkna()" required/><br>
		<span class="tips" id="divname2" style="margin-left:40px;">请输入个性昵称</span>
		
		<input  class="password" name="password" type="password" placeholder="请填写密码" value="${user.password}" onBlur="checkpsd1()" required/><br>
		<span class="tips" id="divpassword1" style="margin-left:40px;">密码长度不得小于6位</span>
		
		<input  class="re-password" name="rePassword" type="password"placeholder="重复密码" value="${user.password}" onBlur="checkpsd2()" required/><br>
		<span class="tips" id="divpassword2" style="margin-left:40px;">两次密码需要相同</span><br>
		
		<input type="text" class="identity" name="captcha"  placeholder="请输入验证码">
		<img class="iden" id="captcha" src="${pageContext.request.contextPath}/code/captcha" style="cursor:pointer" onclick="changeCaptcha(this)"><br>
		<input class="zhuce" type="submit" value="注 册"  onclick="return sub()"/> 
	</form>
	<div class="icode" style="margin-bottom: 10px">${msg}</div>
	</div> 
	</div>
	<div class="foot">
	<p>Copyright &copy;2015年 playplane.com All rights reserved. 浙ICP备15018103号
	</p>
	</div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
	<script src="${pageContext.request.contextPath}/js/formcheck.js"></script>
</body>
</html>