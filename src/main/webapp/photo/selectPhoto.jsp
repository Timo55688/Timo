<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Allieat: 店家照片查詢</title>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
   <tr><td><h3>Allieat: 店家照片管理</h3><h4>(MVC)</h4></td></tr>
</table>

<p>This is the Home page for Store Photo Management</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsg}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsg}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllPhoto.jsp'>List</a> all Store Photos.<br><br></li>

  <li>
    <form method="post" action="photo.do">
      <b>輸入圖片編號:</b>
      <input type="text" name="photoId">
      <input type="hidden" name="action" value="getOneForDisplay">
      <input type="submit" value="送出">
    </form>
  </li>

  <jsp:useBean id="photoSvc" scope="page" class="com.photo.model.PhotoService" />
  <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

  <li>
    <form method="post" action="photo.do">
      <b>選擇圖片編號:</b>
      <select name="photoId">
        <c:forEach var="photoVO" items="${photoSvc.all}">
          <option value="${photoVO.photoId}">${photoVO.photoId}</option>
        </c:forEach>
      </select>
      <input type="hidden" name="action" value="getOneForDisplay">
      <input type="submit" value="送出">
    </form>
  </li>

  <li>
    <form method="post" action="photo.do">
      <b>選擇店家名稱:</b>
      <select name="photoId">
        <c:forEach var="photoVO" items="${photoSvc.all}">
          <c:forEach var="storeVO" items="${storeSvc.all}">
            <c:if test="${photoVO.storeId == storeVO.storeId}">
              <option value="${photoVO.photoId}">${storeVO.name}</option>
            </c:if>
          </c:forEach>
        </c:forEach>
      </select>
      <input type="hidden" name="action" value="getOneForDisplay">
      <input type="submit" value="送出">
    </form>
  </li>
</ul>

<h3>店家照片管理</h3>

<ul>
  <li><a href='addPhoto.jsp'>Add</a> a new Photo</li>
</ul>

</body>
</html>
