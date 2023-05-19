<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>用户管理</title>
  <link rel="stylesheet" href="../reset.css"/>
  <script src="../jquery-3.6.3.min.js"></script>
  <link rel="stylesheet" href="/Shopper/Order-pre.css"/>
</head>
<style>
  .img1{
    width: 60px;
    height: 60px;
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
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;

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
  td{
    font-size: small;
    padding: 6px;
    text-align: center;
    height: 25px;
    line-height: 25px;
  }
  .name{
    width: 100px;
    height: 25px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
</style>
<body>
<div class="center">
  <div class="center-second">
    <input type="text" class="input" name="username" placeholder="请输入"/>
    <div class="center-second-second">搜索</div>
  </div>
</div>
<div class="content">
  <table>
    <thead>
    <th class="content-table-class01">商品ID</th>
    <th class="content-table-class01">商品名称</th>
    <th class="content-table-class01">商品分类</th>
    <th class="content-table-class01">商品图片</th>
    <th class="content-table-class01">商品价格</th>
    <th class="content-table-class01">库存</th>
    <th class="content-table-class02">操作</th>
    </thead>
    <tbody id="tb"></tbody>
  </table>
</div>
<script>
  $(function(){
    $('#remove-addUser').click(function(){
      $('.box-01').hide();
    });
    let goods=[];
    getAllGood();
    function getAllGood(){
      $.ajax({
        url:"/GET/goodsRefuse/1",
        type:"get",
        dataType:"json",
        success:function (goodPage){
          console.log(goodPage);
          // 遍历admins数组，将元素的数组元素填充模板的占位符
          $.each(goodPage.resultList,function (i,item) {
            goods.push('<tr>' +
                    '<td>'+item.goodId+'</td>'+
                    '<td><div class="name">' +item.goodName+
                    '</div></td><td>' +item.categoryName+'/'+item.smallCategoryName+
                    '</td><td><img class="img1" src='+item.goodImg1+
                    '></td><td>' +item.goodPrice+
                    '</td><td>' +item.goodStorage+
                    '</td><td><div class="content-table-operation">' +
                    '<input class="content-table-delete" name="'+item.goodId+'"  type="button" value="下架"/></div></td>' +
                    '<input class="content-table-delete" name="'+item.goodId+'"  type="button" value="查看详情"/></div></td>'+
                    '</tr>')
          })
          $('#tb').empty().append(goods.join(''))

        }
      })
    }
    $('#tb').click(function (e) {
      if (e.target.className==='content-table-delete'){
        $.ajax({
          url:"/DELETE/Good",
          type:"post",
          data:{goodId:e.target.name},
          dataType: "json",
          success:function (res){
            if (res===1){
              goods=[];
              getAllGood();
            }
          }


        });
      }
    });
  })
</script>
</body>
</html>