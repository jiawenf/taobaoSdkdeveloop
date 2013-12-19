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
	</div><div class="hendCen">
	<table border="1">
		<tr>
			<td>卖家昵称</td>
			<td>买家昵称</td>
			<td>商品名称</td>
			<td>商品图片</td>
			<td>购买数量</td>
			<td>实付金额</td>
			<td>交易时间</td>
		</tr>
		<c:forEach var="cast" items="${tradeList }" varStatus="st">

			<tr>
				<td><a
					href="./detailShop?nick=<c:out value="${cast.seller_nick }" />"><c:out
							value="${cast.seller_nick }" /></a></td>
				<td><c:out value="${cast.buyer_nick }" /></td>
				<td><a
					href="./detailItem?num_iid=<c:out value="${cast.num_iid }" />"><c:out
							value="${cast.title }" /></a></td>
				<td><img alt="兔兔图" src="<c:out value="${cast.pic_path }" />"
					width="50" height="50"></td>
				<td><c:out value="${cast.num }" /></td>
				<td>￥：<c:out value="${cast.payment }" />元
				</td>
				<td><c:out value="${cast.created }" /></td>
			</tr>
		</c:forEach>
	</table></div>
</body>
</html>