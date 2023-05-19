<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-21
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>已上架管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <link rel="stylesheet" href="Good-Check.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
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
            height: 84px;
            line-height: 84px;
        }
    </style>
</head>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">已上架商品管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-second">
        <input type="text" id="username" placeholder="商品id"/>
        <div class="center-second-second">搜索</div>
    </div>
</div>
<div class="content">
    <table>
        <thead>
        <th class="content-table-class01">商品id</th>
        <th class="content-table-class01">商品图片</th>
        <th class="content-table-class01">商家id</th>
        <th class="content-table-class01">商品名称</th>
        <th class="content-table-class01">商品分类</th>
        <th class="content-table-class01">商品价格</th>
        <th class="content-table-class01">商品库存</th>
        <th class="content-table-class01">商品成分</th>
        <th class="content-table-class01">商品材料</th>
        <th class="content-table-class02">操作</th>
        </thead>
        <tbody id="tb"></tbody>
    </table>
</div>
<script>
    $(function(){
        let goods=[];
        getGoods();
        function getGoods(){
            $.ajax({
                url:"/GET/GoodRoost/"+1,
                type:"get",
                dataType:"json",
                success:function (goodPage){
                    console.log(goodPage);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(goodPage,function (i,item) {
                        goods.push('<tr>' +
                            '<td>'+item.goodId+'</td>'+
                            '<td><img class="img1" src= ' +item.goodImg1+'>'+
                            '</td><td>' +item.shopperId+
                            '</td><td>' +item.goodName+
                            '</td><td>' +item.categoryName+'/'+item.smallCategoryName +
                            '</td><td>' +item.goodPrice+
                            '</td><td>' +item.goodStorage+
                            '</td><td><img class="img1" src=' +item.goodDetail+'>'+
                            '</td><td><img class="img1" src=' +item.material+ '>'+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.goodId+'"  type="button" value="下架"/></div>' +
                            '<input class="content-table-edit" name="'+item.goodId+'"  type="button" value="成为精选"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(goods.join(''));
                    $('.img1').css("width","80px");
                    $('.img1').css("height","80px");
                    $('.img1').css("padding","5px");

                }
            })

        }
        $('#remove-addUser').click(function(){
            $('.box-01').hide();
        });
        $('#remove-eidtUser').click(function(){
            $('.edit-01').hide();
        });
        $('#addUser').click(function(){
            $('.box-01').show();
        });
        $('#tb').click(function (){
            $('.edit-01').show();
        });
        $('#back').click(function (){
            getGoods();
        });
        //搜索管理员
        $('.center-second-second').click(function (){
            let username = $('#username').val();
            $.ajax({
                url :"/GET/GoodRoost/"+username,
                type:"get",
                dataType:"json",
                success:function (goodList){
                    goods=[];
                    console.log(goodList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(goodList,function (i,item) {
                        goods.push('<tr>' +
                            '<td>'+item.goodId+'</td>'+
                            '<td><img class="img1" src= ' +item.goodImg1+'>'+
                            '</td><td>' +item.shopperId+
                            '</td><td>' +item.goodName+
                            '</td><td>' +item.categoryName+'/'+item.smallCategoryName +
                            '</td><td>' +item.goodPrice+
                            '</td><td>' +item.goodStorage+
                            '</td><td><img class="img1" src=' +item.goodDetail+'>'+
                            '</td><td><img class="img1" src=' +item.material+ '>'+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.goodId+'"  type="button" value="下架"/></div>' +
                            '<input class="content-table-edit" name="'+item.goodId+'"  type="button" value="成为精选"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(goods.join(''));
                    $('.img1').css("width","80px");
                    $('.img1').css("height","80px");
                    $('.img1').css("padding","5px");
                    $('#username').val('');
                }
            });
        });
        $('#tb').click(function (e){
            //管理员id
            console.log(e.target.name);
            if (e.target.className==='content-table-delete'){
                $.ajax({
                    url:"/DELETE/GoodRoost",
                    type:"post",
                    data:{goodId:e.target.name},
                    dataType: "json",
                    success:function (res){
                        if (res===1){
                            goods=[];
                            getGoods();
                        }
                    }


                });
            }
            if (e.target.className==='content-table-edit'){
                $.ajax({
                    url:'/PATCH/becomeSelected',
                    type:'post',
                    data:{goodId:e.target.name},
                    dataType: "json",
                    success:function (res) {

                    }

                })
            }
        });
    })

</script>
</body>
</html>
