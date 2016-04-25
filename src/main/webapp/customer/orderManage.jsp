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
<link href="${pageContext.request.contextPath}/css/chikara-customer-orderManage.css" rel="stylesheet">
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
					<span class="glyphicon glyphicon-user"></span> <a id="popover" data-toggle="popover" class="text-muted" href="${pageContext.request.contextPath}/account/login.jsp">登录</a> /
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

<div class="container">
 	<div class="row">
 		<div class="col-sm-2">
			<div class="left-part">
				<div class="title"><span class="glyphicon glyphicon-align-left text-muted"></span> 我的订单</div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/customer/orderManage?key=all">全部订单</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/customer/orderManage?key=unhandled">待接单</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/customer/orderManage?key=unreceived">待收货</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/customer/orderManage?key=unreviewed">未评价</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/customer/orderManage?key=reviewed">已评价</a></div>
			</div>
		</div>
 		<div class="col-sm-10">
			<div class="right-part container-fluid">
				<div class="part-header">
 					<h3><b>全部订单</b></h3>
				</div>
				<div class="part-body">
				<table class="table">
					<thead>
						<tr>
							<th width="13%">订单编号</th>
							<th width="12%">下单时间</th>
							<th width="17%">订单内容</th>
							<th width="12%">支付金额</th>
							<th width="12%">收货地址</th>
							<th width="10%">支付方式</th>
							<th width="10%">送达时间</th>
							<th width="13%">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
							<tr>
								<td>
									${order.id}
								</td>
								<td>
									<span class="strong"><fmt:formatDate pattern="yyyy/MM/dd" value="${order.createTime}" /></span>
									<span class="text-muted small"><fmt:formatDate pattern="HH:mm" value="${order.createTime}"/></span>
								</td>
								<td>
									<c:forEach var="product" items="${order.products}">
										${product.name} / ${product.added}份<br>
									</c:forEach>
								</td>
								<td>￥<fmt:formatNumber value="${order.total}" pattern="#,##0.0#"/></td>
								<td>${order.address}</td>
								<td>${order.method}</td>
								<td>${order.expect}</td>
								<td>
									<a class="text-primary" href="${pageContext.request.contextPath}/customer/getOrder?id=${order.id}">订单详情</a>
								</td>
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
	<script>
		if('${key}'=='all')
			$('.left-part a').eq(0).addClass('active');
		if('${key}'=='unhandled')
			$('.left-part a').eq(1).addClass('active');
		if('${key}'=='unreceived')
			$('.left-part a').eq(2).addClass('active');
		if('${key}'=='unreviewed')
			$('.left-part a').eq(3).addClass('active');
		if('${key}'=='reviewed')
			$('.left-part a').eq(4).addClass('active');
	</script>
</body>
</html>