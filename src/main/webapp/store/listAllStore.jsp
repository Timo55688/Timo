<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store.model.*"%>

<%
   	StoreService storeSvc = new StoreService();
    List<StoreVO> list = storeSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<html>
<head>
    <title>所有店家列表</title>
</head>
<body>
<h2>所有店家資料</h2>

<c:if test="${not empty list}">
    <table border="1" cellpadding="6">
        <tr>
            <th>店家編號</th>
            <th>店名</th>
            <th>負責人</th>
            <th>Email</th>
            <th>電話</th>
            <th>營運狀態</th>
            <th>操作</th>
        </tr>
        <c:forEach var="storeVO" items="${list}">
            <tr>
                <td>${storeVO.storeId}</td>
                <td>${storeVO.name}</td>
                <td>${storeVO.managerName}</td>
                <td>${storeVO.email}</td>
                <td>${storeVO.phoneNum}</td>
                <td>${storeVO.opStat}</td>
                <td>
                    <form method="post" action="store.do" style="display:inline">
                        <input type="hidden" name="storeId" value="${storeVO.storeId}" />
                        <input type="hidden" name="action" value="getOneForDisplay" />
                        <input type="submit" value="查看" />
                    </form>
                    <form method="post" action="store.do" style="display:inline">
                        <input type="hidden" name="storeId" value="${storeVO.storeId}" />
                        <input type="hidden" name="action" value="getOneForUpdate" />
                        <input type="submit" value="修改" />
                    </form>
                    <form method="post" action="store.do" style="display:inline" onsubmit="return confirm('確定要刪除嗎？');">
                        <input type="hidden" name="storeId" value="${storeVO.storeId}" />
                        <input type="hidden" name="action" value="delete" />
                        <input type="submit" value="刪除" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty list}">
    <p style="color:red">目前尚無任何店家資料。</p>
</c:if>

<br>
<a href="selectStore.jsp">回查詢頁</a>

</body>
</html>
