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
<link href="${pageContext.request.contextPath}/css/chikara-market-set.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-left">
		<img src="${pageContext.request.contextPath}/images/logo.png">
		</div>
		<h3 class="navbar-left"><span class="label label-primary">超市版</span></h3>
		<div class="navbar-right">
			<div class="user-zone">
				<span class="glyphicon glyphicon-user"></span> <a class="text-muted" href="${pageContext.request.contextPath}/market/set">${sessionInfo.name}</a> /
				<a class="text-muted" href="${pageContext.request.contextPath}/user/logout">注销</a>
			</div>
		</div>
	</div>
	</nav>
	
	<div class="container content">
		<div class="row">
			<div class="col-sm-3 left-part">
				<ul class="nav">
					 <li><a href="${pageContext.request.contextPath}/market/home"><span class="glyphicon glyphicon-home"></span> 返回主页</a></li>
  				     <li><a href="${pageContext.request.contextPath}/market/set"><span class="glyphicon glyphicon-bookmark"></span> 我的账户</a></li>
 				     <li><a class="active" href="${pageContext.request.contextPath}/market/setPwd.jsp"><span class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
  				     <li><a href="${pageContext.request.contextPath}/market/editEmailPage"><span class="glyphicon glyphicon-envelope"></span> 绑定邮箱</a></li>
  				     <li><a href="${pageContext.request.contextPath}/market/editPhonePage"><span class="glyphicon glyphicon-phone"></span> 绑定手机</a></li>
				</ul>
				<div class="blank"></div>
			</div>
			<div class="col-sm-9 right-part setEmail">
				<form class="form-horizontal" action="${pageContext.request.contextPath}/user/editPwd" method="post">
					<div class="form-group">
						<label for="inputEmail1" class="col-sm-2 control-label">当前密码</label>
						<div class="col-sm-5">
							<input type="password" name="oldPassword" class="form-control" id="inputEmail1" >
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail2" class="col-sm-2 control-label">新密码</label>
						<div class="col-sm-5">
							<input type="password" name="newPassword" class="form-control" id="inputEmail2" >
						</div>
					</div>	
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">确认密码</label>
						<div class="col-sm-5">
							<input type="password" name="re-password" class="form-control" id="inputEmail3" >
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
	