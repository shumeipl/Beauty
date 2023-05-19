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
    <link rel="stylesheet" href="Comment.css"/>
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
    </style>
</head>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">评价管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-first" id="time-forward">日期正序</div>
    <div class="center-first" id="time-backward">日期倒序</div>
</div>
<div class="content">
    <table>
        <thead>
        <th class="content-table-class01">评价号</th>
        <th class="content-table-class01">用户ID</th>
        <th class="content-table-class01">商品ID</th>
        <th class="content-table-class01">评价星级</th>
        <th class="content-table-class01">评价内容</th>
        <th class="content-table-class01">评价时间</th>
        <th class="content-table-class02">操作</th>
        </thead>
        <tbody id="tb">
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
        getCommentASC();
        function getCommentASC(){
            $.ajax({
                url:"/GET/Comment/"+1+'/'+${sessionScope.shopperId},
                type:"get",
                dataType:"json",
                success:function (CommentPage) {
                    messages=[];
                    console.log(CommentPage);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(CommentPage.resultList,function (i,item) {
                        messages.push('<tr>' +
                            '<td>'+item.commentId+'</td>'+
                            '<td>' +item.userId+
                            '</td><td>' +item.goodId+
                            '</td><td>' +item.commentLevel+
                            '</td><td>' +item.commentContent+
                            '</td><td>' +item.commentDate+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-edit" name="'+item.commentId+'"  type="button" value="回复"/></div></td>' +
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
                    $.each(MessagePage.resultList,function (i,item) {
                        messages.push('<tr>' +
                            '<td>'+item.commentId+'</td>'+
                            '<td>' +item.userId+
                            '</td><td>' +item.goodId+
                            '</td><td>' +item.commentLevel+
                            '</td><td>' +item.commentContent+
                            '</td><td>' +item.commentDate+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-edit" name="'+item.commentId+'"  type="button" value="回复"/></div></td>' +
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
