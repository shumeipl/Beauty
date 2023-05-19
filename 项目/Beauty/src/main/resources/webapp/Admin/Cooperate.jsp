<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-21
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>合作管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <link rel="stylesheet" href="Complain.css"/>
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
        .content-table-delete{
            border: 1px solid #D24D57;
            background-color: #D24D57;
            width: 50px;
            height: 25px;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: small;
            padding: 5px 10px;
            outline: none;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
            transition: .3s;
        }
        .content-table-edit{
            border: 1px solid whitesmoke;
            background-color: white;
            width: 50px;
            border-radius: 10%;
        }
        .content-table-edit:hover{
            border: 1px solid whitesmoke;
            background-color: blue;
            color: white;
            width: 50px;
            border-radius: 10%;
        }
        .content-table-operation{
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            padding: 0 30px;
        }
    </style>
</head>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">合作管理</div>--%>
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
        <th class="content-table-class01">合作ID</th>
        <th class="content-table-class01">用户账号</th>
        <th class="content-table-class01">姓名</th>
        <th class="content-table-class02">所在公司</th>
        <th class="content-table-class02">公司地址</th>
        <th class="content-table-class02">所在职位</th>
        <th class="content-table-class01">联系电话</th>
        <th class="content-table-class02">申请日期</th>
        <th class="content-table-class02">操作</th>
        </thead>
        <tbody id="tb">
        </tbody>
    </table>
</div>
<script>
<%--    查询未被处理的， 已同意，已拒绝--%>
    $(function(){
       let cooperations = [];
       getCooperations();
       function getCooperations() {
           $.ajax({
               url:"/GET/Cooperations/"+1,
               type:"get",
               dataType:"json",
               success:function (res){
                   cooperations=[];
                   $.each(res.resultList,function (i,item) {
                       cooperations.push('<tr>' +
                           '<td>'+item.id+'</td>'+
                           '<td>'+item.userId+'</td>'+
                           '<td>' +item.name+
                           '</td><td>' +item.company+
                           '</td><td>' +item.companyAddress+
                           '</td><td>' +item.duty+
                           '</td><td>' +item.phone+
                           '</td><td>' +item.date+
                           '</td><td><div class="content-table-operation">' +
                           '<input class="content-table-edit"  name="'+item.id+'"  type="button" value="允许"/>' +
                           '<input class="content-table-delete"  name="'+item.id+'"  type="button" value="拒绝"/></div></td>' +
                           '</tr>')
                   })
                   $('#tb').empty().append(cooperations.join(''));
               }
           });
               $('#tb').click(function (e){
                   //管理员id
                   console.log(e.target.name);
                   if (e.target.className==='content-table-delete'){
                       $.ajax({
                           url:"/GET/RefuseCooperations",
                           type:"post",
                           data:{cooperationId:e.target.name},
                           dataType: "json",
                           success:function (res){
                               if (res===1){
                                   getCooperations();
                               }
                           }


                       });
                   }

                   else if(e.target.className==='content-table-edit'){
                       $.ajax({
                           url:"/PUT/AcceptCooperations",
                           type:"post",
                           data:{cooperationId:e.target.name},
                           dataType: "json",
                           success:function (res){
                               if (res===1){
                                   getCooperations();
                               }
                           }


                       });
                   }
               });
           }

    })
</script>
</body>
</html>
