<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-02-21
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link rel="stylesheet" href="../reset.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <link rel="stylesheet" href="/Shopper/SelfCenter.css"/>
</head>
<style>
    .center{
        width: 480px;
    }
</style>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">卖家中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">个人中心</div>--%>
<%--</div>--%>
<div class="eject">
    <div class="eject-first">
        <div style="display: flex;">
            <span style="font-weight: bold;">修改密码</span>
            <div style="margin-left: 250px;font-size: 20px;color: gainsboro;" id="password-remove">×</div>
        </div>
        <br/>
        <div class="eject-second">
            <span style="font-size: large;margin-top: 10px;">手机号：</span><input type="text" placeholder="请输入177****"/>
        </div>
        <div style="display: flex;" class="eject-third">
            <input type="text"  placeholder="请输入"/><div class="eject-button" style="margin-left: 5px;border-radius: 2px;width: 70px;text-align: center">发送验证码</div>
        </div>
        <div class="eject-fourth" id="eject-submit" style="width: 275px">提交</div>
    </div>
</div>
<div class="eject01">
    <div class="eject-first">
        <div style="display: flex;">
            <span style="font-weight: bold;">修改密码</span>
            <div style="margin-left: 250px;font-size: 20px;color: gainsboro;" id="password-remove-01">×</div>
        </div>
        <br/>
        <div class="eject-second01">
            <div>
                <div style="display: flex;margin-top: 10px;">
                    <span style="width: 100px;margin-top: 10px;">新密码：</span>
                    <input placeholder="请输入新密码" name="newPassword"/>
                </div>
            </div>
            <div>
                <div style="display: flex;margin-top: 25px;">
                    <span style="width: 100px;margin-top: 10px;">再次输入：</span>
                    <input placeholder="确认密码" name="newPasswordAgain"/>
                </div>
            </div>
            <div style="display: flex;margin-top: -30px">
                <div class="eject-fourth01" id="eject-submit-01">提交</div>
            </div>
        </div>
    </div>
</div>
<!-- 修改电话 -->
<div class="eject03">
    <div class="eject-first">
        <div style="display: flex;">
            <span style="font-weight: bold;">修改电话</span>
            <div style="margin-left: 250px;font-size: 20px;color: gainsboro;" id="password-remove-03">×</div>
        </div>
        <br/>
        <div class="eject-second">
            <span style="font-size: large;margin-top: 10px;">手机号：</span><input type="text" value="${sessionScope.shopperTel}"/>
        </div>
        <div style="display: flex;" class="eject-third">
            <input type="text" placeholder="请输入验证码"/><div class="eject-button" style="margin-left: 5px;border-radius: 2px;width: 70px;text-align: center">发送验证码</div>
        </div>
        <div class="eject-fourth" id="eject-submit-03" style="width: 275px">提交</div>
    </div>
</div>

<div class="eject04">
    <div class="eject-first">
        <div style="display: flex;">
            <span style="font-weight: bold;">修改密码</span>
            <div style="margin-left: 250px;font-size: 20px;color: gainsboro;" id="password-remove-04">×</div>
        </div>
        <br/>
        <div class="eject-second">
            <span style="font-size: large;margin-top: 10px;">手机号：</span><input type="text" value="${sessionScope.shopperTel}"/>
        </div>
        <div class="eject-fourth" id="eject-submit-04" style="width: 275px">提交</div>
    </div>
</div>

<div class="center">
    <div style="mafont-size: larger; margin-left: 3px; font-weight: 550;border-bottom: 1px solid darkgrey;height: 60px;display:flex;align-items: center;">账号信息</div>
    <div class="center-avatar">
        <img class="center-avatar" src="../asset/管理员%20(1).png"/>
    </div>
    <div class="outer-name">
        <div class="name" style="font-size: small">${sessionScope.shopperName}</div>
    </div>
    <div class="center-account"style="border-bottom: 1px solid darkgray;font-size: small">账号:${sessionScope.shopperId}</div>
    <div style="display: flex;border-bottom: 1px solid darkgray;">
        <div class="center-phoneNumber"style="border-bottom: 1px solid darkgray;font-size: small;width: 300px;">电话: ${sessionScope.shopperTel}</div>
        <div style="display: flex;margin-left: 10px"><img class="icon" src="../asset/修改.png"/></div>
        <div class="center-a" style="width: 120px;">修改电话</div>
    </div>
</div>
<div class="footer">
    <div style="display: flex;margin: 0 auto;">
        <div><img class="footer-icon" src="../asset/修改.png"/></div>
        <div class="footer-a">修改密码</div>
    </div>
</div>
<script>
    $(function(){
        $('.footer-a').click(function(){
            $('.eject').show();
        });
        $('#password-remove').click(function(){
            $('.eject').hide();
        });
        $('#eject-submit').click(function(){
            $('.eject').hide();
            $('.eject01').show();
        });
        $('#password-remove-01').click(function(){
            $('.eject01').hide();
        });
        $('#password-remove-03').click(function(){
            $('.eject03').hide();
        });
        $('#password-remove-04').click(function(){
            $('.eject04').hide();
        });
        $('.center-a').click(function(){
            $('.eject03').show();
        });
        $('#eject-submit-03').click(function(){
            $('.eject03').hide();
            $('.eject04').show();
        });

    })
</script>
</body>
</html>
