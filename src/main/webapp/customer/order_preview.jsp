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
<link href="${pageContext.request.contextPath}/css/XM-order_preview.css" rel="stylesheet">
<script>
	//商品对象数组
	var data=[];
</script>
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

	<div class="order_products">
	<table class="table" style="width:900px;margin:auto;">
	<tbody>
     <tr style="height:60px"> <td colspan="4" ></td></tr>
	<tr style="height:40px;text-align:auto">
	<td style="color:#999;padding-left:20px">商品照片</td>
	<td style="color:#999;padding-left:20px">商品名称</td>
	<td style="color:#999;padding-left:20px">价格</td>
	<td style="color:#999;padding-left:0px">数量</td>
	</tr>
	<c:forEach var="product" items="${productlist}">
	<tr>
	
	<td><img  width="60" height="60" src="${product.pic}"> </td>
	<td style="padding-top:27px;">${product.name}</td>
	<td style="padding-top:27px;">￥${product.price}</td>
	<td style="padding-top:27px;">${product.added}<br></td>
	</tr>
	<script>
		 data.push({id:'${product.id}',name:'${product.name}',price:'${product.price}',number:'${product.number}',added:'${product.added}'});
	</script>
	
	</c:forEach>
	</tbody>
	<tfoot>
	<tr>
	
	<td colspan="4">
	<span  style="margin-left:700px;color:#999">总计：<span style="color:#FD4B64;font-size:18px;">￥<fmt:formatNumber value="${amount}" pattern="#,##0.0#"/></span></span>
	</td>
	</tr>
	</tfoot>
	</table>
	<form style="margin-left:50px;position: relative;top:30px;min-height:100px;">
	收货地址：<select id="address" style="max-width:200px" name="address"></select>&nbsp;&nbsp;<a id="popover" data-toggle="popover" href="${pageContext.request.contextPath}/customer/getDelivery">管理</a>
	<span style="margin-left:20px">付款方式：<label><input id="method" type="radio" name="method" value="货到付款" checked/>货到付款</label>
		   <label><input id="method" type="radio" name="method" value="在线支付"/>在线支付</label></span>
	<span style="margin-left:20px">送达时间：<select id="expect" name="expect">
			<option value="马上送达">马上送达</option>
			<option value="半小时内">半小时内</option>
			<option value="一小时内">一小时内</option>
		   </select></span>
	<input id="submit-button" type="button" style="position: absolute;right: 100px;top:-10px;" class="btn btn-primary btn-lg active" value="提交订单" onclick="submitOrder();return false;">
	</form>
	
	</div>

	
	
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
	<script>
	//弹出框初始化
	$('#popover').popover({
		 title:'未选择收货地址',
	 	 content:'未选择收货地址',
	 	 html:true,
	 	 placement:'bottom',
	 	 trigger:'manual',
	 	 template:'<div class="popover" role="tooltip">'+
	  				'<div class="arrow"></div>'+
	 			 	'<div class="popover-content"></div>'+
	  		      '</div>'
	});
	//加载收货地址
	$(function(){
		$.ajax({
			url:'${pageContext.request.contextPath}/customer/getDeliveryJson',
			type:"post",
   			headers: { 
     	  	 	'Accept': 'application/json',
    	  	    'Content-Type': 'application/json' 
	   		 },
			dataType:"json",
			data:"",
			success:function(result) {
				for(i in result)
					$('#address').append('<option>'+result[i].name+" "+result[i].phone+" "+result[i].address+'</option>');
			}
		});
	});
	//提交订单事件
	function submitOrder(){
		if($('#address option').length==0){
			$('#popover').popover('show');
			$(document).unbind('click').bind('click',function(){
				if(!$(event.target).is('[role="tooltip"],[role="tooltip"] *,#submit-button'))
				$('#popover').popover('hide');
			});
			return false;
		}
		var order={products:data,address:$('#address').val(),expect:$('#expect').val(),method:$('#method:checked').val()};
		$.ajax({
			url:'${pageContext.request.contextPath}/customer/submitOrder',
			type:"post",
   			headers: { 
     	  	 	'Accept': 'application/json',
    	  	    'Content-Type': 'application/json' 
	   		},
			dataType:"json",
			data:JSON.stringify(order),
			success:function(result) {
				location="${pageContext.request.contextPath}/customer/getOrder?id="+result.id;
			}
		});
	}
	</script>
	 <footer class="text-center">
	<h4>
		Copyright &copy;2015年 playplane.com All rights reserved.
		<a href="http://www.miitbeian.gov.cn/" class="text-muted" target="_blank">浙ICP备15018103号</a>
	</h4>
</footer>
</body>
</html>