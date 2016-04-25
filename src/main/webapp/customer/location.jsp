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
<link href="${pageContext.request.contextPath}/css/chikara-customer-location.css" rel="stylesheet">
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
			<li><a href="#">加入我们</a></li>
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
<div id="container">
	<div class="content">
		<div class="content_title">选择地域/学校</div>
		<div class="choose">
			<div class="choice"><span class="pro">所在省份</span><ul class="province" id="province"></ul></div>
			<div class="choice"><span class="pro">所在城市</span><ul class="province" id="city"></ul></div>
			<div class="choice"><span class="pro">所在学校</span><ul class="province" id="school"></ul></div>
			<div class="choice"><span class="pro">已开通</span><ul class="province" id="unit"></ul></div>
			<div class="choice"><span class="pro">未开通</span><ul class="province" id="anti-unit"></ul></div>
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
            		 <div class="modal-title" id="myModalLabel">超市入驻</div>
          			 <form class="form-horizontal" action="${pageContext.request.contextPath}/apply/applySubmit"  method="post">
						<input  name="unitId"  type="hidden" value="${apply.unitId}">
						<div class="form-group">
							<label class="col-sm-3 control-label">地址</label>
							<div class="col-sm-8">
								<p class="form-control-static"></p>
							</div>
						</div>	
          			 	<div class="form-group">
							<label for="control-1" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-8">
								<input type="text" name="name" class="form-control" id="control-1" >
							</div>
						</div>
          			 	<div class="form-group">
							<label class="col-sm-3 control-label">姓别</label>
							<div class="col-sm-8">
								<label style="margin-right:20px;position: relative;top:5px;"><input name="gender"  type="radio" value="男" checked><span> 男</span></label>
								<label style="position: relative;top:5px;"><input  name="gender" type="radio" value="女"><span> 女</span></label>
							</div>
						</div>		
          			 	<div class="form-group">
							<label for="control-2" class="col-sm-3 control-label">手机</label>
							<div class="col-sm-8">
								<input type="tel" name="phone" class="form-control" id="control-2" >
							</div>
						</div>
          			 	<div class="form-group">
							<label for="control-3" class="col-sm-3 control-label">邮箱</label>
							<div class="col-sm-8">
								<input type="text" name="email" class="form-control" id="control-3" >
							</div>
						</div>
					    <button type="submit" class="col-sm-offset-3 btn btn-primary">提交</button>
           			</form>
        		 </div>
     		 </div>
		</div>
	</div>

	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
	<script>
	$(function(){
		getProvince();
	});
	
	 //获取省份列表
	function getProvince(){
		$.post('${pageContext.request.contextPath}/location/province', {},function(result) {
			for(i in result){
				$('#province').append('<li><a href=\'javascript:void(0)\'>'+result[i]+'</a></li>');
			}
			$('a','#province').each(function(){
				$(this).click(function(){
					$(this).addClass('link-active');
					$(this).parent().siblings().children().removeClass('link-active');
					getCity(this);
				});
			});
			$('a:first','#province').addClass('link-active').click();
		},'JSON');
	}
	
	//获取城市列表
	function getCity(target){
		$.post('${pageContext.request.contextPath}/location/city', {province:$(target).html()},function(result) {
			$('#city').empty();
			for(i in result){
				$('#city').append('<li><a href=\'javascript:void(0)\'>'+result[i]+'</a></li>');
			}
			$('a','#city').each(function(){
				$(this).click(function(){
					$(this).addClass('link-active');
					$(this).parent().siblings().children().removeClass('link-active');
					getSchool(this);
				});
			});
			$('a:first','#city').addClass('link-active').click();
		},'JSON');
	}
	
	//获取学校列表
	function getSchool(target){
		$.post('${pageContext.request.contextPath}/location/school', {city:$(target).html()},function(result) {
			$('#school').empty();
			for(i in result){
				$('#school').append('<li><a href=\'javascript:void(0)\'>'+result[i]+'</a></li>');
			}
			$('a','#school').each(function(){
				$(this).click(function(){
					$(this).addClass('link-active');
					$(this).parent().siblings().children().removeClass('link-active');
					getUnit(this);
				});
			});
			$('a:first','#school').addClass('link-active').click();
		},'JSON');
	}
	
	//获取楼栋列表
	function getUnit(target){
		$.post('${pageContext.request.contextPath}/location/unit', {school:$(target).html()},function(result) {
			$('#unit').empty();
			$('#anti-unit').empty();
			for(i in result){
				if(result[i].available==true){
					var node=$("<li><a class='text-decoration' href='javascript:void(0)' onclick='findProduct("+result[i].id+")'>"+result[i].name+"</a></li>");
					$('#unit').append(node);
				}else{
					var node=$("<li><a class='text-decoration' href='javascript:void(0)'  data-toggle='modal' data-target='#myModal' onclick='getData(\""+result[i].name+"\",\""+result[i].id+"\")' >"+result[i].name+"</a></li>");
					$('#anti-unit').append(node);
				};
			};
			$('a','#anti-unit').each(function(){
				var content=$(this).html();
				$(this).mouseover(function(){
					$(this).html("我要开店");
				});
				$(this).mouseout(function(){
					$(this).html(content);
				});
			});
		},'JSON');
	}
	
	//已开通楼栋获取商品
	function findProduct(id) {
		setCookie("unitId", id);
		location='${pageContext.request.contextPath}/product/listByCustomer?id='+id;	
	}
	
	//未开通楼栋获取申请入驻表
	function applyUnit(id) {
		location='${pageContext.request.contextPath}/apply/applyPage?unitId='+id;	
	}
	//点击 我要开店，弹出modal后，数据传输
	function getData(address,id) {
		  if('${sessionInfo.type}'!='2'){
			  location="${pageContext.request.contextPath}/account/login.jsp";
			  return false;
		  }
		$('.form-control-static','#myModal').html(address);
		$('input[type="hidden"]').val(id);
	}
	</script> 
</body>
</html>