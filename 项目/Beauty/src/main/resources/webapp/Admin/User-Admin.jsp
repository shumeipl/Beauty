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
    <title>管理员管理</title>
    <link rel="stylesheet" href="../reset.css"/>
    <link rel="stylesheet" href="User-Admin.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
</head>
<style>
    .center .center-first{
        margin: 20px;
        cursor: pointer;
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
<%--    <div class="header-thirdText">管理员管理</div>--%>
<%--</div>--%>
<div class="center">
    <div class="center-first" id="back">&lt;返回</div>
    <div class="center-first" id="addUser">＋新增</div>
    <div class="center-second">
        <input type="text" id="username" placeholder="请输入账号或姓名"/>
        <div class="center-second-second">搜索</div>
    </div>
</div>
<div class="content">
    <table>
        <thead>
            <th class="content-table-class01">账号</th>
            <th class="content-table-class01">姓名</th>
            <th class="content-table-class01">身份证号</th>
            <th class="content-table-class01">联系电话</th>
            <th class="content-table-class02">操作</th>
        </thead>
        <tbody id="tb"></tbody>
    </table>
</div>
<div class="box-01">
    <div style="color: gainsboro;font-size: larger;margin-left: 430px;" id="remove-addUser">×</div>
    <div class="box-02">
        <div style="font-weight: bold;text-align: center;margin-top: 20px;">新增管理员</div>
        <div>
            <div class="box-03">
                <div>姓名</div><input class="input-v"  type="text" placeholder="请输入" id="name"/>
            </div>
        </div>
        <div>
            <div>
            </div>
            <div>
                <div class="box-03">
                    <div>身份证号</div><input class="input-v" type="text" placeholder="请输入" id="idcard"/>
                </div>
                <div class="box-03">
                    <div>性别</div><input class="input-v"  type="text" placeholder="请输入" id="sex"/>
                </div>
            </div>
            <div>
                <div class="box-03">
                    <div>联系电话</div><input class="input-v" type="text" placeholder="请输入" id="tel"/>
                </div>
            </div>
            <div>
                <div class="box-03">
                    <div>通讯地址</div><input class="input-v" type="text" placeholder="请输入" id="address"/>
                </div>
            </div>
            <input  type="button"  value="新增管理员" class="box-03-button"/>
        </div>
    </div>
</div>
<script>
    $(function(){
        let admins=[];
        function getAdmins(){
            $.ajax({
                url:"/GET/Admin/1",
                type:"get",
                dataType:"json",
                success:function (adminList){
                    console.log(adminList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(adminList.resultList,function (i,item) {
                        admins.push('<tr>' +
                            '<td>'+item.adminId+'</td>'+
                            '<td>' +item.adminName+
                            '</td><td>' +item.adminIdcard+
                            '</td><td>' +item.adminTel+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.adminId+'"  type="button" value="删除"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(admins.join(''))

                }
            })

        }
        getAdmins();
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
            getAdmins();
        });
        //搜索管理员
        $('.center-second-second').click(function (){
            let username = $('#username').val();
            $.ajax({
                url :"/GET/Admin/AdminId/"+username,
                type:"get",
                dataType:"json",
                success:function (adminList){
                    admins=[];
                    console.log(adminList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(adminList.resultList,function (i,item) {
                        admins.push('<tr>' +
                            '<td>'+item.adminId+'</td>'+
                            '<td>' +item.adminName+
                            '</td><td>' +item.adminIdcard+
                            '</td><td>' +item.adminTel+
                            '</td><td><div class="content-table-operation">' +
                            '<input class="content-table-delete" name="'+item.adminId+'"  type="button" value="删除"/></div></td>' +
                            '</tr>')
                    })
                    $('#tb').empty().append(admins.join(''));
                    $('#username').val('');
                }
            });
        });
        //编辑信息
        $('edit-03-button').click(function (){
            let tel = $("#edit-tel").val();
            let address=$("#edit-address").val();
            $.ajax({
                url:"PUT/Admin"+id,
                dataType:"json",
                type:post,
                data:{tel:tel,address:address},
                success:function (res){
                    if (res === 1){
                        $('.box-01').hide();
                        admins = [];
                        getAdmins();
                    }
                }
            });
        });
        let id = 0;
        //添加管理员
        $('.box-03-button').click(function (){
            let name = $('#name').val();
            let idcard = $('#idcard').val();
            let sex = $('#sex').val();
            let tel = $('#tel').val();
            let address=$('#address').val();
            $.ajax({
                url: "/POST/Admin",
                type: "post",
                data:{name:name,idcard:idcard,sex:sex,tel:tel,address:address},
                dataType: "json",
                success:function (res){
                    console.log(res);
                    if (res === 1){
                        $('.box-01').hide();
                        admins=[];
                        getAdmins();
                        $('#name').val('');
                        $('#idcard').val('');
                        $('#sex').val('');
                        $('#tel').val('');
                        $('#address').val('');
                    }
                }
            })
        }
        );
        //删除管理员
        $('#tb').click(function (e){
            //管理员id
            console.log(e.target.name);
            $.ajax({
                url:"/DELETE/Admin/"+e.target.name,
                type:"post",
                dataType: "json",
                success:function (res){
                    if (res===1){
                        admins=[];
                        getAdmins();
                    }
                }


            });
        });
        $('#tb').click(function (e){
            //管理员id
            if(e.target.className==="content-table-delete") {
                id = e.target.name;
                $('.edit-01').show();
            }
        });
    })

</script>
</body>
</html>
