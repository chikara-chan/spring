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
<link href="${pageContext.request.contextPath}/css/chikara-market-orderManage.css" rel="stylesheet">
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
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/broadcast">店铺公告</a></div>
				<div class="title"><span class="glyphicon glyphicon-align-left text-muted"></span> 订单管理</div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/orderManage">全部订单</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/unhandledOrder">待接单</a></div>
				<div class="sub-title"><a class="active" href="${pageContext.request.contextPath}/market/unreceivedOrder">待收货</a></div>
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
 					<h3><b>待收货</b></h3>
				</div>
				<div class="part-body">
				<table class="table">
					<thead>
						<tr>
							<th width="12%">订单编号</th>
							<th width="12%">下单时间</th>
							<th width="16%">订单内容</th>
							<th width="8%">支付金额</th>
							<th width="13%">收货地址</th>
							<th width="11%">支付方式</th>
							<th width="11%">送达时间</th>
							<th width="17%">状态</th>
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
									<c:choose> 
 		  								<c:when test="${order.status=='待接单'}">
 		  								  <button class="btn btn-primary btn-sm" onclick="location='${pageContext.request.contextPath}/market/acceptOrder?id='+${order.id}">接单</button>&nbsp;&nbsp;
			 						  	  <button class="btn btn-danger btn-sm" onclick="location='${pageContext.request.contextPath}/market/cancelOrder?id='+${order.id}">拒接</button>
									    </c:when> 
   									    <c:when test="${order.status=='已取消'}">   
									  		订单已取消
      							 	    </c:when> 
      								    <c:when test="${order.status=='已拒接'}">   
								  			订单被拒绝
      							  	    </c:when> 
      							  	    <c:when test="${order.status=='已接单'}">   
								  			<span class="label label-warning" style="font-size: 14px;">待收货</span>
      							    	</c:when> 
       							 	    <c:when test="${order.status=='已收货'}">   
								 	 		已完成
      						    		</c:when> 
      						  		    <c:when test="${order.status=='已评价'}">   
								 	 		已评价
     						  		     </c:when>
							  		</c:choose>
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
</body>
</html>