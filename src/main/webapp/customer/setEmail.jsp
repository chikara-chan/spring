<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/plane.ico">
<title>PlayPlane网上超市</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/chikara-customer-set.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="navbar-left">
			<img src="${pageContext.request.contextPath}/images/logo.png">
			</div>
			<ul class="nav navbar-nav navbar-left h4">
				<li><a href="${pageContext.request.contextPath}/index.jsp">主页</a></li>
				<li><a href="${pageContext.request.contextPath}/customer/orderManage?key=all">我的订单</a></li>
				<li><a href="${pageContext.request.contextPath}/customer/chat.jsp">动弹聊天室</a></li>
				<li><a href="http://www.playplane.com">加入我们</a></li>
			</ul>
			<div class="navbar-right">
				<c:if test="${empty sessionInfo}">
					<div class="user-zone">
						<span class="glyphicon glyphicon-user"></span> <a class="text-muted" href="${pageContext.request.contextPath}/account/login.jsp">登录</a> /
						<a class="text-muted" href="${pageContext.request.contextPath}/account/reg.jsp">注册</a>
					</div>
				</c:if>
				<c:if test="${!empty sessionInfo}">
					<div class="user-zone">
						<span class="glyphicon glyphicon-user"></span> <a class="text-muted" href="${pageContext.request.contextPath}/customer/set">${sessionInfo.name}</a> /
						<a class="text-muted" href="${pageContext.request.contextPath}/user/logout">注销</a>
					</div>
				</c:if>
			</div>
		</div>
	</nav>
	
	<div class="container content">
		<div class="row">
			<div class="col-sm-3 left-part">
				<ul class="nav">
  				     <li><a href="${pageContext.request.contextPath}/customer/set"><span class="glyphicon glyphicon-bookmark"></span> 我的账户</a></li>
 				     <li><a href="${pageContext.request.contextPath}/customer/setPwd.jsp"><span class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
  				     <li><a class="active" href="${pageContext.request.contextPath}/customer/editEmailPage"><span class="glyphicon glyphicon-envelope"></span> 绑定邮箱</a></li>
  				     <li><a href="${pageContext.request.contextPath}/customer/editPhonePage"><span class="glyphicon glyphicon-phone"></span> 绑定手机</a></li>
  				     <li><a href="${pageContext.request.contextPath}/customer/getDelivery"><span class="glyphicon glyphicon-th-list"></span> 收货管理</a></li>
				</ul>
				<div class="blank"></div>
			</div>
			<div class="col-sm-9 right-part setEmail">
				<form class="form-horizontal" action="${pageContext.request.contextPath}/customer/editEmail" method="post">
					<c:if test="${!empty email}">
						<div class="form-group">
							<label for="inputEmail1" class="col-sm-2 control-label">旧邮箱</label>
							<div class="col-sm-5">
								<input type="email" name="oldEmail" class="form-control" id="inputEmail1" >
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label for="inputEmail2" class="col-sm-2 control-label">新邮箱</label>
						<div class="col-sm-5">
							<input type="email" name="newEmail" class="form-control" id="inputEmail2" >
						</div>
					</div>	
					<div class="form-group">
						<label for="inputCaptcha" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" name="captcha" class="form-control" id="inputCaptcha">
						</div>
						<div class=" col-sm-3">
							<img id="captcha" src="${pageContext.request.contextPath}/code/captcha" style="cursor:pointer" onclick="changeCaptcha(this)">
						</div>
					</div>	
					<div class="form-group">
   						 <div class="col-sm-offset-2 col-sm-10">
     				 		 <button type="submit" class="btn btn-primary">提交</button>
   						 </div>
 					</div>
 					<div style="margin-top:30px;">
   						 <div class="col-sm-offset-3 col-sm-3">
   						 	 <c:if test="${!empty msgSuccess}">
     				 		 	<div class="alert alert-success text-center" role="alert"><span class="glyphicon glyphicon-ok"></span>${msgSuccess}</div>
     				 		 </c:if>
   						 	 <c:if test="${!empty msgError}">
     				 			 <div class="alert alert-danger text-center" role="alert"><span class="glyphicon glyphicon-remove"></span>${msgError}</div>
     				 		 </c:if>
     				 		 <c:if test="${!empty msg}">
     				 			 <div class="alert alert-danger text-center" role="alert"><span class="glyphicon glyphicon-remove"></span>${msg}</div>
     				 		 </c:if>
   						 </div>
 					</div>
				</form>
			</div>
		</div>
	</div>
	<footer class="text-center">
		<h4>
			Copyright &copy;2015年 playplane.com All rights reserved.
			<a href="http://www.miitbeian.gov.cn/" class="text-muted" target="_blank">浙ICP备15018103号</a>
		</h4>
	</footer>

	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
</body>
</html>
	