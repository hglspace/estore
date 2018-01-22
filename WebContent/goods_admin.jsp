<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>后台商品管理</title>
		<style type="text/css">
			table {
				width: 1000px;
				border-collapse: collapse;
			}
			td,th {
				border: 1px black solid;
			}
			
			.even {
				background-color: pink;
			}
			
			.odd {
				background-color: gray;
			}
		</style>
	</head>
  	
	<body>
		<table>
			<tr>
				<th>商品名称</th>
				<th>市场价</th>
				<th>优惠价</th>
				<th>库存数量</th>
				<th>商品分类</th>
				<th>描述</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${gList }" var="g" varStatus="stu">
			<tr class="${stu.count % 2 == 0 ? 'even' : 'odd' }">
				<td>${g.name }</td>
				<td>${g.marketprice }</td>
				<td>${g.estoreprice }</td>
				<td>${g.num }</td>
				<td>${g.category }</td>
				<td title="${g.description}">
					<c:if test="${ fn:length(g.description) > 20 }">
						${ fn:substring(g.description, 0, 20) }...
					</c:if>
					<c:if test="${ fn:length(g.description) <= 20 }">
						${ g.description }
					</c:if>
				</td>
				<td>
					<a href="##">修改</a>
					<a href="##">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>
