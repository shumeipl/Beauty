<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-21
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>商家管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <link rel="stylesheet" href="User-Shopper.css"/>
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
<%--    <div class="header-thirdText">商家管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-second">
        <input type="text" id="username" placeholder="请输入"/>
        <div class="center-second-second">搜索</div>
    </div>
</div>
<div class="content">
    <table>
        <thead>
             <th class="content-table-class01">店铺id</th>
            <th class="content-table-class01">店铺头像</th>
            <th class="content-table-class01">店铺名称</th>
            <th class="content-table-class01">联系电话</th>
            <th class="content-table-class01">身份证号</th>
            <th class="content-table-class02">通讯地址</th>
            <th class="content-table-class02">操作</th>
        </thead>
        <tbody id="tb"></tbody>
    </table>
</div>
<%--<div class="box-01">--%>
<%--    <div style="color: gainsboro;font-size: larger;margin-left: 430px;" id="remove-addUser">×</div>--%>
<%--    <div class="box-02">--%>
<%--        <div style="font-weight: bold;text-align: center;margin-top: 20px;">新增商家</div>--%>
<%--        <div>--%>
<%--            <div class="box-03">--%>
<%--                <div>店铺头像</div><input class="input-v"  type="text" placeholder="请输入" name="name"/>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <div>--%>
<%--                <div class="box-03">--%>
<%--                    <div>店铺名</div><input class="input-v"  type="text" placeholder="请输入" name="age"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <div class="box-03">--%>
<%--                    <div>商品类别</div><input class="input-v" type="text" placeholder="请输入" name="sex"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <div class="box-03">--%>
<%--                    <div>商家姓名</div><input class="input-v" type="text" placeholder="请输入" name="birthday"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div>--%>
<%--                <div class="box-03">--%>
<%--                    <div>联系电话</div><input class="input-v" type="text" placeholder="请输入" name="address"/>--%>
<%--                </div>--%>
<%--                <div class="box-03">--%>
<%--                    <div>身份证号</div><input class="input-v" type="text" placeholder="请输入" name="birthday"/>--%>
<%--                </div>--%>
<%--                <div class="box-03">--%>
<%--                    <div>收货地址</div><input class="input-v" type="text" placeholder="请输入" name="address"/>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <input  type="button" value="新增商家" class="box-03-button"/>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<script>
    $(function(){
        $('#remove-addUser').click(function(){
            $('.box-01').hide();
        });
        $('#addUser').click(function(){
            $('.box-01').show();
        });
        $('#time-forward').css('background-color','blue');
        $('#time-backward').css('background-color','#6495ed');
        $('#time-forward').click(function(){
            $('#time-forward').css('background-color','blue');
            $('#time-backward').css('background-color','#6495ed');
        });
        $('#time-backward').click(function(){
            $('#time-forward').css('background-color','#6495ed');
            $('#time-backward').css('background-color','blue');
        });
        let shoppers=[];
        function getShoppersDESC(){
            $.ajax({
                url:"/GET/shoppers/DESC/"+1,
                type:"get",
                dataType:"json",
                success:function (Shoppers){
                    shoppers=[];
                    console.log(Shoppers);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(Shoppers.resultList,function (i,item) {
                        users.push('<tr>' +
                            '<td>'+item.shopperId+'</td>'+
                            '<td>' +item.shopperAvatar+
                            '<td>' +item.shopperName+
                            '</td><td>' +item.shopperTel+
                            '</td><td>' +item.shopperIdCard+
                            '</td><td>' +item.shopperAddress+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.shopperId+'"  type="button" value="删除"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(shoppers.join(''))

                }
            })}
        function getShoppersASC(){
            shoppers=[];
            $.ajax({
                url:"/GET/shoppers/ASC/"+1,
                type:"get",
                dataType:"json",
                success:function (Shoppers){
                    console.log(Shoppers);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(Shoppers.resultList,function (i,item) {
                        shoppers.push('<tr>' +
                            '<td>'+item.shopperId+'</td>'+
                            '<td>' +item.shopperAvatar+
                            '<td>' +item.shopperName+
                            '</td><td>' +item.shopperTel+
                            '</td><td>' +item.shopperIdCard+
                            '</td><td>' +item.shopperAddress+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.shopperId+'"  type="button" value="删除"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(shoppers.join(''))

                }
            })};
        $('#time-backward').click(function (){
            getShoppersDESC();
        });
        getShoppersASC();

        $('.center-second-second').click(function (){
            let username = $('#username').val();
            $.ajax({
                url :"/GET/User/ShopperId/"+username,
                type:"get",
                dataType:"json",
                success:function (userList){
                    shoppers=[];
                    console.log(userList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(userList.resultList,function (i,item) {
                        shoppers.push('<tr>' +
                            '<td>'+item.shopperId+'</td>'+
                            '<td>' +item.shopperAvatar+
                            '<td>' +item.shopperName+
                            '</td><td>' +item.shopperTel+
                            '</td><td>' +item.shopperIdCard+
                            '</td><td>' +item.shopperAddress+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.shopperId+'"  type="button" value="删除"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(shoppers.join(''));
                    $('#username').val('');
                }
            });
        });
        //删除管理员
        $('#tb').click(function (e){
            //管理员id
            if(e.target.className==="content-table-delete") {
                $.ajax({
                    url: "/DELETE/shopper" + e.target.name,
                    type: "post",
                    data: {shopperOpenid: e.target.name},
                    dataType: "json",
                    success: function (res) {
                        if (res === 1) {
                            shoppers = [];
                            getShoppersASC();
                        }
                    }

                });
            }
        });
    })
</script>
</body>
</html>
