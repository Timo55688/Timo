<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.store.model.*"%>
<%
  StoreVO storeVO = (StoreVO) request.getAttribute("storeVO"); // FoodServlet.java 存入 req 的 foodVO 物件
%>
<html>
<head>
    <title>單一店家資料</title>
</head>
<body>
    <h2>單一店家查詢結果</h2>

    <c:if test="${not empty storeVO}">
        <table border="1" cellpadding="6">
            <tr><th>欄位</th><th>內容</th></tr>
            <tr><td>店家編號</td><td><%=storeVO.getStoreId() %></td></tr>
            <tr><td>店名</td><td>${storeVO.name}</td></tr>
            <tr><td>負責人</td><td>${storeVO.managerName}</td></tr>
            <tr><td>Email</td><td>${storeVO.email}</td></tr>
            <tr><td>密碼</td><td>${storeVO.password}</td></tr>
            <tr><td>電話</td><td>${storeVO.phoneNum}</td></tr>
            <tr><td>統一編號</td><td>${storeVO.guiNum}</td></tr>
            <tr><td>營業登記編號</td><td>${storeVO.businessRegNum}</td></tr>
            <tr><td>註冊時間</td><td>${storeVO.regTime}</td></tr>
            <tr><td>點數</td><td>${storeVO.points}</td></tr>
            <tr><td>帳號狀態</td><td>${storeVO.accStat}</td></tr>
            <tr><td>營運狀態</td><td>${storeVO.opStat}</td></tr>
            <tr><td>開店時間</td><td>${storeVO.opTime}</td></tr>
            <tr><td>取餐時間</td><td>${storeVO.pickTime}</td></tr>
            <tr><td>最晚下單</td><td>${storeVO.lastOrder}</td></tr>
            <tr><td>關店時間</td><td>${storeVO.closeTime}</td></tr>
            <tr><td>地址</td><td>${storeVO.address}</td></tr>
            <tr><td>縣市</td><td>${storeVO.county}</td></tr>
            <tr><td>鄉鎮市區</td><td>${storeVO.district}</td></tr>
            <tr><td>郵遞區號</td><td>${storeVO.postalCode}</td></tr>
            <tr><td>星數</td><td>${storeVO.starNum}</td></tr>
            <tr><td>造訪人數</td><td>${storeVO.visitorsNum}</td></tr>
            <tr><td>審核狀態</td><td>${storeVO.reviewed}</td></tr>
        </table>
    </c:if>

    <c:if test="${empty storeVO}">
        <p style="color:red">查無資料！</p>
    </c:if>

    <br>
    <a href="selectStore.jsp">回查詢頁</a>
</body>
</html>
