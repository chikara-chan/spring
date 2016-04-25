<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/plane.ico">
<title>PlayPlane网上超市</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/chikara-admin-index.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-left">
		<img src="${pageContext.request.contextPath}/images/logo.png">
		</div>
		<h3 class="navbar-left"><span class="label label-primary">管理员版</span></h3>
		<div class="navbar-right">
			<div class="user-zone">
				<a class="text-muted" href="${pageContext.request.contextPath}/user/logout">退出系统</a>
			</div>
		</div>
	</div>
</nav>

<div class="container">
 	<div class="row">
 		<div class="col-sm-2">
			<div class="left-part">
				<div class="title"><span class="glyphicon glyphicon-th-list text-muted"></span> 管理员菜单</div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/apply/list">审核管理</a></div>
				<div class="sub-title"><a class="active" href="${pageContext.request.contextPath}/market/listByAdmin">超市管理</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/customer/listByAdmin">用户管理</a></div>
				<div class="sub-title"><a href="">投诉管理</a></div>
				<div class="sub-title"><a href="">地址管理</a></div>
			</div>
		</div>
 		<div class="col-sm-10">
			<div class="right-part container-fluid">
				<div class="part-header">
 					<h3><b>审核管理</b></h3>
				</div>
				<div class="part-body">
				
				<table class="table">
					<thead>
						<tr>
							<th width="20%">账号创建时间</th>
							<th width="20%">上次登录时间</th>
							<th width="20%">姓名</th>
							<th width="20%">手机</th>
							<th width="20%">邮箱</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="market" items="${markets}">
							<tr>
								<td>
									<span class="strong"><fmt:formatDate pattern="yyyy/MM/dd" value="${market.createTime}" /></span>
									<span class="text-muted small"><fmt:formatDate pattern="HH:mm" value="${market.createTime}"/></span>
								</td>
								<td>
									<span class="strong"><fmt:formatDate pattern="yyyy/MM/dd" value="${market.lastTime}" /></span>
									<span class="text-muted small"><fmt:formatDate pattern="HH:mm" value="${market.lastTime}"/></span>
								</td>
								<td>${market.name}</td>
								<td>${market.phone}</td>
								<td>${market.email}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
			</div>
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
</body>
</html>