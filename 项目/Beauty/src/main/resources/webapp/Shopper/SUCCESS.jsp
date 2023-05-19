<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-04-17
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<style>
    .success-image{
        width: 100px;
        height: 100px;
        margin: 0 auto;
    }
</style>
<body>
<div style="margin: 0 auto; display: flex; margin-top: 230px;">
    <img src="/image/成功.png" class="success-image"/>
</div>
<div style="font-size: 30px;display: flex;margin-top: 10px;justify-content: center;">SUCCESS</div>
<script src="../jquery-3.6.3.min.js">
    $(function () {
        goto();
        function goto() {
            window.location("/ShopperGoodMan.jsp");
        }
    })
</script>
</body>
</html>
