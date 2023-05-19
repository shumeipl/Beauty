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
    <title>用户管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <link rel="stylesheet" href="User-user.css"/>
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
            margin: 10px 20%;
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
</head>
<body>
<%--<div class="FY">--%>
<%--    <div class="FY-dec" id="dec">-</div>--%>
<%--    <input id="currentPage" onchange="getNumber" name="currentPage" value="1"/>--%>
<%--    <div class="FY-inc" id="inc">+</div>--%>
<%--    <div class="summary">共<div id="all"></div>页</div>--%>
<%--</div>--%>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">用户管理</div>--%>
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
            <th class="content-table-class01">账号</th>
            <th class="content-table-class01">昵称</th>
            <th class="content-table-class01">性别</th>
        </thead>
        <tbody id="tb"></tbody>
    </table>
</div>
<script>
    $(function(){
        var currentPage=1;
        var max=0;
        function getNumber(){
            var val = $('#currentPage').val();
            currentPage=val;
            getUsersASC()
        }
            $('#dec').click(function(){
                var val = $('#currentPage').val();
                if(val>1){
                    var val1 = val-1;
                    $('#currentPage').val(val1)
                    currentPage=val1;
                    getUsersASC()
                }
            });
            $('#inc').click(function(){
                var val = $('#currentPage').val();
                if(val<max){
                    var val1 = val-0+1;
                    $('#currentPage').val(val1);
                    currentPage=val1;
                    getUsersASC()
                }
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
            let users=[];
            function getUsersASC(){
            users=[];
                $.ajax({
                    url:"/GET/UserASC/"+1,
                    type:"get",
                    dataType:"json",
                    success:function (Users){
                        max=Users.pageCount;
                        console.log(Users);
                        $('#all').val(max);
                        // 遍历admins数组，将元素的数组元素填充模板的占位符
                        $.each(Users.resultList,function (i,item) {
                            users.push('<tr>' +
                                '<td>'+item.userId+'</td>'+
                                '<td>' +item.userNickname+
                                '</td><td>' +item.userGender+
                                '</td>' +
                                '</tr>')
                        })
                        $('#tb').empty().append(users.join(''))

                    }
                })};
        $('#time-backward').click(function (){
            getUsersDESC();
        });
        $('#time-forward').click(function (){
            getUsersASC();
        });
        getUsersASC();

        $('.center-second-second').click(function (){
            let username = $('#username').val();
            $.ajax({
                url :"/GET/User/UserId/"+username,
                type:"get",
                dataType:"json",
                success:function (userList){
                    users=[];
                    console.log(userList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(userList.resultList,function (i,item) {
                        users.push('<tr>' +
                            '<td>'+item.userId+'</td>'+
                            '<td>' +item.userNickname+
                            '</td><td>' +item.userSex+
                            '</td></tr>')
                    })
                    $('#tb').empty().append(users.join(''));
                    $('#username').val('');
                }
            });
        });
    })
</script>
</body>
</html>
