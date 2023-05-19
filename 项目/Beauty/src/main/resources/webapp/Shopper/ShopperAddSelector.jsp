<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-03-30
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品添加选择页面</title>
    <link rel="stylesheet" href="../reset.css">
    <script src="../jquery-3.6.3.min.js"></script>
    <style>
        #left,#right{
            margin-left: 80px;
            height: 250px;
            width: 250px;
            display: flex;
            justify-content: center;
            line-height: 250px;
            font-size: 30px;
            background-color: cornflowerblue;
            border-radius: 25px;
            color: aliceblue;
        }
        #container{
            display: flex;
        }
        #left:hover,#right:hover{
            background-color: blue;
        }
    </style>
</head>
<body>
<div id="container">
    <div id="left">简单商品</div>
    <div id="right">复杂商品</div>
</div>
<script>
    $(function(){
        $('#left').click(function(){
            location.href="../ShopperAddGood.jsp";
        });
        $('#right').click(function(){
            location.href="./ShopperAddGoodComplex.jsp";
        });
    })
</script>
</body>
</html>
