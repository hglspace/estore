<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的购物车</title>
<%@include file="inc/common_head.jsp"%>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block table">
		<div class="AreaR">
			<div class="block box">
				<div class="blank"></div>
				<div id="ur_here">
					当前位置: <a href="index.jsp">首页</a><code>&gt;</code>我的购物车
				</div>
			</div>
			<div class="blank"></div>
			<div class="box">
				<div class="box_1">
					<div class="userCenterBox boxCenterList clearfix"
						style="_height:1%;">
						<h5><span>我的购物车</span></h5>
						<table width="100%" align="center" border="0" cellpadding="5"
							cellspacing="1" bgcolor="#dddddd">
							<tr>
								<th bgcolor="#ffffff">商品名称</th>
								<th bgcolor="#ffffff">市场价</th>
								<th bgcolor="#ffffff">本店价</th>
								<th bgcolor="#ffffff">购买数量</th>
								<th bgcolor="#ffffff">小计</th>
								<th bgcolor="#ffffff" width="160px">操作</th>
							</tr>
							<c:if test="${empty list }">
								暂时还没有商品哟.....<a href="${root}/queryGoodServlet">去逛逛</a>
							</c:if>
							<c:if test="${not empty list }">
					<%-- 		<c:set var="sum" value="0"></c:set>
							<c:set var="save" value="0"></c:set> --%>
							<!--  el表达式取值的时候如果key不存在，就什么都没有,所以可以注释-->
							<!-- 数据使用EL表达式获取之后，都转成字符串的形式，如果执行运算，那么将字符串转成数字类型，然后再进行运算 -->
							测试：${ss+5}
							<c:forEach  var="cc" items="${list}">
								<c:set var="sum" value="${sum + cc.goods.estoreprice*cc.buynum}"></c:set>
								<c:set var="save" value="${save + (cc.goods.marketprice - cc.goods.estoreprice)*cc.buynum}"></c:set>
							<tr>
								<td bgcolor="#ffffff" align="center" style="width:300px;">
									<!-- 商品图片 -->
									<a href="javascript:;" target="_blank">
										<img style="width:80px; height:80px;"
										src="${root}${cc.goods.imgurl}"
										border="0" title="${cc.goods.name}" />
									</a><br />
									<!-- 商品名称 -->
									<a href="javascript:;" target="_blank" class="f6">${cc.goods.name}</a>
								</td>
								<td align="center" bgcolor="#ffffff">${cc.goods.marketprice }元</td>
								<td align="center" bgcolor="#ffffff">${cc.goods.estoreprice }元</td>
								<td align="center" bgcolor="#ffffff">
									<input value="${cc.buynum }" size="4" class="inputBg" style="text-align:center;" />
								</td>
								<td align="center" bgcolor="#ffffff">${cc.goods.estoreprice * cc.buynum}元</td>
								<td align="center" bgcolor="#ffffff">
									<a href="javascript:;" class="f6">删除</a>
								</td>
							</tr>
							</c:forEach>
							</c:if>
							<tr>
								<td colspan="6" style="text-align:right;padding-right:10px;font-size:25px;">
									购物金额小计&nbsp;<font color="red">${sum }</font>元，
									共为您节省了&nbsp;<font color="red">${save }</font>元
									<a href="orders_submit.jsp"><input value="去结算" type="button" class="btn" /></a>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="blank"></div>
		<div class="blank5"></div>
	</div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>
