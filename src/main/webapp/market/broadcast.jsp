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
<link href="${pageContext.request.contextPath}/css/chikara-market-index.css" rel="stylesheet">
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

<div class="container">
 	<div class="row">
 		<div class="col-sm-2">
			<div class="left-part">
				<div class="title"><span class="glyphicon glyphicon-bookmark text-muted"></span> 店铺信息</div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/home">店铺动态</a></div>
				<div class="sub-title"><a class="active" href="${pageContext.request.contextPath}/market/broadcast">店铺公告</a></div>
				<div class="title"><span class="glyphicon glyphicon-align-left text-muted"></span> 订单管理</div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/orderManage">全部订单</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/unhandledOrder">待接单</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/unreceivedOrder">待收货</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/finishedOrder">已完成</a></div>
				<div class="title"><span class="glyphicon glyphicon-tags text-muted"></span> 商品管理</div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/product/listByMarket">商品列表</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/addProduct.jsp">商品上架</a></div>
				<div class="title"><span class="glyphicon glyphicon-eye-open text-muted"></span> 评价管理</div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/listReview">全部评价</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/listUnrepliedReview">未回复</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/listRepliedReview">已回复</a></div>
			</div>
		</div>
 		<div class="col-sm-10">
			<div class="right-part container-fluid">
				<div class="part-header">
 					<h3><b>店铺公告</b></h3>
				</div>
				<div class="part-body">
					<form action="${pageContext.request.contextPath}/market/editBroadcast">
						<div class="form-group">
							<textarea name="broadcast" class="form-control" placeholder="说点什么吧。。。">${broadcast}</textarea>
						</div>
						<button class="btn btn-primary" type="submit">发布</button><br>
     				 	<c:if test="${!empty msg}">
     				 		<span class="text-success text-center" style="position: relative;top:10px;left:10px;"><span class="glyphicon glyphicon-ok"></span>${msg}</span>
     				 	</c:if>
					</form>
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