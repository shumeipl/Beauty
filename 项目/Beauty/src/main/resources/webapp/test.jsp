<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-08
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
我是测试页
<form action="/upload"enctype="multipart/form-data" method="post">
<input type="file" name="file" value="上传图片">
    <input type="submit" value="上传">
</form>
</body>
</html>
