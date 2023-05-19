<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>商品审核</title>
    <link rel="stylesheet" href="../reset.css"/>
    <link rel="stylesheet" href="Good-Check.css"/>
    <script src="../jquery-3.6.3.min.js"></script>
    <script src="http://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
    <style>
        .img1{
            width: 40px;
            height: 40px;
        }
        .pop{
            width: 375px;
            /* height: 667px; */
            /*border: 1px solid red;*/
        }
        .tips1{
            display: flex;
            margin-top: 10px;
        }
        .tips2{
            display: flex;
            margin: 0 auto;
        }
        .tips3-text-left{
            width: 100px;
            margin-left: 15px;
        }
        .tips3-text-right{
            width: 20px;
        }
        .icon{
            width: 20px;
            height: 20px;
        }
        .main-text{
            font-size: small;
            font-weight: 550;
            margin-top: 20px;
        }
        .title{
            font-weight: 550;
        }
        .cf-td{
            border:1px solid gray;
        }
        .outer-pop{
            border: 1px solid silver;
            margin: 0 auto;
            width: 376px;
        }
    </style>
</head>
<body>
<%--<div class="header">--%>
<%--    <div class="header-firstText">管理员中心</div>--%>
<%--    <div class="header-secondText">>></div>--%>
<%--    <div class="header-thirdText">商品上架审核</div>--%>
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
        <th class="content-table-class01">商品备案号</th>
        <th class="content-table-class01">商品分类</th>
        <th class="content-table-class01">商品价格</th>
        <th class="content-table-class01">商品库存</th>
        <th class="content-table-class01">商品介绍</th>
        <th class="content-table-class01">商品材料</th>
        <th class="content-table-class02">操作</th>
        </thead>
        <tbody id="tb"></tbody>
    </table>
    <div class="outer-pop" id="outer-pop">
        <div style="font-size: xx-large" id="outer-pop-delete">×</div>
    <div class="pop" id="pop" >
        <div>
            <div class="title">安全</div>
        </div>
        <div>
            <div class="main-text">成分安全提示</div>
        </div>
        <div class="tips1">
            <div class="tips2">
                <img class="icon" src="../image/香水.png"/>
                <div class="tips3-text-left">香精</div>
                <div class="tips3-text-right" id="xj">
                    1
                </div>
            </div>
            <div class="tips2">
                <img class="icon" src="../image/防腐剂.png"/>
                <div class="tips3-text-left">防腐剂</div>
                <div class="tips3-text-right" id="ffj">
                    1
                </div>
            </div>
        </div>
        <div class="tips1">
            <div class="tips2">
                <img class="icon" src="../image/警告.png"/>
                <div class="tips3-text-left">风险成分</div>
                <div class="tips3-text-right" id="fxcf">
                    1
                </div>
            </div>
            <div class="tips2">
                <img class="icon" src="../image/孕妇.png"/>
                <div class="tips3-text-left">孕妇慎用</div>
                <div class="tips3-text-right" id="yfsy">
                    1
                </div>
            </div>
        </div>
        <div class="tips1">
            <div class="tips2">
                <img class="icon" src="../image/33类酒精饮料x16.png"/>
                <div class="tips3-text-left">酒精</div>
                <div class="tips3-text-right" id="jj">
                    1
                </div>
            </div>
            <div class="tips2">
                <img class="icon" src="../image/平衡油脂痘痘.png"/>
                <div class="tips3-text-left">致痘成分</div>
                <div class="tips3-text-right" id="zdcf">
                    1
                </div>
            </div>
        </div>
        <div>
            <div class="main-text">全成分表</div>
        </div>
        <table>
            <thead>
            <td class="cf-td">成分名称</td>
            <td class="cf-td">成分安全分</td>
            <td class="cf-td">活性成分</td>
            <td class="cf-td">致痘风险</td>
            <td class="cf-td">使用目的</td>
            </thead>
            <tbody id="tb1"></tbody>
        </table>
</div>
</div>
</div>
<script>
    $('#outer-pop').hide();
    $(function(){
        $('#outer-pop-delete').click(function (){
            $('#outer-pop').hide();
        });
        let compositions=[];
        let goods=[];
        function getGoods(){
            $.ajax({
                url:"/GET/GoodCheck/"+1,
                type:"get",
                dataType:"json",
                success:function (goodPage){
                    console.log(goodPage);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(goodPage.resultList,function (i,item) {
                        goods.push('<tr>' +
                            '<td>'+item.goodId+'</td>'+
                            '<td><img class="img1" src='+item.goodImg1+'>' +
                            '</td><td>' +item.shopperId+
                            '</td><td>' +item.goodName+
                            '</td><td>' +item.goodCertificationNumber+
                            '</td><td>' +item.categoryName+'/'+item.smallCategoryName +
                            '</td><td>' +item.goodPrice+
                            '</td><td>' +item.goodStorage+
                            '</td><td>' +item.goodIntro+
                            '</td><td><img class="img1" src=' +item.material+'>'+
                            '</td><td><div class="content-table-operation" >' +
                            '<input class="content-table-edit01" name="'+item.goodId+'"  type="button" value="分析"/></div>' +
                            '<input class="content-table-edit02" name="'+item.goodId+'"  type="button" value="允许"/></div>' +
                            '<input class="content-table-edit03" name="'+item.goodId+'"  type="button" value="拒绝"/></div></td>'+
                            '</tr>')
                    })
                    $('#tb').empty().append(goods.join(''));
                    $('.img1').css("width","80px");
                    $('.img1').css("height","80px");
                }
            })

        }
        getGoods();
        // $('#pop').hide();
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
        let goodId="";
        //搜索商品
        $('.center-second-second').click(function (){
            let username = $('#username').val();
            $.ajax({
                url :"/GET/GoodCheckByName/"+username,
                type:"get",
                dataType:"json",
                success:function (goodList){
                    goods=[];
                    console.log(goodList);
                    // 遍历admins数组，将元素的数组元素填充模板的占位符
                    $.each(goodList,function (i,item) {
                        goods.push('<tr>' +
                            '<td>'+item.goodId+'</td>'+
                            '<td><img class="img1" src='+item.goodImg1+'>' +
                            '</td><td>' +item.shopperId+
                            '</td><td>' +item.goodName+
                            '</td><td>' +item.goodCertificationNumber+
                            '</td><td>' +item.categoryName+'/'+item.smallCategoryName +
                            '</td><td>' +item.goodPrice+
                            '</td><td>' +item.goodStorage+
                            '</td><td>' +item.goodIntro+
                            '</td><td><img class="img1" src=' +item.material+'>'+
                            '</td><td><div class="content-table-operation" >' +
                            '<input class="content-table-edit01" name="'+item.goodId+'"  type="button" value="分析"/></div>' +
                            '<input class="content-table-edit02" name="'+item.goodId+'"  type="button" value="允许"/></div>' +
                            '<input class="content-table-edit03" name="'+item.goodId+'"  type="button" value="拒绝"/></div></td>'+
                            '</tr>')
                    })
                    $('#tb').empty().append(goods.join(''));
                    $('.img1').css("width","80px");
                    $('.img1').css("height","80px");
                    $('#username').val('');
                }
            });
        });
        //删除管理员
        $('#tb').click(function (e){
            //管理员id
            console.log(e);
            if (e.target.className==='content-table-edit03'){
                $.ajax({
                    url:"/DELETE/GoodCheck",
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
            else if (e.target.className==='content-table-edit02'){
                console.log(e.target.name);
                $.ajax({
                    url:"/PUT/GoodCheck",
                    type:"post",
                    data:{goodId:e.target.name},
                    dataType: "json",
                    success:function (res){
                        goods=[];
                        getGoods();
                    }


                });
            }
            else if(e.target.className==='content-table-edit01'){
                compositions=[];
                $.ajax({
                    url:"/POST/getCompositionTable",
                    type:"post",
                    data:{goodId:e.target.name},
                    dataType: "json",
                    success:function (res){
                        console.log(res);
                        $('#outer-pop').show();
                       $('#xj')[0].innerText=res.xj.length;
                       $('#jj')[0].innerText=res.jj.length;
                       $('#ffj')[0].innerText=res.ffj.length;
                       $('#fxcf')[0].innerText=res.fxfg.length;
                       $('#yfsy')[0].innerText=res.yssy.length;
                       $('#zdcf')[0].innerText=res.zdfx.length;
                        $.each(res.compositionList,function (i,item) {
                            compositions.push('<tr>' +
                                '<td>'+item.compositionName+'</td>'+
                                '</td><td>' +item.compositionSecurityLow+'-'+item.compositionSecurityHigh+
                                '</td><td>' +item.compositionActivity+
                                '</td><td>' +item.compositionCauseAcne+
                                '</td><td>' +item.compositionAim+
                                '</td>'+
                                '</tr>')
                        })
                        $('#tb1').empty().append(compositions.join(''));
                        if (compositions!=[]){
                            var imgUrl;
                            html2canvas($('#pop')[0]).then(canvas =>{
                                imgUrl = canvas.toDataURL("image/png");
                                console.log(imgUrl);
                                $.ajax({
                                    url:"/PATCH/GoodDetail",
                                    type:"post",
                                    data:{goodId:e.target.name,goodDetail:imgUrl},
                                    dataType: "json",
                                    success:function (res){
                                        if (res==1){
                                            console.log("添加成功！");
                                        }
                                    }

                                });
                            });
                        }
                    }


                });

            }
        });

    })

</script>
</body>
</html>
