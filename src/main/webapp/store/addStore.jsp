<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store.model.*"%>

<%
// 從 FoodServlet 取得 foodVO 物件 (若輸入錯誤則保留輸入值)
StoreVO storeVO = (StoreVO) request.getAttribute("storeVO");
%>
<html>
<head>
<title>新增店家</title>
</head>
<body>
<h2>新增店家資料</h2>

<%-- 錯誤訊息顯示 --%>
<c:if test="${not empty errorMsg}">
  <font color="red">請修正以下錯誤:</font>
  <ul>
    <c:forEach var="message" items="${errorMsg}">
      <li style="color:red">${message}</li>
    </c:forEach>
  </ul>
</c:if>

<form method="post" action="store.do">
  <input type="hidden" name="action" value="insert">

  店名：<input type="text" name="name" value="${storeVO.name}" /><br>
  負責人姓名：<input type="text" name="managerName" value="${storeVO.managerName}" /><br>
  Email：<input type="text" name="email" value="${storeVO.email}" /><br>
  密碼：<input type="password" name="password" value="${storeVO.password}" /><br>
  電話：<input type="text" name="phoneNum" value="${storeVO.phoneNum}" /><br>
  統一編號：<input type="text" name="guiNum" value="${storeVO.guiNum}" /><br>
  營業登記編號：<input type="text" name="businessRegNum" value="${storeVO.businessRegNum}" /><br>
  帳號狀態：<input type="text" name="accStat" value="${storeVO.accStat}" placeholder="0 或 1" /><br>
  營運狀態：<input type="text" name="opStat" value="${storeVO.opStat}" placeholder="0 或 1" /><br>
  開店時間（HH:mm）：<input type="text" name="opTime" value="${storeVO.opTime}" /><br>
  取餐時間（HH:mm）：<input type="text" name="pickTime" value="${storeVO.pickTime}" /><br>
  最晚下單（HH:mm）：<input type="text" name="lastOrder" value="${storeVO.lastOrder}" /><br>
  關店時間（HH:mm）：<input type="text" name="closeTime" value="${storeVO.closeTime}" /><br>
  地址：<input type="text" name="address" value="${storeVO.address}" /><br>
  縣市：<input type="text" name="county" value="${storeVO.county}" /><br>
  鄉鎮市區：<input type="text" name="district" value="${storeVO.district}" /><br>
  郵遞區號：<input type="text" name="postalCode" value="${storeVO.postalCode}" /><br>

  <input type="submit" value="新增店家">
</form>

</body>
</html>