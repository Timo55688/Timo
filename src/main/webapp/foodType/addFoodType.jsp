<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.foodType.model.*" %>
<%@ page import="com.store.model.*" %>

<%
    FoodTypeVO foodTypeVO = (FoodTypeVO) request.getAttribute("foodTypeVO");
    if (foodTypeVO == null) {
        foodTypeVO = new FoodTypeVO(); // 避免 NullPointerException
    }
%>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>餐點類型新增 - addFoodType.jsp</title>
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
                <h3>餐點類型新增 - addFoodType.jsp</h3>
            </td>
            <td>
                <h4><a href="../index.jsp"><img src="../images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
            </td>
        </tr>
    </table>

    <h3>資料新增:</h3>

    <c:if test="${not empty errorMsg}">
        <font style="color:red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsg}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>

    <form method="post" action="foodType.do">
        <table>
            <jsp:useBean id="storeSvc" class="com.store.model.StoreService" scope="page" />

            <tr>
                <td>店家名稱:</td>
                <td>
                    <select name="storeId">
                        <option value="">請選擇店家</option>
                        <c:forEach var="storeVO" items="${storeSvc.all}">
                            <option value="${storeVO.storeId}"
                                <c:if test="${storeVO.storeId == foodTypeVO.storeId}">selected</c:if>>
                                ${storeVO.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>類型名稱:</td>
                <td>
                    <input type="text" name="type" value="${foodTypeVO.type}" size="20"/>
                </td>
            </tr>
        </table>

        <br>
        <input type="hidden" name="action" value="insert">
        <input type="submit" value="送出新增">
    </form>

</body>
</html>
