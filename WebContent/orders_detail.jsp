<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<%@include file="inc/common_head.jsp"%>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a>
					<code>&gt;</code>
					用户中心
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix"
						style="_height:1%;">
						<h5>
							<span>订单状态</span>
						</h5>
						<table width="100%" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#dddddd">
							<tr>
								<td width="15%" align="right">订单编号：</td>
								<td align="left">${orders.id}</td>
							</tr>
							<tr>
								<td width="15%" align="right">订单状态：</td>
								<td align="left">
									<c:if test="${orders.status==1 }">
									<font color="red">未支付</font>
								</c:if>
								<c:if test="${orders.status==2 }">
									<font color="green">已支付</font>
								</c:if>
								<c:if test="${orders.status==3 }">
									<font color="gray">已过期</font>
								</c:if>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">下单时间：</td>
								<td align="left">${orders.createtime}</td>
							</tr>
							<tr>
								<td align="right">收货人信息：</td>
								<td align="left">${orders.address }</td>
							</tr>
						</table>
						<div class="blank"></div>
						<h5><span>商品列表</span></h5>
						<table width="100%" border="0" cellpadding="5" cellspacing="1"
							bgcolor="#dddddd">
							<tr>
								<th width="22%" align="center">商品名称</th>
								<th width="29%" align="center">市场价格</th>
								<th width="26%" align="center">商品价格</th>
								<th width="10%" align="center">购买数量</th>
								<th width="20%" align="center">小计</th>
							</tr>
							<c:set var="sum" value="0"></c:set>
							<c:forEach items="${orders.oList }" var="oi">
							<c:set var="sum" value="${sum+oi.goods.estoreprice * oi.buynum }"></c:set>
							<tr>
								<td>
									<a href="javascript:;" class="f6">${oi.goods.name }</a>
								</td>
								<td>${oi.goods.marketprice }元</td>
								<td>${oi.goods.estoreprice }元</td>
								<td align="center">${oi.buynum }</td>
								<td>${oi.goods.estoreprice * oi.buynum }元</td>
							</tr>
							</c:forEach>
							<tr>
								<td colspan="5" style="text-align:right;padding-right:10px;font-size:25px;">
									商品总价&nbsp;<font color="red">&yen;${sum }</font>元
									<a href="pay.jsp"><input value="确认支付" type="button" class="btn" /></a>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>