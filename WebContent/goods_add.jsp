<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选购中心</title>
<%@include file="inc/common_head.jsp"%>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block box">
		<div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="index.jsp">首页</a>
			<code>&gt;</code>
			商品列表
		</div>
	</div>
	<div class="blank"></div>
	<div class="block clearfix">
		<div class="AreaR">
			<div class="box">
				<div class="box_1">
					<div class="art-post-body">
									  <!-- 添加商品表单 -->
									  	<form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/addGoodsServlet" onsubmit="return validateForm();">
											<table>
												<tr>
													<td>商品名称</td>
													<td><input type="text" name="name" id="name"/></td>
													<td id="name_msg"></td>
												</tr>
												<tr>
													<td>市场价格</td>
													<td><input type="text" name="marketprice" id="fixed_price"/></td>
													<td id="fixed_price_msg"></td>
												</tr>
												<tr>
													<td>Estore打折价格</td>
													<td><input type="text" name="estoreprice" id="sale_price"/></td>
													<td id="sale_price_msg"></td>
												</tr>
												<tr>
													<td>商品分类</td>
													<td>
														<select name="category" id="category">
															<option value="图书、电子书刊、音像">图书、电子书刊、音像</option>
															<option value="家用电器">家用电器</option>
															<option value="手机数码">手机数码</option>
															<option value="电脑、办公">电脑、办公</option>
															<option value="家居、家具、家装、厨具">家居、家具、家装、厨具</option>
														</select>
													</td>
													<td id="category_msg"></td>
												</tr>
												<tr>
													<td>商品数量</td>
													<td><input type="text" name="num" id="num"/></td>
													<td id="pnum_msg"></td>
												</tr>
												<tr>
													<td>图片</td>
													<td><input type="file" name="imgurl" id="imgurl"/></td>
													<td id="imgurl"></td>
												</tr>
												<tr>
													<td>描述</td>
													<td><textarea rows="5" cols="80" name="description" id="description"></textarea> </td>
													<td id="description_msg"></td>
												</tr>
												<tr>
													<td colspan="3">
														<input type="submit" value="添加商品" />
													</td>
												</tr>
											</table>
										</form>
									  <!-- 添加商品表单结束 -->                        
                           			<div class="cleared"></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="inc/footer.jsp"%>
	  <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript">
    	var msg = {
    		"name" : "商品名称",
    		"fixed_price" : "市场价格",
    		"sale_price" : "打折价格",
    		"num":"商品数量",
    		"imgurl":"商品图片"
    	};
    
		function validateRequired(fieldname){
			var objval = document.getElementById(fieldname).value;
			if(objval.match("^\\s*$")!=null){
				document.getElementById(fieldname+"_msg").innerHTML = "<span style='color:red'>"+msg[fieldname]+"不能为空！</span>";
				return false;
			}else{
				document.getElementById(fieldname+"_msg").innerHTML = "";
				return true;
			}
		}
	
		function validateForm(){
			var isNameValid = validateRequired("name");
			var isfixedPriceValid = validateRequired("fixed_price");
			var isSalePriceValid = validateRequired("sale_price");
			var isPnumValid = validateRequired("num");
			var isProductImgValid =  validateRequired("imgurl");
	        return isNameValid&&isfixedPriceValid&&isSalePriceValid&&isPnumValid&&isProductImgValid;
		}
	</script>
</body>
</html>