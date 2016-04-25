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
<link href="${pageContext.request.contextPath}/css/chikara-market-product.css" rel="stylesheet">
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
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/unreceivedOrder">待收货</a></div>
				<div class="sub-title"><a href="${pageContext.request.contextPath}/market/finishedOrder">已完成</a></div>
				<div class="title"><span class="glyphicon glyphicon-tags text-muted"></span> 商品管理</div>
				<div class="sub-title"><a class="active" href="${pageContext.request.contextPath}/product/listByMarket">商品列表</a></div>
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
 					<h3><b>商品列表</b></h3>
				</div>
				<div class="part-body">
					<c:forEach var="product" items="${products}">
						<div class="product-div">
							<img class="img-thumbnail" src="${product.pic}"><br>
							<div class="row">
								<div class="col-sm-6 name">
									${product.name}
								</div>
								<div class="col-sm-5 text-danger h4 price">
									￥${product.price}
								</div>
								</div>
								<div class="row">
								<div class="col-sm-5 small bottom-col">
									库存：${product.number}
								</div>
								<div class="col-sm-7 ">
									<button class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal2" onclick="editPage('${product.id}','${product.name}','${product.price}','${product.number}')">编辑</button>
									<button class="btn btn-danger btn-sm" onclick="location='${pageContext.request.contextPath}/product/delete?id=${product.id}'">删除</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
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
            		 <div class="modal-title" id="myModalLabel">编辑商品</div>
          			 <form class="form-horizontal" action="${pageContext.request.contextPath}/product/edit" method="post" enctype="multipart/form-data">
          			 	<div class="form-group">
							<label for="control-1" class="col-sm-2 control-label">名称</label>
							<div class="col-sm-9">
								<input type="text" name="name" value="${product.name}" class="form-control" id="control-1" >
							</div>
						</div>	
          			 	<div class="form-group">
							<label for="control-2" class="col-sm-2 control-label">价格</label>
							<div class="col-sm-9">
								<input type="tel" name="price" value="${product.price}" class="form-control" id="control-2" >
							</div>
						</div>
          			 	<div class="form-group">
							<label for="control-3" class="col-sm-2 control-label">库存</label>
							<div class="col-sm-9">
								<input type="text" name="number" value="${product.number}" class="form-control" id="control-3" >
							</div>
						</div>
						<input id="lefile" type="file" name="product_picture" style="display:none" accept="image/*">
						<div class="input-group">
  							<input id="photoCover" type="text" class="form-control" disabled>
 							<a class="input-group-addon btn btn-default" id="basic-addon2" onclick="$('input[id=lefile]').click();">浏览</a>
						</div>
						
						<input name="id" type="hidden" value="${product.id}">
					    <button type="submit" class="col-sm-offset-2 btn btn-primary">保存</button>
           			</form>
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
	function editPage(id,name,price,number) {
		$('input[name="name"]','#myModal2').val(name);
		$('input[name="price"]','#myModal2').val(price);
		$('input[name="number"]','#myModal2').val(number);
		$('input[name="id"]','#myModal2').val(id);
	}
	$('input[id=lefile]').change(function() {
		$('#photoCover').val($(this).val());
	});
	</script>
</body>
</html>