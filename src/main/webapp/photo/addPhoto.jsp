<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.photo.model.*" %>

<%
    PhotoVO photoVO = (PhotoVO) request.getAttribute("photoVO");
%>
--<%= photoVO == null %>--

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>店家圖片新增 - addPhoto.jsp</title>
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
                <h3>店家圖片新增 - addPhoto.jsp</h3>
            </td>
            <td>
                <h4><a href="selectPhoto.jsp">回首頁</a>
                </h4>
            </td>
        </tr>
    </table>

    <h3>資料新增:</h3>

    <%-- 錯誤表列 --%>
    <c:if test="${not empty errorMsg}">
        <font style="color: red">請修正以下錯誤:</font>
        <ul>
            <c:forEach var="message" items="${errorMsg}">
                <li style="color: red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/photo/photo.do" enctype="multipart/form-data">

        <table>
            <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
            <tr>
                <td>店家名稱:</td>
                <td>
                    <select name="storeId">
                        <option value="">請選擇店家</option>
                        <c:forEach var="storeVO" items="${storeSvc.all}">
                            <option value="${storeVO.storeId}"
                                <c:if test="${photoVO != null && photoVO.storeId == storeVO.storeId}">selected</c:if>>
                                ${storeVO.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>上傳圖片:</td>
                <td><input type="file" name="photoSrc" /></td>
            </tr>
        </table>
        <br>
        <input type="hidden" name="action" value="insert">
        <input type="submit" value="送出新增">
    </form>

</body>
</html>
