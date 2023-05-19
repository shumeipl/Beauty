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
    <link rel="stylesheet" href="Order-pre.css"/>
    <style>
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
            margin-top: 8px;
            width: 70px;
            height: 30px;
            margin-left: 5px;
            font-weight: bold;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: small;
            padding: 5px 10px;
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
            width: 150px;
            background-color: white;
            text-align: center;
            font-weight: 500;
            font-size: small;
            height: 30px;
            line-height:30px ;
            color: gray;
        }
        td{
            font-size: small;
            padding: 6px;
            text-align: center;
            height: 25px;
            line-height: 25px;
        }
        .address{
            width: 100px;
            height: 25px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .name{
            width: 100px;
            height: 25px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        </style>
</head>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">卖家中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">订单管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-first" id="back">返回</div>
    <div class="center-second">
        <input type="text" class="input" name="username" placeholder="请输入"/>
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
        <th class="content-table-class01">收货人</th>
        <th class="content-table-class01">收货地址</th>
        <th class="content-table-class01">订单日期</th>
        <th class="content-table-class01">操作</th>
        </thead>
        <tbody id="tb">
        </tbody>
    </table>
</div>
<div id="eject-box01" class="eject-box01">
    <div id="eject-head">
        <div id="eject-head-text" class="eject-head-text">输入快递单号</div>
        <div id="eject-head-cancel" class="eject-head-cancel">×</div>
    </div>
    <div id="eject-box02">
        <div class="text1">承运商</div>
        <div style="display: flex;">
            <div id="img-icon">
                <img id="img-img" src="#"/>
            </div>
            <select id="tracking-shopper"  name="tracking-shopper">
                <option class="option1" value=""></option>
                <option class="option1" value="../image/天猫-顺丰包邮.png">顺丰</option>
                <option class="option2" value="../image/圆通速递.png" >圆通</option>
                <option class="option3" value="../image/邮政快递包裹 图标制作模版.png">邮政</option>
            </select>
        </div>
        <div class="text1">快递单号</div>
        <input id="tracking-num" name="tracking-num" type="text" placeholder="输入快递单号"/>
        <input id="submit" type="submit" value="提交" />
    </div>
</div>
<!-- -->
<div id="eject-box01-01" class="eject-box01">
    <div id="eject-head-01">
        <div id="eject-head-text-01" class="eject-head-text">完成</div>
        <div id="eject-head-cancel-01" class="eject-head-cancel">×</div>
    </div>
    <img style="width: 300px; height: 300px;" src="../image/对钩.gif"/>
</div>
</div>
<!--  -->
<script>
    $(function(){
        getOrder();
        let orders=[];
        function getOrder(){
            $.ajax({
                url:"/GET/OrderOrderBeforeFH/1",
                type:"get",
                dataType:"json",
                success:function (Orders){
                    orders=[];
                    console.log(Orders);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(Orders.resultList,function (i,item) {
                        orders.push('<tr>' +
                            '<td>'+item.orderHao+'</td>'+
                            '<td>' +item.userId+'</td>'+
                            '<td>' +item.goodId+
                            '</td><td><div  class="name"> ' +item.goodName+
                            '</div></td><td>' +item.goodNum+
                            '</td><td>' +item.addressConsignee+
                            '</td><td><div class="address">' +item.provinceName+''+item.cityName+''+item.districtName+''+item.detailAddress+
                            '</div></td><td>' +item.orderDate+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.orderId+'"  type="button" value="发货"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(orders.join(''))

                }
            })};

        //
        $("#eject-box01-01").hide();
        $('#eject-box01').hide();
        $('#img-img').hide();
        $('#tracking-shopper').change(function(){
            $('#img-img').show();
            var options =$('#tracking-shopper');
            $('#img-img').attr('src',options.val());
        });
        $('#eject-head-cancel').click(function(){
            $('#eject-box01').hide();
        });
        $('#submit').click(function(e){
            let trackingNum = $('#tracking-num').val();
            let trackingShopperImg=$('#tracking-shopper').val();
            let trackingShopper="";
            if (trackingShopperImg==='../image/圆通速递.png'){
                trackingShopper='圆通速递';
            }
            else if (trackingShopperImg==='../image/天猫-顺丰包邮.png'){
                trackingShopper='顺丰速运';
            }
            else if (trackingShopperImg==='../image/邮政快递包裹 图标制作模版.png'){
                trackingShopper='邮政快递';
            }
            console.log(trackingShopperImg);
            console.log(trackingShopper)
            $.ajax({
                url:"/PATCH/OrderOrderBeforeFH/",
                type:"post",
                dataType:"json",
                data:{orderId:id,trackingNum:trackingNum,trackingShopper:trackingShopper},
                success:function (res) {
                    if (res === 1){
                        $('#eject-box01').hide();
                        $("#eject-box01-01").show();
                        getOrder();
                    }
                }
            });
        });
        $("#eject-head-cancel-01").click(function(){
            $("#eject-box01-01").hide();
        });
        //
        let id=0;
        $('#tb').click(function (e){
            //管理员id
            if(e.target.className==="content-table-delete") {
                id = e.target.name;
                $('#eject-box01').show();

            }
        });

        $('.center-second-second').click(function (){
            let username = $('#username').val();
            $.ajax({
                url :"/GET/OrderBeforeFHById/"+username,
                type:"get",
                dataType:"json",
                success:function (userList){
                    orders=[];
                    console.log(userList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(userList.resultList,function (i,item) {
                        orders.push('<tr>' +
                            '<td>'+item.orderHao+'</td>'+
                            '<td>' +item.userId+
                            '<td>' +item.goodId+
                            '</td><td>' +item.goodName+
                            '</td><td>' +item.goodNum+
                            '</td><td>' +item.addressName+
                            '</td><td>' +item.addressTel+
                            '</td><td>' +item.orderDate+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.orderId+'"  type="button" value="发货"/></div></td>' +
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
