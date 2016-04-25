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
<link href="${pageContext.request.contextPath}/css/chikara-customer-index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/XM-order.css" rel="stylesheet">
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
	
	<div id="order_content">
		<div class="order_table">
			<table class="table table-striped" style="margin:auto">
				<tr>
					<td colspan="4" style="padding-left:30px">卖家信息</td>
				</tr>
				<tr>
					<td style="padding-left:30px">昵称：${order.market.name}</td>
					<td colspan="2" style="padding-left:35px" >地址：${order.market.address}</td>
					<td>手机：${order.market.phone}</td>
				</tr>
				<tr>
					<td colspan="4" style="padding-left:30px">订单信息</td>
				</tr>
				<tr>
					<td colspan="2" style="padding-left:30px">订单编号：${order.id}</td>
					<td colspan="2" style="padding-left:35px">下单时间：${order.createTime}</td>
				</tr>
				<tr>
					<td style="padding-left:30px">商品详情</td>
    				<td style="padding-left:50px">名称</td>
    				 <td style="padding-left:50px">价格</td>
     				 <td style="padding-left:60px">数量</td>
     			</tr>
     			 <c:forEach var="product" items="${order.products}">
					<tr style="background-color:#fff !important">
						<td style="padding-left:30px"><img   width="80" height="80" src="${product.pic}"></td>
						<td style="padding-left:30px;padding-top:35px;">${product.name}</td>
						<td style="padding-left:40px;padding-top:35px;">￥${product.price}</td>
						<td style="padding-left:70px;padding-top:35px;">${product.added}</td>
					</tr>
	
				</c:forEach>
				<tr>
					<td colspan="4" style="padding-left:865px;">总计：<span style="color:#FD4B64;font-size:18px;"><fmt:formatNumber value="${order.total}" pattern="#,##0.0#"/></span><br></td>
				</tr>
				<tr>
	           		 <td colspan="2" style="padding-left:30px">收货地址：${order.address}</td>
	          		  <td>要求送达时间：${order.expect}</td>
              		  <td>付款方式：${order.method}</td>
	           </tr>
	           <tr>
	             <td colspan="4" style="padding-left:30px;">	
		       		<c:choose> 
 			       		<c:when test="${order.status=='待接单'}">   
		       				<button type="button" class="btn btn-danger" style="margin-left:850px;" onclick="location='${pageContext.request.contextPath}/customer/cancelOrder?id='+${order.id}" style="margin-left:950px">取消订单</button>
           				</c:when> 
   	       				<c:when test="${order.status=='已取消'}">   
	       					您已取消订单
             			  </c:when> 
             			  <c:when test="${order.status=='已拒接'}">   
		       				商家拒绝接单
            			   </c:when> 
            			   <c:when test="${order.status=='已接单'}">   
	       					<button type="button" class="btn btn-primary" style="margin-left:850px;" onclick="location='${pageContext.request.contextPath}/customer/finishOrder?id='+${order.id}"style="margin-left:950px">确认收货</button>
            			   </c:when> 
           			    <c:when test="${order.status=='已收货'}">   
	       					<form action="${pageContext.request.contextPath}/customer/reviewOrder">
	       						<input type="hidden" name="id" value="${order.id}">
	       						<textarea name="review" rows="3" cols="50" placeholder="说点什么吧" ></textarea>
	       						<input class="btn btn-primary" type="submit" value="评价" style="margin-top:-20px;margin-left:15px">
	       					</form>
             			  </c:when> 
            			   <c:when test="${order.status=='已评价'}">   
	       					您已评价!!!
         			      </c:when>
       				</c:choose>
       			  </td>
				</tr>
			</table>
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