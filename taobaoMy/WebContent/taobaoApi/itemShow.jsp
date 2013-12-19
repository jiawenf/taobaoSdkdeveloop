<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	text-align: center;
}

ul {
	display: inline;
}

li {
	list-style: none;
	margin-left: 10px;
	display: inline;
}
.headCen {
	text-align: center;
}
</style>
</head>
<body>
	<div class="headCen">
		<ul>
			<li><a href="./allTrade">查询所有交易信息</a></li>
			<li><a href="./userDetail">显示用户信息</a></li>
		</ul>
	</div><div  class="headCen">
	<table border="1" align="center">
		<tr>
			<td>商品名称</td>
			<td>商品图片</td>
			<td>是否在售</td>
			<td>库存数量</td>
			<td>创建时间</td>
			<td>商品描述</td>
		</tr>
		<tr>
			<td><c:out value="${item.title }" /></td>
			<td><a href="<c:out value="${item.detail_url }" />"><img
					alt="" src="<c:out value="${item.pic_url }" />"></a></td>
			<td><c:out value="${item.approve_status }" /></td>
			<td><c:out value="${item.num }" /></td>
			<td><c:out value="${item.delist_time }" /></td>
			<td><c:out value="${item.desc }" escapeXml="false" /></td>
		</tr>
	</table></div>
</body>
</html>