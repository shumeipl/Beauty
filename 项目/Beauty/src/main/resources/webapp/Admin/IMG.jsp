<%@ page import="com.pengling.beauty.entity.Composition" %><%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-04-18
  Time: 1:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>生成图片</title>
  <link rel="stylesheet" href="../reset.css"/>
  <link rel="stylesheet" href="/webapp/Shopper/Order-pre.css"/>
  <script src="../jquery-3.6.3.min.js"></script>
</head>
<body>
<div class="content">
  <table>
    <thead>
    <th class="content-table-class01">成分名</th>
    <th class="content-table-class01">成分安全分</th>
    <th class="content-table-class01">活性成分</th>
    <th class="content-table-class01">致痘风险</th>
    <th class="content-table-class01">使用目的</th>
    <th class="content-table-class01">功效</th>
    </thead>
    <tbody id="tb">
    <c:forEach items="${compositions}" var="item">
      <tr>
      <td>item.compositionName</td>
      <td>item.compositionSecurityLow - item.compositionSecurityHigh</td>
      <td>item.compositionActivity</td>
      <td>item.compositionCauseAcne</td>
      <td>item.compositionAim</td>
      <td>item.compositionEndAim</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<script>
</script>
</body>
</html>
