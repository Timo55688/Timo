<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Food Type: Home</title>

<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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
				<h3>Food Type: Home</h3>
				<h4>( MVC )</h4>
			</td>
		</tr>
	</table>

	<p>This is the Home page for Food Type Module</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤訊息顯示 --%>
	<c:if test="${not empty errorMsg}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsg}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='listAllFoodType.jsp'>List</a> all Food Types. <br>
		<br></li>

		<li>
			<form method="post" action="foodType.do">
				<b>輸入類型編號:</b> <input type="text" name="foodTypeId"> <input
					type="hidden" name="action" value="getOneForDisplay"> <input
					type="submit" value="送出">
			</form>
		</li>

		<jsp:useBean id="foodTypeSvc" scope="page"
			class="com.foodType.model.FoodTypeService" />

		<li>
			<form method="post" action="foodType.do">
				<b>選擇類型編號:</b> <select size="1" name="foodTypeId">
					<c:forEach var="foodTypeVO" items="${foodTypeSvc.all}">
						<option value="${foodTypeVO.foodTypeId}">${foodTypeVO.foodTypeId}</option>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOneForDisplay">
				<input type="submit" value="送出">
			</form>
		</li>

		<li>
			<form method="post" action="foodType.do">
				<b>選擇類型名稱:</b> <select size="1" name="foodTypeId">
					<c:forEach var="foodTypeVO" items="${foodTypeSvc.all}">
						<option value="${foodTypeVO.foodTypeId}">${foodTypeVO.type}</option>
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOneForDisplay">
				<input type="submit" value="送出">
			</form>
		</li>
	</ul>

	<h3>類型管理</h3>
	<ul>
		<li><a href='addFoodType.jsp'>Add</a> a new Food Type</li>
	</ul>

</body>
</html>
