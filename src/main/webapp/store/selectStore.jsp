<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Allieat: Store 管理</title>

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
  <tr><td><h3>Allieat: Store 管理</h3><h4>(MVC)</h4></td></tr>
</table>

<p>這是 Allieat 系統的店家管理首頁</p>

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
  <li><a href='listAllStore.jsp'>List</a> all Stores.<br><br></li>

  <li>
    <form method="post" action="store.do">
      <b>輸入店家編號:</b>
      <input type="text" name="storeId">
      <input type="hidden" name="action" value="getOneForDisplay">
      <input type="submit" value="送出">
    </form>
  </li>

  <jsp:useBean id="storeSvc" scope="page" class="com.store.model.StoreService" />

  <li>
    <form method="post" action="store.do">
      <b>選擇店家編號:</b>
      <select size="1" name="storeId">
        <c:forEach var="storeVO" items="${storeSvc.all}" >
          <option value="${storeVO.storeId}">${storeVO.storeId}</option>
        </c:forEach>
      </select>
      <input type="hidden" name="action" value="getOneForDisplay">
      <input type="submit" value="送出">
    </form>
  </li>

  <li>
    <form method="post" action="store.do">
      <b>選擇店家名稱:</b>
      <select size="1" name="storeId">
        <c:forEach var="storeVO" items="${storeSvc.all}" >
          <option value="${storeVO.storeId}">${storeVO.name}</option>
        </c:forEach>
      </select>
      <input type="hidden" name="action" value="getOneForDisplay">
      <input type="submit" value="送出">
    </form>
  </li>
</ul>

<h3>店家管理</h3>
<ul>
  <li><a href='addStore.jsp'>Add</a> a new Store</li>
</ul>

</body>
</html>