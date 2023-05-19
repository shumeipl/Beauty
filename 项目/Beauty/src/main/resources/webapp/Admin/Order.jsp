<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-21
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <link rel="stylesheet" href="Order.css"/>
</head>
<style>
    .content-table-class01{
        width: 100px;
        text-align: center;
        font-weight: 500;
        font-size: small;
        height: 30px;
        line-height:30px ;
        background-color: white;
        color: gray;
        padding: 5px;

    }
    .content-table-class02{
        width: 300px;
        background-color: white;
        text-align: center;
        font-weight: 500;
        font-size: small;
        height: 30px;
        line-height:30px ;
        color: gray;
    }
    .center-second{
        display: flex;
        height: 30px;
        margin-top:30px;
        margin-left: 750px;
    }
    .center-second input{
        height: 30px;
        text-align: left;
    }

    .center-second-second{
        width: 60px;
        height: 25px;
        margin-left: 5px;
        font-weight: bold;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: small;
        padding: 2px 5px;
        outline: none;
        background-color: #5073fb;
        color: #fff;
        border-radius: 5px;
        cursor: pointer;
        transition: .3s;
    }
    table{
        margin: 10px 30px;
        background-color: white;
        border: 1px solid gainsboro;
    }
    td{
        font-size: small;
        padding: 6px;
        text-align: center;
        height: 25px;
        line-height: 25px;
    }
</style>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">订单管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-second">
        <input type="text" name="username" placeholder="请输入"/>
        <div class="center-second-second">搜索</div>
    </div>
</div>
<div class="content">
    <table>
        <thead>
        <th class="content-table-class01">订单号</th>
        <th class="content-table-class01">用户id</th>
        <th class="content-table-class01">商品id</th>
        <th class="content-table-class01">商品名称</th>
        <th class="content-table-class01">商品数量</th>
        <th class="content-table-class01">商家名称</th>
        <th class="content-table-class01">商家电话</th>
        <th class="content-table-class01">订单日期</th>
        <th class="content-table-class01">快递单号</th>
        </thead>
        <tbody id="tb">
        </tbody>
    </table>
</div>
<script>
    $(function(){
        let orders=[];
        getOrdersASC()
        function getOrdersASC(){
            $.ajax({
                url:"/GET/Order/"+1,
                type:"get",
                dataType:"json",
                success:function (Orders){
                    console.log(Orders);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(Orders.resultList,function (i,item) {
                        orders.push('<tr>' +
                            '<td>'+item.orderHao+'</td>'+
                            '<td>' +item.userId+
                            '<td>' +item.goodId+
                            '</td><td>' +item.goodName+
                            '</td><td>' +item.goodNum+
                            '</td><td>' +item.shopperName+
                            '</td><td>' +item.shopperTel+
                            '</td><td>' +item.orderDate+
                            '</td><td>' +item.orderTrackingNum+
                            '</td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(orders.join(''))

                }
            })};

        $('.center-second-second').click(function (){
            let username = $('#username').val();
            $.ajax({
                url :"/GET/OrderById/"+username,
                type:"get",
                dataType:"json",
                success:function (userList){
                    shoppers=[];
                    console.log(userList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(userList.resultList,function (i,item) {
                        shoppers.push('<tr>' +
                            '<td>'+item.orderHao+'</td>'+
                            '<td>' +item.userId+
                            '<td>' +item.goodId+
                            '</td><td>' +item.goodName+
                            '</td><td>' +item.goodNum+
                            '</td><td>' +item.shopperName+
                            '</td><td>' +item.shopperTel+
                            '</td><td>' +item.orderDate+
                            '</td><td>' +item.orderTrackingNum+
                            '</td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(orders.join(''));
                    $('#username').val('');
                }
            });
        });
    })
</script>
</body>
</html>
