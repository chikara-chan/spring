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
<script>
//商品对象数组
var data=[];
//购物车总价
var total=0;

//用户点击加入购物车事件
function addCar(id){
	for(i in data){
		if(data[i].id==id){
			var number=parseInt(data[i].number);
			if(number<=0||data[i].added>=number)
				return;
			total=parseFloat((total+parseFloat(data[i].price)).toFixed(1));
			$('#total').html(total);
			if(data[i].added!=0){
				data[i].added++;
				$('#'+data[i].id).html(parseInt($('#'+data[i].id).html())+1);
				animate();
				return;
			}
			var tdNumber=$('<td><button onclick="removeE(\''+data[i].id+'\')" class="btn btn-xs btn-default" style="color: red">-</button> <span id="'+data[i].id+'">1</span> <button onclick="add(\''+data[i].id+'\')" class="btn btn-xs btn-default" style="color: red">+</button></td>');
			var tdName=$('<td>'+data[i].name+'</td>');
			var tdPrice=$('<td>￥<span>'+data[i].price+'</span></td>');
			$('tbody').append('<tr>');
			$('tr:last','tbody').append(tdName).append(tdNumber).append(tdPrice);
			data[i].added=1;
		}
	}
	animate();
}
//加入购物车动画效果
function animate(){
	var offset = $("#car-img").offset(); 
    var flyer = $('<img src="${pageContext.request.contextPath}/images/flyer.png">'); //抛物体对象 
    flyer.fly({ 
      start: { 
        left: event.clientX, //抛物体起点横坐标 
        top: event.clientY-50  //抛物体起点纵坐标 
      }, 
      end: { 
        right: 340,//抛物体终点横坐标 
        bottom: 30, //抛物体终点纵坐标 
      }, 
      onEnd: function() { 
    	  flyer.remove();
    	  judge();
      } 
    }); 
}
//用户点击购物车添加按钮事件
function removeE(id){
	for(i in data){
		if(data[i].id==id){
			total=parseFloat((total-parseFloat(data[i].price)).toFixed(1));
			$('#total').html(total);
			data[i].added--;
			$('#'+data[i].id).html(parseInt($('#'+data[i].id).html())-1);
			if(data[i].added<=0)
				$('#'+id).parents('tr').remove();
		}
	}
	judge();
}
//用户点击购物车删减按钮事件
function add(id){
	for(i in data){
		if(data[i].id==id){
			var number=parseInt(data[i].number);
			if(number<=0||data[i].added>=number)
				return;
			total=parseFloat((total+parseFloat(data[i].price)).toFixed(1));
			$('#total').html(total);
			data[i].added++;
			$('#'+data[i].id).html(parseInt($('#'+data[i].id).html())+1);
		}
	}
}
//判断购物车里面有无商品
function judge(){
	if(total<=0)
		$('#car').hide();
	else 
		$('#car').show();
}
</script>
</head>
<body>
	<c:if test="${sessionInfo.type==1||sessionInfo.type==0}">
		<c:redirect url="/account/login.jsp"/>
	</c:if>
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
	
<div class="container ">
 	<div class="row">
 		<div class="col-sm-9">
			<c:forEach var="product" items="${products}">
				<div class="product-div">
					<img class="img-thumbnail" src="${product.pic}"><br>
					<div class="row">
						<div class="col-sm-6 name">
							${product.name}
						</div>
						<div class="col-sm-6 text-danger h4 price">
							￥${product.price}
						</div>
						</div>
						<div class="row">
						<div class="col-sm-5 small bottom-col">
							库存：${product.number}
						</div>
						<div class="col-sm-7 ">
							<button class="btn btn-default" onclick="addCar('${product.id}')">加入购物车</button>
						</div>
					</div>
					<script>
						 data.push({id:'${product.id}',name:'${product.name}',price:'${product.price}',number:'${product.number}',added:0});
					</script>
				</div>
			</c:forEach> 
		</div>
		<div class="col-sm-3">
			<div class="model-box">
				<div class="title"><b>当前位置</b></div>
				<div class="content">
					<span class="glyphicon glyphicon-map-marker text-primary"></span>
					<c:if test="${empty address}">
						<c:redirect url="/index.jsp" />
					</c:if>
					<c:if test="${!empty address}">
						<a class="text-muted" href="${pageContext.request.contextPath}/customer/location.jsp">${address.schoolName}${address.unitName}</a>
					</c:if>
					<span class="glyphicon glyphicon-hand-left text-danger"></span>
				</div>
			</div>
			<div class="model-box">
				<div class="title"><b>店铺公告</b></div>
				<div class="content">
					<span class="glyphicon glyphicon-bullhorn text-primary"></span>
					${broadcast}
				</div>
			</div>
			<div class="model-box">
				<div class="title"><b>评价区</b></div>
				<div class="content">
					<c:forEach var="order" end="9" items="${orders}">
						<div class="content-review">
							<p>${order.review}</p>
							<h6 class="text-muted"><fmt:formatDate value="${order.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></h6>
							<c:if test="${!empty order.replyReview}">
								<div class="reply-review">超市回复：${order.replyReview}</div>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
	
<div id="car" style="position: fixed;right: 30px;bottom: 0;background:#fff; width:350px;display: none">
	<table class="table" style="border:1px solid gray;margin-bottom:0">
		<thead>
			<tr>
				<th width="40%">商品 <a id="car-empty" class="text-danger h6" href="javascript:void(0)">[清空]</a></th>
				<th width="30%">份数</th>
				<th width="30%">价格</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
		<tfoot style="background:#28f;">
			<tr>
				<td>
					<div class="img-circle" style="background:#06d;width:50px;height:50px;text-align: center;line-height: 55px;font-size: 25px;position: absolute;left:15px;bottom: 10px">
						<span class="glyphicon glyphicon-shopping-cart" style="color:#fff;"></span>
					</div>
				</td>
				<td>
					<span class="h4" style="color: white;position: absolute;left: 75px;bottom: 5px">总计</span>
					<span class="h3" style="color: white;position: absolute;left: 110px;bottom: 3px">￥<span id="total">0</span></span>
				</td>
				<td>
					<button id="car-button" class="btn btn-default" style="width:100px;">去下单</button>
				</td>
			</tr>
		</tfoot>
	</table>
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
<script src="${pageContext.request.contextPath}/js/fly.js"></script>
<script src="${pageContext.request.contextPath}/js/requestAnimationFrame.js"></script>
<script>
//购物车清空按钮点击事件
	$(function () {
		$('#car-empty').click(function(){
			total=0;
			judge();
			$('tbody').empty();
			for(i in data){
				data[i].added=0;
			}
		});
	});
	//购物车下单点击事件
	$(function () {
		  $('#popover').popover({
			  title:'您尚未登录',
			  content:'您尚未登录',
			  html:true,
			  placement:'bottom',
			  trigger:'manual',
			  selector:$('.test'),
			  template:'<div class="popover" role="tooltip">'+
			  				'<div class="arrow"></div>'+
			 			 	'<div class="popover-content"></div>'+
			  		   '</div>'
		  });
		  $('#car-button').click(function(){
			  if('${sessionInfo.type}'!='2'){
				  $('#popover').popover('show');
				  $(document).unbind('click').bind('click',function(){
				  	if(!$(event.target).is('[role="tooltip"],[role="tooltip"] *,#popover'))
						$('#popover').popover('hide');
				  });
				  $('html,body').animate({scrollTop:0});
				  return false;
			  }else {
				  $.ajax({
						url:'${pageContext.request.contextPath}/product/submitCar',
						type:"post",
					    headers: { 
					        'Accept': 'application/json',
					        'Content-Type': 'application/json' 
					    },
						dataType:"json",
						data:JSON.stringify(data),
						success:function(result) {
							location="${pageContext.request.contextPath}/customer/order_preview.jsp";
						}
				  });
			  }
		  });
	});

</script>
</body>
</html>