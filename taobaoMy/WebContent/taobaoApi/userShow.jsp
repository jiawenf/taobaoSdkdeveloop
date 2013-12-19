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
	</div><div class="headCen">
	<table border="1">
		<tr>
			<td>用户昵称</td>
			<td>用户编号</td>
			<td>类型</td>
		</tr>
		<c:forEach var="cast" items="${userList }" varStatus="st">

			<tr>
				<td><a href="./detailShop?nick=<c:out value="${cast.nick }" />"><c:out
							value="${cast.nick }" /></a></td>
				<td><c:out value="${cast.user_id }" /></td>
				<td><c:out value="${cast.type }" /></td>
			</tr>
		</c:forEach>
	</table></div>
</body>
</html>