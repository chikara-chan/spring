<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/plane.ico">
<title>PlayPlane网上超市</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/reg.css" rel="stylesheet">
</head>
<body>
<div class="signup container">

<p class="headtitle">登 录
</p><a class="denglu" href="${pageContext.request.contextPath}/account/reg.jsp"><span>没有账号</span>点此注册</a>


<div class="form">

<form name="formset" action="${pageContext.request.contextPath}/user/login"  method="post">
		<c:if test="${!empty msg}">
        	<div class="alert alert-danger" id="hbox" onblur="checkna()"><span class="glyphicon glyphicon-remove text-danger"></span>${msg}</div>
		</c:if>
		<input class="user" name="username" value="${user.username}" placeholder="请输入账号" onblur="checkphone()" required/><br><br>
		<input  class="password" name="password" value="${user.password}" type="password" placeholder="请填写密码" onBlur="checkpsd1()" required/><br>
		<br>
		<input class="identity" name="captcha"  placeholder="请输入验证码">
		<img class="iden" id="captcha" src="${pageContext.request.contextPath}/code/captcha" style="cursor:pointer" onclick="changeCaptcha(this)"><br>
		<input class="zhuce" type="submit" value="登录"> 
		
	</form>
	
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