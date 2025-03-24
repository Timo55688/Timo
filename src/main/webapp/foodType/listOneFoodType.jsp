<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.foodType.model.*" %>

<%
	FoodTypeVO foodTypeVO = (FoodTypeVO) request.getAttribute("foodTypeVO");
%>

<html>
<head>
    <title>餐點類型 - 單筆查詢結果</title>
    <style>
        table {
            border-collapse: collapse;
            width: 60%;
            margin: 30px auto;
        }
        th, td {
            border: 1px solid #666;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>

<h2 align="center">查詢結果：餐點類型</h2>

<c:if test="${not empty foodTypeVO}">
    <table>
        <tr>
            <th>類型編號 (foodTypeId)</th>
            <th>店家編號 (storeId)</th>
            <th>類型名稱 (type)</th>
        </tr>
        <tr>
            <td>${foodTypeVO.foodTypeId}</td>
            <td>${foodTypeVO.storeId}</td>
            <td>${foodTypeVO.type}</td>
        </tr>
    </table>
</c:if>

<c:if test="${empty foodTypeVO}">
    <p style="color:red; text-align:center;">查無資料</p>
</c:if>

<div align="center" style="margin-top:20px;">
    <a href="selectFoodType.jsp">🔙 回查詢頁</a> |
    <a href="listAllFoodType.jsp">📋 返回全部列表</a>
</div>

</body>
</html>
