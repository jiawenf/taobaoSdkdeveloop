<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
.hendCen {
	text-align: center;
}
</style>
</head>
<body>
	<div class="hendCen">
		<ul>
			<li><a href="./allTrade">查询所有交易信息</a></li>
			<li><a href="./userDetail">显示用户信息</a></li>
		</ul>
	<div class="hendCen">
	<table border="1">
		<tr>
			<td>用户昵称</td>
			<td>店铺名称</td>
			<td>创建时间</td>
			<td>店铺描述</td>
		</tr>
		<c:forEach var="cast" items="${shopList }" varStatus="st">

			<tr>
				<td><a href="./detailShop?nick=<c:out value="${cast.nick }" />"><c:out
							value="${cast.nick }" /></a></td>
				<td><c:out value="${cast.title }" /></td>
				<td><c:out value="${cast.created }" /></td>
				<td><c:out value="${cast.desc }" escapeXml="false" /></td>
			</tr>
		</c:forEach>
	</table></div>
</body>
</html>