<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-21
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>留言管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <link rel="stylesheet" href="Message.css"/>
</head>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">留言管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-first" id="time-forward">日期正序</div>
    <div class="center-first" id="time-backward">日期倒序</div>
</div>
<div class="content">
    <table>
        <thead>
        <th class="content-table-class01">留言号</th>
        <th class="content-table-class01">用户名称</th>
        <th class="content-table-class01">联系电话</th>
        <th class="content-table-class01">留言内容</th>
        <th class="content-table-class01">留言时间</th>
        <th class="content-table-class02">操作</th>
        </thead>
        <tbody id="#tb">
        </tbody>
    </table>
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
        let messages=[];
        getMessageASC();
        function getMessageASC(){
            $.ajax({
               url:"/GET/MessagesASC/"+1,
                type:"get",
                dataType:"json",
                success:function (MessagePage) {
                    messages=[];
                    console.log(MessagePage);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(MessagePage.resultList,function (i,item) {
                        messages.push('<tr>' +
                            '<td>'+item.messageId+'</td>'+
                            '<td>' +item.userId+
                            '</td><td>' +item.messageContent+
                            '</td><td>' +item.messageDate+
                            '</td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(messages.join(''));
                }

            });
        };
        function getMessageDESC(){
            $.ajax({
                url:"/GET/MessagesDESC/"+1,
                type:"get",
                dataType:"json",
                success:function (MessagePage) {
                    messages=[];
                    console.log(MessagePage);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(MessagePage.resultList,function (i,item) {
                        messages.push('<tr>' +
                            '<td>'+item.messageId+'</td>'+
                            '<td>' +item.userId+
                            '</td><td>' +item.messageContent+
                            '</td><td>' +item.messageDate+
                            '</td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(messages.join(''));
                }

            });
        }
    })
</script>
</body>
</html>
