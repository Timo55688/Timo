<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.foodType.model.*" %>

<%
	FoodTypeService foodTypeSvc = new FoodTypeService();
	request.setAttribute("list", foodTypeSvc.getAll());
%>

<html>
<head>
    <title>餐點類型一覽表</title>
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #666;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #ddd;
        }
    </style>
</head>
<body>

<h2 align="center">餐點類型清單</h2>

<table>
    <tr>
        <th>類型編號 (foodTypeId)</th>
        <th>店家編號 (storeId)</th>
        <th>類型名稱 (type)</th>
        <th>操作</th>
    </tr>

    <c:forEach var="foodTypeVO" items="${list}">
        <tr>
            <td>${foodTypeVO.foodTypeId}</td>
            <td>${foodTypeVO.storeId}</td>
            <td>${foodTypeVO.type}</td>
            <td>
                <form action="foodType.do" method="post" style="display:inline;">
                    <input type="hidden" name="foodTypeId" value="${foodTypeVO.foodTypeId}">
                    <input type="hidden" name="action" value="getOneForUpdate">
                    <input type="submit" value="修改">
                </form>
                &nbsp;
                <form action="foodType.do" method="post" style="display:inline;" 
                      onsubmit="return confirm('確定要刪除嗎？');">
                    <input type="hidden" name="foodTypeId" value="${foodTypeVO.foodTypeId}">
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" value="刪除">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

<div align="center">
    <a href="addFoodType.jsp">➕ 新增餐點類型</a> |
    <a href="selectFoodType.jsp">🔍 查詢餐點類型</a>
</div>

</body>
</html>
