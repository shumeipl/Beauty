<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-21
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>投诉管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <link rel="stylesheet" href="Complain.css"/>
</head>
<body>
<div>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">投诉管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-first" id="time-forward">日期正序</div>
    <div class="center-first" id="time-backward">日期倒序</div>
    <div class="center-second">
        <input type="text" id="username" placeholder="请输入"/>
        <div class="center-second-second">搜索</div>
    </div>
</div>
<div class="content">
    <table>
        <thead>
        <th class="content-table-class01">投诉号</th>
        <th class="content-table-class01">商品id</th>
        <th class="content-table-class01">用户id</th>
        <th class="content-table-class01">投诉内容</th>
        <th class="content-table-class01">商家名称</th>
        <th class="content-table-class02">商家电话</th>
        <th class="content-table-class01">投诉日期</th>
        </thead>
        <tbody id="tb">
        </tbody>
    </table>
</div>
</div>
<script>
    $(function(){
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
        let complains=[];
        getComplain();
        function getComplain(){
            $.ajax({
                url:"/GET/Complain/"+1,
                type:"get",
                dataType:"json",
                success:function (complainPage){
                    complains=[];
                    console.log(complainPage);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(complainPage.resultList,function (i,item) {
                        complains.push('<tr>' +
                            '<td>'+item.complainId+'</td>'+
                            '<td>' +item.goodId+
                            '</td><td>' +item.userId+
                            '</td><td>' +item.complainContent+
                            '</td><td>' +item.shopperName+
                            '</td><td>' +item.shopperTel+
                            '</td><td>' +item.complainDate+
                            '</td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(complains.join(''));
                }
            })};
            function getComplainByGood(){
                let username = $('#username').val();
                $.ajax({
                    url:"/GET/ComplainByGood/"+username+"/"+1,
                    type:"get",
                    dataType:"json",
                    success:function (complainPage){
                        complains=[];
                        // 遍历admins数组，将元素的数组元素填充模板的占位符
                        $.each(complainPage.resultList,function (i,item) {
                            complains.push('<tr>' +
                                '<td>'+item.complainId+'</td>'+
                                '<td>' +item.goodId+
                                '</td><td>' +item.userId+
                                '</td><td>' +item.complainContent+
                                '</td><td>' +item.shopperName+
                                '</td><td>' +item.shopperTel+
                                '</td><td>' +item.complainDate+
                                '</td>' +
                                '</tr>')
                        })
                        $('#tb').empty().append(complains.join(''));
                        $('#username').val('');
                    }
                });
        }
    })
</script>
</body>
</html>
