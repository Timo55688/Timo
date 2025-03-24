<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>店家照片 - listOnePhoto.jsp</title>
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
        table {
            width: 600px;
            background-color: white;
            margin-top: 5px;
            margin-bottom: 5px;
        }
        table, th, td {
            border: 1px solid #CCCCFF;
        }
        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body bgcolor='white'>

<h4>此頁使用 JSTL & EL 寫法:</h4>
<table id="table-1">
    <tr>
        <td>
            <h3>店家照片 - listOnePhoto.jsp</h3>
            <h4><a href="selectPhoto.jsp">回首頁</a></h4>
        </td>
    </tr>
</table>

<c:choose>
    <c:when test="${not empty photoVO}">
        <table>
            <tr>
                <th>圖片編號</th>
                <th>店家編號</th>
                <th>建立時間</th>
                <th>圖片預覽</th>
            </tr>
            <tr>
                <td>${photoVO.photoId}</td>
                <td>${photoVO.storeId}</td>
                <td>${photoVO.updateTime}</td>
                <td>
                    <img src="${pageContext.request.contextPath}/photo/photo.do?action=showPhoto&photoId=${photoVO.photoId}" width="200">
                </td>
            </tr>
        </table>
    </c:when>
    <c:otherwise>
        <h3 style="color:red;">查無資料，請確認輸入是否正確。</h3>
        <a href="selectPhoto.jsp">回選擇頁</a></div>
    </c:otherwise>
</c:choose>

</body>
</html>
