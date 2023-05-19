<%--
  Created by IntelliJ IDEA.
  User: 18167
  Date: 2023-03-30
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>商品添加页面</title>
  <script src="jquery-3.6.3.min.js"></script>
</head>
<style>
  input[type=text],input[type=file], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
  }
  .center-second-second{
    background-color: blue;
    width: 40px;
    height: 30px;
    margin-left: 5px;
    font-weight: bold;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    font-size: small;
  }
  input[type=submit] {
    width: 100%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }

  input[type=submit]:hover {
    background-color: #45a049;
  }

  form {
    width: 800px;
    margin: 0 auto;
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
  }
</style>
<body>
<div id="back" class="center-second-second">返回</div>
<form action="/PUT/good" enctype="multipart/form-data" method="post">
  <%--@declare id="fname"--%><%--@declare id="lname"--%><%--@declare id="goodsmallcategory"--%><label for="fname">商品名</label>
  <input type="text" id="goodName" name="goodName" placeholder="输入..." required>
  <label for="goodCategory">商品类型1</label>
  <select id="goodCategory" name="goodCategory">
    <option value="护肤">护肤</option>
    <option value="彩妆">彩妆</option>
    <option value="个护">个护</option>
  </select>
  <label for="goodSmallCategory">商品类型2</label>
  <select id="goodSmallCategory" name="goodSmallCategory">
  </select>
  <label for="fname">商品图片(主图)</label>
  <input type="file" id="goodImg1" name="goodImg1" value="上传图片" required>
  <label for="fname">商品图片（次图1）</label>
  <input type="file" id="goodImg2" name="goodImg2" value="上传图片" required>
  <label for="fname">商品图片（次图2）</label>
  <input type="file" id="goodImg3" name="goodImg3" value="上传图片" required>
  <label for="fname">最低价格</label>
  <input type="text" id="goodPrice" name="goodPrice" placeholder="输入..." required>

  <label for="lname">库存</label>
  <input type="text" id="goodStorage" name="goodStorage" placeholder="输入..." required>
  <label for="lname">备案号</label>
  <input type="text" id="goodCertificateNumber" name="goodCertificateNumber" placeholder="输入..." required>
  <label for="lname">成分表</label>
  <input type="file" id="goodMaterial" name="goodMaterial" value="上传图片" required>
  <label for="lname">详情页</label>
  <input type="file" id="goodDetail" name="goodMaterial" value="上传图片"  required>

    <div>子商品1</div>
    <label for="lname">名称</label>
    <input type="text" id="subGoodName1" name="subGoodName1" placeholder="输入..." required>
    <label for="lname">图片</label>
    <input type="file" id="subGoodImg1" name="subGoodImg1" placeholder="输入..." required>
    <label for="lname">价格</label>
    <input type="text" id="subGoodPrice1" name="subGoodPrice1" placeholder="输入..." required>
    <label for="lname">库存</label>
    <input type="text" id="subGoodStorage1" name="subGoodStorage1" placeholder="输入..." required>
    <!--  -->
    <div>子商品2</div>
    <label for="lname">名称</label>
    <input type="text" id="subGoodName2" name="subGoodName2" placeholder="输入..." required>
    <label for="lname">图片</label>
    <input type="file" id="subGoodImg2" name="subGoodImg2" placeholder="输入..." required>
    <label for="lname">价格</label>
    <input type="text" id="subGoodPrice2" name="subGoodPrice2" placeholder="输入..." required>
    <label for="lname">库存</label>
    <input type="text" id="subGoodStorage2" name="subGoodStorage2" placeholder="输入..." required>
    <!--  -->
    <div>子商品3</div>
    <label for="lname">名称</label>
    <input type="text" id="subGoodName3" name="subGoodName3" placeholder="输入..." required>
    <label for="lname">图片</label>
    <input type="file" id="subGoodImg3" name="subGoodImg3" placeholder="输入..." required>
    <label for="lname">价格</label>
    <input type="text" id="subGoodPrice3" name="subGoodPrice3" placeholder="输入..." required>
    <label for="lname">库存</label>
    <input type="text" id="subGoodStorage3" name="subGoodStorage3" placeholder="输入..." required>
    <!-- -->
    <div>子商品4</div>
    <label for="lname">名称</label>
    <input type="text" id="subGoodName4" name="subGoodName4" placeholder="输入..." required>
    <label for="lname">图片</label>
    <input type="file" id="subGoodImg4" name="subGoodImg4" placeholder="输入..." required>
    <label for="lname">价格</label>
    <input type="text" id="subGoodPrice4" name="subGoodPrice4" placeholder="输入..." required>
    <label for="lname">库存</label>
    <input type="text" id="subGoodStorage4" name="subGoodStorage4" placeholder="输入..." required>
    <!--  -->
    <div>子商品5</div>
    <label for="lname">名称</label>
    <input type="text" id="subGoodName5" name="subGoodName5" placeholder="输入..." required>
    <label for="lname">图片</label>
    <input type="file" id="subGoodImg5" name="subGoodImg5" placeholder="输入..." required>
    <label for="lname">价格</label>
    <input type="text" id="subGoodPrice5" name="subGoodPrice5" placeholder="输入..." required>
    <label for="lname">库存</label>
    <input type="text" id="subGoodStorage5" name="subGoodStorage5" placeholder="输入..." required>

  <input type="submit" id="submit" value="Submit">
</form>
</body>
<script>
  $(function () {
    $('#back').click(function () {
      location.href="./ShopperGoodMan.jsp"
    });
    // $('#submit').click(function (){
    //     let goodName = $('#goodName').val();
    //     let goodPrice = $('#goodPrice').val();
    //     let goodStorage = $("#goodStorage").val();
    //     let goodMaterial =   $('#goodMaterial').val();
    //     let goodCertificateNumber = $("#goodCertificateNumber").val();
    //     let  goodImg1 = $("#goodImg1").val();
    //     let  goodImg2 = $("#goodImg2").val();
    //     let  goodImg3 = $("#goodImg3").val();
    //     let goodDetail = $("#goodDetail").val();
    //     let goodCategory = $("#goodCategory").val();
    //     let goodSmallCategory = $('#goodSmallCategory').val();
    //     $.ajax({
    //         url: "PUT/good",
    //         type: "post",
    //         dataType: "json",
    //         data:{goodName:goodName,goodPrice:goodPrice,goodStorage:goodStorage,goodMaterial:goodMaterial,
    //         goodCertificateNumber:goodCertificateNumber,goodImg1:goodImg1,goodImg2:goodImg2,goodImg3:goodImg3,
    //         goodDetail:goodDetail,goodCategory:goodCategory,goodSmallCategory:goodSmallCategory},
    //         success:function (e) {
    //             if (e === 1) {
    //                 alert("新增成功！");
    //                 $('#goodName').val('');
    //                 $('#goodCategory').val('');
    //                 $('#goodSmallCategory').val('');
    //                 $('#goodImg1').val('');
    //                 $('#goodImg2').val('');
    //                 $('#goodImg3').val('');
    //                 $('#goodPrice').val('');
    //                 $('#goodStorage').val('');
    //                 $('#goodCertificateNumber').val('');
    //                 $('#goodMaterial').val('');
    //                 $('#goodDetail').val('');
    //             }
    //         }
    //     });
    //
    // });
    let options=[];
    $('#goodCategory').change(function () {
      let categoryName =$('#goodCategory').val();
      $.ajax({
        url:"GET/smallCategory/"+categoryName,
        type:"get",
        dataType:"json",
        success:function (res) {
          options=[];
          console.log(res)
          $.each(res,function (i,item) {
            options.push(
                    '<option value='+item.smallCategoryName+'>'+item.smallCategoryName+'</option>'
            )

          })
          console.log(options);
          $('#goodSmallCategory').empty().append(options.join(''))
        }
      })
    })})
</script>
</html>