<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-04
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录页面</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="AdminLogin.css">
</head>
<body>
<div class="box">
    <div class="content">
        <img class="login-img images" src="./asset/管理员.png" alt="登录">
        <div class="login-wrapper">
            <h1 class="h1-text">登录</h1>
            <form action="/LoginCheck">
                <div class="login-form">
                    <div class="user-fvorm form-item">
                        <div class="text-tips">
                            <span>账号</span>
                        </div>
                        <input type="text" name="account" id="account" autocomplete="off" placeholder="请输入账号或手机号" />
                    </div>
                    <div class="password-form form-item">
                        <div class="text-tips">
                            <span>密码</span>
                        </div>
                        <input type="password" name="password" id="password" placeholder="请输入密码" />
                    </div>
                    <button class="btn" type="submit" :disabled="!canSubmit">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
