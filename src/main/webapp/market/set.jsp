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
  				     <li><a class="active" href="${pageContext.request.contextPath}/market/set"><span class="glyphicon glyphicon-bookmark"></span> 我的账户</a></li>
 				     <li><a href="${pageContext.request.contextPath}/market/setPwd.jsp"><span class="glyphicon glyphicon-edit"></span> 修改密码</a></li>
  				     <li><a href="${pageContext.request.contextPath}/market/editEmailPage"><span class="glyphicon glyphicon-envelope"></span> 绑定邮箱</a></li>
  				     <li><a href="${pageContext.request.contextPath}/market/editPhonePage"><span class="glyphicon glyphicon-phone"></span> 绑定手机</a></li>
				</ul>
				<div class="blank"></div>
			</div>
			<div class="col-sm-9 right-part set">
				<span class="h3">Welcome Back，<b>${market.name }</b></span>
			    <a id="popover" class="text-primary" href="javascript:void(0)" data-toggle="popover">修改</a>
			    <br><br>
				手机：<c:if test="${!empty market.phone}">${market.phone}</c:if><c:if test="${empty market.phone}">尚未绑定</c:if><br>
				邮箱：<c:if test="${!empty market.email}">${market.email}</c:if><c:if test="${empty market.email}">尚未绑定</c:if><br>
				创建时间：<fmt:formatDate value="${market.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
				上次登录时间：<fmt:formatDate value="${market.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/><br>
			</div>
		</div>
	</div>
	<footer class="text-center">
		<h4>
			Copyright &copy;2015年 playplane.com All rights reserved.
			<a href="http://www.miitbeian.gov.cn/" class="text-muted" target="_blank">浙ICP备15018103号</a>
		</h4>
	</footer><script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
	<script>
	$(function () {
		  $('#popover').popover({
			  title:'修改昵称',
			  content:'<form class="form-inline" action="${pageContext.request.contextPath}/market/editName">'+
			  			  '<div class="form-group">'+
			  				  '<input id="input-text" class="form-control" style="width:100px;height:30px;margin-right:10px" type="text" name="name">'+
			  				  '<button id="input-submit" class="btn btn-default btn-sm" type="submit">提交</button>'+
			  			  '</div>'+
			  		  '<form>',
			  html:true,
			  placement:'bottom',
			  trigger:'manual',
			  template:'<div class="popover" role="tooltip">'+
			  				'<div class="arrow"></div>'+
			 				'<h3 class="popover-title"></h3>'+
			 			 	'<div class="popover-content"></div>'+
			  		   '</div>'
		  });
		  $('#popover').click(function(){
			  $('#popover').popover('show');
			  $(document).unbind('click').bind('click',function(){
			  	if(!$(event.target).is('[role="tooltip"],[role="tooltip"] *,#popover'))
					$('#popover').popover('hide');
			  });
			  $('#input-submit').click(function(){
					if($('#input-text').val()=="")
						return false;
			  });
		  });
			 
	});
	</script>
</body>
</html>
	