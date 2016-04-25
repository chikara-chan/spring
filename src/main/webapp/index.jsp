<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>PlayPlane网上超市</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/chikara.js"></script>
	<script>
		if(getCookie("unitId")!=null)
			location='${pageContext.request.contextPath}/product/listByCustomer?id='+getCookie("unitId");
		else 
			location='${pageContext.request.contextPath}/customer/location.jsp';
	</script>
</body>
</html>
	