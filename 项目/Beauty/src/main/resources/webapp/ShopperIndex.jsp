<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商家首页</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="jquery-3.6.3.min.js"></script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }

        body {
            position: relative;
            width: 100%;
        }

        .header {
            height: 60px;
            width: 100%;
            display: flex;
            align-items: center;
            border-bottom: 1px solid #f1f1f1;
            background-color: #324057;
        }

        .logo {
            cursor: pointer;
            display: flex;
            align-items: center;
            width: 250px;
            padding-left: 40px;
        }

        .logo span {
            color: #5073fb;
        }

        .search--notification--profile {
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: calc(100% - 300px);
            padding: 0 40px;
        }

        .search {
            background-color: #f1f4f8;
            border-radius: 50px;
            width: 400px;
            padding: 5px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .search input {
            background-color: transparent;
            outline: none;
            border: none;
            text-indent: 15px;
            width: 85%;
        }

        .search button {
            outline: none;
            border: none;
            border-radius: 50%;
            background-color: #fff;
            padding: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .search button i {
            font-size: 1.1rem;
            color: #5073fb;
        }

        .notification--profile {
            display: flex;
            align-items: center;
        }

        .picon {
            cursor: pointer;
            margin-left: 20px;
            font-size: 1.1rem;
            padding: 5px;
            border-radius: 5px;
        }

        .lock {
            color: #5073fb;
            background-color: rgba(80, 115, 251, .2);
        }

        .bell {
            color: #f1d243;
            background-color: rgba(241, 210, 67, .2);
        }

        .chat {
            color: #70d7a5;
            background-color: rgba(112, 215, 165, .2);
        }

        .profile {
            position: relative;
            width: 40px;
            height: 40px;
            background-color: #5073fb;
            border: 1px solid #5073fb;
            border-radius: 50%;
            overflow: hidden;
        }

        .profile .icon {
            position: absolute;
            top: 0;
            left: 0;
            width: 110%;
            height: 110%;
            object-fit: cover;
        }

        .main {
            position: relative;
            width: 100%;
            min-height: calc(100vh - 60px);
            top: 0;
            margin: 0;
        }


        /* sidebar */

        .sidebar {
            top: 0;
            left: 0;
            position: absolute;
            height: 100%;
            width: 103px;
            background-color: #324057;
            padding: 30px;
            justify-content: space-between;

        }

        .sidebar.active {

            width: 250px;
            display: flex;
            flex-direction: column;
            border-right: 2px solid #f1f1f1;
            transition: .3s;

        }
        .child-menu{
            width: 100%;
            background-color: #324057;
            margin-top: 0;
            margin-bottom: 0;
            display: none;

        }
        .user-menu{
            width: 100%;
            background-color: #324057;
            margin-top: 0;
            margin-bottom: 0;
            display: none;

        }
        .sidebar--items{
            border: 1px solid #283346;
        }

        .sidebar--items .child-menu-item{
            display: column;
            font-size: 0.8rem;
            color: #fff;
            padding: 8px;
            padding-left: 45px;
            cursor: pointer;
            border: 1px solid #283346;
        }
        .sidebar--items .user-menu-item{
            display: column;
            font-size: 0.8rem;
            color: #fff;
            padding: 8px;
            padding-left: 50px;
            cursor: pointer;
            border: 1px solid #283346;
        }

        .sidebar.active .sidebar--items .child-menu.active{
            display: block;
        }
        .sidebar.active .sidebar--items .user-menu.active{
            display: block;
        }

        .sidebar.active .sidebar--items .child-menu-item:hover{
            background-color: #5073fb;
            color: #fff;
        }
        .sidebar.active .sidebar--items .user-menu-item:hover{
            background-color: #5073fb;
            color: #fff;
        }

        .sidebar .sidebar--item{
            display: none;
        }
        .sidebar.active .sidebar--item {
            display: block;
        }
        .child-menu .child-menu-item{
            display: none;
        }
        .user-menu .user-menu-item{
            display: none;
        }
        .child-menu.active .child-menu-item{
            display: block;
        }
        .user-menu.active .user-menu-item{
            display: block;
        }

        li {
            list-style: none;
        }

        a {
            text-decoration: none;
            display: block;
        }

        .sidebar--items a,
        .sidebar--bottom-items a {
            display: flex;
            align-items: center;
            font-size: 1.1rem;
            color: #fff;
            padding: 10px;
        }

        .sidebar--items li,
        .sidebar--bottom-items li {
            align-items: center;
            margin-bottom: 0px;
            font-size: 1.0rem;
            color: #fff;
            padding: 10px;

        }

        .sidebar.active .sidebar--items a:hover,
        .sidebar--bottom-items a:hover {
            background-color: #5073fb;
            color: #fff;
        }



        .sidebar--bottom-items li:last-child a {
            margin-bottom: 0;
        }

        .icon {
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-left: -20px;
            font-size: 22px;
            padding: 0 15px;
        }

        .icon-0 {
            color: #5073fb;
        }

        .icon-1 {
            color: #a280e9;
        }

        .icon-2 {
            color: #70d7a5;

        }

        .icon-3 {
            color: #85ade3;
        }
        .icon-4 {
            /*color: #e36ac8;*/
            color: rgb(241, 210, 67);
        }

        .icon-5 {
            color: #e36ac8;
        }

        .icon-6 {
            color: #5f5ce0;
        }

        .icon-7 {
            color: #e86786;
        }

        .icon-8 {
            color: #f1d243;
        }


        /* main--content */

        .main--content {
            position: absolute;
            top: 0;
            right: 0;
            height: 100%;
            width: calc(100% - 103px);
            padding: 0;
            overflow-y: scroll;
            overflow-x: scroll;
            transition: .3s;
        }

        .main--content.active {
            width: calc(100% - 250px);
        }

        .title {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 10px;
            margin-top: 30px;
        }

        .section--title {
            font-weight: 400;
        }

        .dropdown {
            outline: none;
            border: none;
            background-color: #f1f4f8;
            border-radius: 5px;
            width: 150px;
            padding: 5px;
        }

        .cards {
            display: flex;
            gap: 20px;
        }

        .card {
            cursor: pointer;
            padding: 20px;
            border-radius: 20px;
            min-width: 230px;
            height: auto;
            transition: .3s;
        }

        .card:hover {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }

        .card--data {
            display: flex;
            align-items: flex-start;
            justify-content: space-between;
        }

        .card h1 {
            font-size: 30px;
            margin-top: 10px;
        }

        .card--icon--lg {
            font-size: 71px;
            margin: 0 auto;
        }

        .c1 {
            color: #70d7a5;
            background-color: #f0fbf6;
        }

        .c2 {
            color: #aebffd;
            background-color: #edf1ff;
        }

        .card--stats {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-top: 5px;
        }

        .card--stats span {
            display: flex;
            align-items: center;
        }

        .card--icon {
            margin-right: 5px;
        }

        .stat--icon {
            color: #5f5ce0;
        }

        .up--arrow {
            color: #70d7a5;
        }

        .down--arrow {
            color: #e86786;
        }
        .card-1 {
            background-color: rgba(80, 115, 251, .1);
        }

        .card-1 .card--title {
            color: rgba(80, 115, 251, 1);
        }

        .card-1 .card--icon--lg {
            color: rgba(80, 115, 251, .4);
        }

        .card-2 {
            background-color: rgba(241, 210, 67, .1);
        }

        .card-2 .card--title {
            color: rgba(241, 210, 67, 1);
        }
        .card-2 .card--icon--lg {
            color: rgba(241, 210, 67, .4);
        }

        .card-3 {
            background-color: rgba(112, 215, 165, .1);
        }

        .card-3 .card--title {
            color: rgba(112, 215, 165, 1);
        }

        .card-3 .card--icon--lg {
            color: rgba(112, 215, 165, .4);
        }

        .card-4 {
            background-color: rgba(227, 106, 200, .1);
        }

        .card-4 .card--title {
            color: rgba(227, 106, 200, 1);
        }

        .card-4 .card--icon--lg {
            color: rgba(227, 106, 200, .4);
        }
        /* teachers */
        /*教师们*/
        .teachers--right--btns {
            display: flex;
            align-items: center;
            gap: 30px;
        }

        .add {
            display: flex;
            align-items: center;
            padding: 5px 10px;
            outline: none;
            border: none;
            background-color: #5073fb;
            color: #fff;
            border-radius: 5px;
            cursor: pointer;
            transition: .3s;
        }

        .add:hover {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }

        .add i {
            margin-right: 10px;
            padding: 5px;
            background-color: #fff;
            border-radius: 50%;
            color: #000;
        }

        .teachers--cards {
            display: flex;
            gap: 20px;
        }

        .teacher--card {
            padding: 20px;
            border-radius: 20px;
            height: auto;
            transition: .3s;
            border: 2px solid #f1f1f1;
            display: flex;
            flex-direction: column;
            align-items: center;
            font-size: .8rem;
        }

        .teacher--card:hover {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        }

        .img--box--cover {
            border: 1px solid #5073fb;
            padding: 2px;
            border-radius: 50%;
            display: inline-block;
            margin-bottom: 10px;
        }

        .img--box--cover1 {
            border: 1px solid #70d7a5;
        }

        .img--box {
            position: relative;
            width: 71px;
            height: 71px;
            overflow: hidden;
            border-radius: 50%;
        }
        .free {
            color: #70d7a5;
        }

        /*中学教师*/
        .scheduled {
            color: #5073fb;
        }


        /* recent--students */
        /*学生*/
        .recent--students {
            margin-bottom: 20px;
        }

        .table {
            height: 200px;
            overflow-y: scroll;
        }

        table {
            width: 100%;
            text-align: left;
            border-collapse: collapse;
            cursor: pointer;
        }

        tr {
            border-bottom: 1px solid #f1f1f1;
        }

        td,
        th {
            padding-block: 10px;
        }

        .edit {
            color: #70d7a5;
            margin-right: 10px;
        }

        .delete {
            color: #e86786;
        }
        .collapse__link{
            justify-self: flex-end;
            transition: .5s;
        }

        .collapse_menu{
            /* display: none; */
            display: none;
            width: 175px;
            padding: .75rem 2.25rem;
        }
        .collapse__sublink{
            color: var(--sub-color);
            font-size: var(--smal-font-size);
            display: block;
        }

        .collapse__sublink:hover{
            color: var(--white-color);
        }

        .showCollapse{
            display: block;
        }


        /* responsive starts here */

        @media screen and (max-width: 1350px) {
            .cards,
            .teachers--cards {
                overflow-x: scroll;
            }
        }

        @media screen and (max-width: 1024px) {
            table {
                min-width: 600px;
            }
        }

        @media screen and (max-width: 768px) {
            .logo {
                padding-left: 30px;
                width: fit-content;
            }

            .search--notification--profile {
                padding: 0 20px;
                margin-left: auto;
            }

            .main--content {
                padding: 0 0px;
            }

            .sidebar {
                padding: 20px;
            }

            .sidebar.active {
                width: 85px;
            }

            .main--content.active {
                width: calc(100% - 85px);
            }
        }
        @media screen and (max-width: 590px) {
            .search,
            .lock,
            .chat {
                display: none;
            }

            .notification--profile {
                margin-left: auto;
            }

            .search--notification--profile {
                width: fit-content;
            }

            .sidebar {
                transform: translateX(-100%);
                z-index: 10;
                background-color: #324057;
            }

            .sidebar.active {
                transform: translateX(0);
                width: 250px;
            }

            .sidebar.active .sidebar--item {
                display: block;
            }

            .main--content {
                width: calc(100% - 0px);
            }

            .main--content.active {
                width: calc(100% - 0px);
            }
        }

        @media screen and (max-width: 450px) {
            .teacher--filter {
                display: none;
            }

            .main--content {
                padding: 0 0px;
            }

            .logo {
                padding-left: 10px;
            }

            .search--notification--profile {
                padding: 0 10px;
            }

            .sidebar {
                padding: 10px;
            }
        }
    </style>
</head>
<body>
<section class="header">
    <div class="logo">
        <i class="el-icon-menu icon icon-0 menu"></i>
        <h2><span>卖家后台管理</span></h2>
    </div>
</section>
<section class="main">
    <div class="sidebar">
        <ul class="sidebar--items">
            <li>
                <a href="#" class="good">
                        <span class="icon icon-3">
                            <i class="el-icon-date"></i>
                        </span>
                    <span class="sidebar--item" style="white-space: nowrap;">商品管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                </a>
                <ul class="child-menu sidebar--items">
                    <li class="child-menu-item sidebar--item" id="GoodMan">已上架</li>
                    <li class="child-menu-item sidebar--item" id="checking">审核中</li>
                    <li class="child-menu-item sidebar--item" id="failedChecking">审核未通过</li>
                    <li class="child-menu-item sidebar--item" id="supplement">商品补货</li>
                </ul>
            </li>
            <li>
                <a href="#" class="order">
                        <span class="icon icon-3">
                            <i class="el-icon-date"></i>
                        </span>
                    <span class="sidebar--item" style="white-space: nowrap;">订单管理</span>
                    <ion-icon name="chevron-down-outline" class="collapse__link" ></ion-icon>
                </a>
                <ul class="order-menu sidebar--items">
                    <li class="order-menu-item sidebar--item" id="Order-pre">待发货</li>
                    <li class="order-menu-item sidebar--item" id="Order-beforeSH">待收货</li>
                    <li class="order-menu-item sidebar--item" id="Order-after">已收货</li>
                </ul>
            </li>
            <li>
                <a href="#" id="Comment">
                        <span class="icon icon-4">
                            <i class="el-icon-user"></i>
                        </span>
                    <span class="sidebar--item">评价中心</span>
                </a>
            </li>
            <li>
                <a href="#"  id="SelfCenter">
                        <span class="icon icon-5">
                            <i class="el-icon-s-data"></i>
                        </span>
                    <span class="sidebar--item">个人中心</span>
                </a>
            </li>
        </ul>
        <ul class="sidebar--bottom-items">
            <li>
                <a href="#">
                    <ion-icon name="log-out-outline" class="nav_icon" ></ion-icon>
                    <span class="sidebar--item">退出登录</span>
                </a>
            </li>
        </ul>
    </div>
    <div class="main--content">
        <iframe style="position: absolute;width: 100%;height: 100%;" src="ShopperBlank.jsp"></iframe>
    </div>
</section>
<script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js">
</script>
<script>
    let menu = document.querySelector('.menu');
    let sidebar = document.querySelector('.sidebar');
    let mainContent = document.querySelector('.main--content');
    let childMenu = document.querySelector('.child-menu');
    menu.onclick = function () {
        sidebar.classList.toggle('active');
        mainContent.classList.toggle('active')
    };
    let good = document.querySelector('.good');
    good.onclick = function (){
        childMenu.classList.toggle('active');
    };
    let order = document.querySelector('.order');
    let orderMenu = document.querySelector('.order-menu');
    order.onclick = function (){
        orderMenu.classList.toggle('active');
    };
    $(function (){
        $('#checking').click(function () {
            $('#checking').css("background-color","#5073fb");
            $('#supplement').css("background-color","#324057");
            $('#GoodMan').css("background-color","#324057")
            $('#failedChecking').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('#Order-after').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','Shopper/Checking.jsp');
        });
        $('#supplement').click(function (){
            $('#supplement').css("background-color","#5073fb");
            $('#failedChecking').css("background-color","#324057");
            $('#checking').css("background-color","#324057");
            $('#GoodMan').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('#Order-after').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','Shopper/Supplement.jsp');
        });
        $('#failedChecking').click(function () {
            $('#failedChecking').css("background-color","#5073fb");
            $('#checking').css("background-color","#324057");
            $('#GoodMan').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('#Order-after').css("background-color","#324057");
            $('#supplement').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','Shopper/FailedChecking.jsp');
        });
        $('#GoodMan').click(function (){
            $('#GoodMan').css("background-color","#5073fb");
            $('#failedChecking').css("background-color","#324057");
            $('#checking').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('#supplement').css("background-color","#324057");
            $('#Order-after').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','ShopperGoodMan.jsp');
        });
        $('#Comment').click(function (){
            $('#Comment').css("background-color","#5073fb");
            $('#GoodMan').css("background-color","#324057");
            $('#failedChecking').css("background-color","#324057");
            $('#checking').css("background-color","#324057");
            $('#Order-after').css("background-color","#324057");
            $('#supplement').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','Shopper/Comment.jsp');
        });
        $('#Message').click(function (){
            $('iframe').attr('src','Shopper/Message.jsp');
        });
        $('#Order-pre').click(function (){
            $('#GoodMan').css("background-color","#324057");
            $('#failedChecking').css("background-color","#324057");
            $('#checking').css("background-color","#324057");
            $('#Order-pre').css("background-color","#5073fb");
            $('#Order-after').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('#supplement').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','Shopper/Order-pre.jsp');
        });
        $('#Order-after').click(function (){
            $('#Order-after').css("background-color","#5073fb");
            $('#GoodMan').css("background-color","#324057");
            $('#failedChecking').css("background-color","#324057");
            $('#checking').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('#supplement').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','Shopper/Order-after.jsp');
        });
        $('#SelfCenter').click(function (){
            $('#SelfCenter').css("background-color","#5073fb");
            $('#GoodMan').css("background-color","#324057");
            $('#failedChecking').css("background-color","#324057");
            $('#checking').css("background-color","#324057");
            $('#Order-after').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#supplement').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#Order-beforeSH').css("background-color","#324057");
            $('iframe').attr('src','Shopper/SelfCenter.jsp');
        });
        $('#Order-beforeSH').click(function (){
            $('#Order-beforeSH').css("background-color","#5073fb");
            $('#GoodMan').css("background-color","#324057");
            $('#failedChecking').css("background-color","#324057");
            $('#checking').css("background-color","#324057");
            $('#Order-after').css("background-color","#324057");
            $('#Order-pre').css("background-color","#324057");
            $('#supplement').css("background-color","#324057");
            $('#Comment').css("background-color","#324057");
            $('#SelfCenter').css("background-color","#324057");
            $('iframe').attr('src','Shopper/Order-beforeSH.jsp');
        });
    })
</script>
</body>
</html>
