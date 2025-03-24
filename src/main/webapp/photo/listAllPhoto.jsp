<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.photo.model.*"%>

<%-- 此頁使用 EL 來取值 --%>
<%
    PhotoService photoSvc = new PhotoService();
    List<PhotoVO> list = photoSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有店家照片資料 - listAllPhoto.jsp</title>

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

<style>
  table {
	width: 800px;
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

<h4>此頁使用 EL 來取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有店家照片資料 - listAllPhoto.jsp</h3>
		 <h4><a href="selectPhoto.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>照片編號</th>
		<th>店家編號</th>
		<th>圖片預覽</th>
		<th>創建日期</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="photoVO" items="${list}">
		<tr>
			<td>${photoVO.photoId}</td>
			<td>${photoVO.storeId}</td>
			<td><img src="${pageContext.request.contextPath}/photo/photo.do?action=showPhoto&photoId=${photoVO.photoId}" width="200"></td>
			<td>${photoVO.updateTime}</td>
			<td>
			  <form method="post" action="${pageContext.request.contextPath}/photo/photo.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="photoId" value="${photoVO.photoId}">
			     <input type="hidden" name="action" value="getOneForUpdate">
			  </form>
			</td>
			<td>
			  <form method="post" action="${pageContext.request.contextPath}/photo/photo.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="photoId" value="${photoVO.photoId}">
			     <input type="hidden" name="action" value="delete">
			  </form>
			</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>
