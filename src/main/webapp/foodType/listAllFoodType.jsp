<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.foodType.model.*"%>

<%
    FoodTypeService foodTypeSvc = new FoodTypeService();
    List<FoodTypeVO> list = foodTypeSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
    <title>所有餐點類型</title>
</head>
<body>
<h2>所有餐點類型資料</h2>

<c:if test="${not empty list}">
    <table border="1" cellpadding="6">
        <tr>
            <th>類型編號</th>
            <th>店家編號</th>
            <th>類型名稱</th>
            <th>操作</th>
        </tr>
        <c:forEach var="foodTypeVO" items="${list}">
            <tr>
                <td>${foodTypeVO.foodTypeId}</td>
                <td>${foodTypeVO.storeId}</td>
                <td>${foodTypeVO.type}</td>
                <td>
                    <form method="post" action="foodType.do" style="display:inline">
                        <input type="hidden" name="foodTypeId" value="${foodTypeVO.foodTypeId}" />
                        <input type="hidden" name="action" value="getOneForDisplay" />
                        <input type="submit" value="查看" />
                    </form>
                    <form method="post" action="foodType.do" style="display:inline">
                        <input type="hidden" name="foodTypeId" value="${foodTypeVO.foodTypeId}" />
                        <input type="hidden" name="action" value="getOneForUpdate" />
                        <input type="submit" value="修改" />
                    </form>
                    <form method="post" action="foodType.do" style="display:inline" onsubmit="return confirm('確定要刪除嗎？');">
                        <input type="hidden" name="foodTypeId" value="${foodTypeVO.foodTypeId}" />
                        <input type="hidden" name="action" value="delete" />
                        <input type="submit" value="刪除" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty list}">
    <p style="color:red">目前尚無任何餐點類型資料。</p>
</c:if>

<br>
<a href="selectFoodType.jsp">回查詢頁</a>

</body>
</html>
