<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<p class="headtitle" style="text-align:center;">超市入驻
</p>

<div class="form">

	<form action="${pageContext.request.contextPath}/apply/applySubmit" method="post">
		<input  name="unitId"  type="hidden" value="${apply.unitId}">
		<input class="user"  name="name"  placeholder="请输入姓名"><br>
		<span class="sex">性别：</span><label><input class="danxuan" name="gender" type="radio" value="男" checked><span class="male">男</span></label>
			<label><input  class="danxuanm" name="gender" type="radio" value="女"><span class="male">女</span></label><br>
		<input name="phone" placeholder="请输入手机号"><br>
		<input name="email" placeholder="请输入邮箱"><br>
		<!-- <span>验证码：<input name="captcha"> -->
		<input class="identity" name="captcha"  placeholder="请输入验证码">
		<img class="iden" id="captcha" src="${pageContext.request.contextPath}/code/captcha" style="cursor:pointer" onclick="changeCaptcha(this)"><br>
		
		<input  class="zhuce" type="submit" value="提交">
	</form>${msg}
	</div>
	</div>
	<div class="foot">
	<p>Copyright &copy;2015年 playplane.com All rights reserved. 浙ICP备15018103号
	</p>
	</div>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
</body>
</html>