<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.foodType.model.*"%>

<%
FoodTypeVO foodTypeVO = (FoodTypeVO) request.getAttribute("foodTypeVO"); // FoodTypeServlet.java 存入 req 的 foodTypeVO 物件
%>
--<%=foodTypeVO == null%>--${foodTypeVO.storeId}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>類型資料修改 - updateFoodTypeInput.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>餐點類型資料修改 - updateFoodTypeInput.jsp</h3>
				<h4>
					<a href="selectFoodType.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<c:if test="${not empty errorMsg}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsg}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="foodType.do" name="form1">
		<table>
			<tr>
				<td>類型編號:</td>
				<td><%=foodTypeVO.getFoodTypeId()%></td>
			</tr>
			<tr>
				<td>店家編號:</td>
				<td><%=foodTypeVO.getStoreId()%></td>
			</tr>
			<tr>
				<td>類型名稱:</td>
				<td><select name="type">
						<c:set var="selected" value="<%=foodTypeVO.getType()%>" />
						<jsp:useBean id="foodTypeSvc" scope="page"
							class="com.foodType.model.FoodTypeService" />
						<c:set var="typeList" value="${foodTypeSvc.all}" />

						<c:forEach var="typeVO" items="${typeList}">
							<option value="${typeVO.type}"
								<c:if test="${typeVO.type == selected}">selected</c:if>>${typeVO.type}</option>
						</c:forEach>

				</select></td>
			</tr>
		</table>
		<input type="hidden" name="foodTypeId"
			value="${foodTypeVO.foodTypeId}" /> <input type="hidden"
			name="storeId" value="${foodTypeVO.storeId}" /> <br> <input
			type="hidden" name="action" value="update"> <input
			type="hidden" name="foodTypeId"
			value="<%=foodTypeVO.getFoodTypeId()%>"> <input type="submit"
			value="送出修改">
	</FORM>

</body>
</html>
