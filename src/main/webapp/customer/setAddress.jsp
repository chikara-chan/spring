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
  				     <li><a href="${pageContext.request.contextPath}/customer/editEmailPage"><span class="glyphicon glyphicon-envelope"></span> 绑定邮箱</a></li>
  				     <li><a href="${pageContext.request.contextPath}/customer/editPhonePage"><span class="glyphicon glyphicon-phone"></span> 绑定手机</a></li>
  				     <li><a class="active" href="${pageContext.request.contextPath}/customer/getDelivery"><span class="glyphicon glyphicon-th-list"></span> 收货管理</a></li>
				</ul>
				<div class="blank"></div>
			</div>
			<div class="col-sm-9 right-part setAddress">
				<c:forEach var="delivery" items="${deliverys}">
					<div class="card pull-left">
						<div class="card-section first">
							${delivery.name}
							<a class="pull-right" href="${pageContext.request.contextPath}/customer/deleteDelivery?id=${delivery.id}">删除</a>
							<span class="pull-right">&nbsp;</span>
							<a class="pull-right" href="javascript:void(0)" data-toggle="modal" data-target="#myModal2" onclick="editPage('${delivery.id}','${delivery.name}','${delivery.phone}','${delivery.address}')">编辑</a>
						</div>
						<div class="card-section second">
							${delivery.phone}<br>
							${delivery.address}
						</div>
					</div>
				</c:forEach>
				<div class="card pull-left add" data-toggle="modal" data-target="#myModal">
					<span class="glyphicon glyphicon-plus icon"></span><br>
					<span class="word">新增收货地址</span>
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
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal">
   		<div class="modal-dialog">
     		 <div class="modal-content">
       			 <div class="modal-body">
           			 <button type="button" class="close" data-dismiss="modal">&times;</button>
            		 <div class="modal-title" id="myModalLabel">新增收货地址</div>
          			 <form class="form-horizontal" action="${pageContext.request.contextPath}/customer/addDelivery"  method="post">
          			 	<div class="form-group">
							<label for="control-1" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-9">
								<input type="text" name="name" class="form-control" id="control-1" >
							</div>
						</div>	
          			 	<div class="form-group">
							<label for="control-2" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-9">
								<input type="tel" name="phone" class="form-control" id="control-2" >
							</div>
						</div>
          			 	<div class="form-group">
							<label for="control-3" class="col-sm-2 control-label">地址</label>
							<div class="col-sm-9">
								<input type="text" name="address" class="form-control" id="control-3" >
							</div>
						</div>
					   <button type="submit" class="col-sm-offset-2 btn btn-primary">提交</button>
           			</form>
        		 </div>
     		 </div>
		</div>
	</div>
	
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal2">
   		<div class="modal-dialog">
     		 <div class="modal-content">
       			 <div class="modal-body">
           			 <button type="button" class="close" data-dismiss="modal">&times;</button>
            		 <div class="modal-title" id="myModalLabel">编辑收货地址</div>
          			 <form class="form-horizontal" action="${pageContext.request.contextPath}/customer/editDelivery"  method="post">
          			 	<div class="form-group">
							<label for="control-1" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-9">
								<input type="text" name="name" class="form-control" id="control-1" >
							</div>
						</div>	
          			 	<div class="form-group">
							<label for="control-2" class="col-sm-2 control-label">手机</label>
							<div class="col-sm-9">
								<input type="tel" name="phone" class="form-control" id="control-2" >
							</div>
						</div>
          			 	<div class="form-group">
							<label for="control-3" class="col-sm-2 control-label">地址</label>
							<div class="col-sm-9">
								<input type="text" name="address" class="form-control" id="control-3" >
							</div>
						</div>
						<input name="id" type="hidden">
					   <button type="submit" class="col-sm-offset-2 btn btn-primary">保存</button>
           			</form>
        		 </div>
     		 </div>
		</div>
	</div>

	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
	<script>
	function editPage(id,name,phone,address) {
		$('input[name="name"]','#myModal2').val(name);
		$('input[name="phone"]','#myModal2').val(phone);
		$('input[name="address"]','#myModal2').val(address);
		$('input[name="id"]','#myModal2').val(id);
	}
	</script>
</body>
</html>
	