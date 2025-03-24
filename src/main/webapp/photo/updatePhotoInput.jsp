<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.photo.model.*" %>

<%
    PhotoVO photoVO = (PhotoVO) request.getAttribute("photoVO");
%>

<html>
<head>
    <title>修改店家圖片 - updatePhotoInput.jsp</title>
    <style>
        table {
            background-color: #f8f8ff;
            border: 1px solid #999;
            padding: 10px;
            width: 600px;
            margin: 20px auto;
        }
        td {
            padding: 8px;
        }
        h3 {
            text-align: center;
        }
    </style>
</head>
<body>

<h3>修改店家圖片</h3>

<c:if test="${not empty errorMsg}">
    <div style="color:red; text-align: center;">
        <p>請修正以下錯誤：</p>
        <ul>
            <c:forEach var="msg" items="${errorMsg}">
                <li>${msg}</li>
            </c:forEach>
        </ul>
    </div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/photo/photo.do" enctype="multipart/form-data">
    <table>
        <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />
        
        <tr>
            <td>照片編號：</td>
            <td><input type="text" name="photoId" value="<%= photoVO.getPhotoId() %>" readonly></td>
        </tr>
        
        <tr>
            <td>店家：</td>
            <td>
                <select name="storeId">
                    <c:forEach var="storeVO" items="${storeSvc.all}">
                        <option value="${storeVO.storeId}"
                            <c:if test="${photoVO.storeId == storeVO.storeId}">selected</c:if>>
                            ${storeVO.name}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>原始圖片：</td>
            <td>
                <img src="${pageContext.request.contextPath}/photo/photo.do?action=showPhoto&photoId=<%= photoVO.getPhotoId() %>" width="200">
            </td>
        </tr>

        <tr>
            <td>上傳新圖片（可選）：</td>
            <td><input type="file" name="photoSrc"></td>
        </tr>

        <tr>
            <td colspan="2" style="text-align: center;">
                <input type="hidden" name="action" value="update">
                <input type="submit" value="確認修改">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
