// pages/order-confirm/index.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 确认订单状态 1 表示购物车来 0 表示是从商品来
    type:'',
    hasAddress:false,
    addressInfo:null,
    cartInfo:null,
    shopperInfo:null,
    goodInfo:null,
    zongJia:null,
    jian:null,
    password:[],
    hidden:true,
    option2byNum:null,
    zongE:null,
    Num:null,
    hasConfirm:false
  },
  submitConfirm(){
    console.log(this.data.hasAddress);
    if (this.data.hasAddress) {
      console.log(2);
      this.setData({
        hidden:false
      })
    }
  },
  getCartInfo(){
    wx.request({
      url: 'http://localhost:8081/GET/Cart/'+app.globalData.userInfo.userId,
      method:'GET',
      header:{
      'content-type':'application/json'},
     success:(res)=>{
       console.log(res.data);
       this.setData({
         cartInfo:res.data
       })
       var cartInfo = this.data.cartInfo;
       var length = cartInfo.length;
       console.log("Length="+length);
       var num = 0 ;
       var jian=0;
       for(var i = 0; i<length;i++){
         var innerLength = cartInfo[i].goodInfos.length;
        for(var  j= 0 ; j < innerLength ; j++){
          if (cartInfo[i].goodInfos[j].selected==0) {
            cartInfo[i].goodInfos[j]=null;
            console.log(cartInfo);
          }
          else{
            num++;
            jian++;
          }
        }
        if (num==0) {
          console.log(i);
          console.log('删除购物车数据！');
          cartInfo[i]=null;
        }
      }
       this.setData({
         cartInfo:cartInfo,
         jian:jian,
         Num:jian
       })
       this.countPrice();
     }})
  },
  countPrice(){
    var cartInfo = this.data.cartInfo;
     var length = cartInfo.length;
     var price = 0 ;
     for(var i = 0 ; i  < length ; i ++){
       var innerLength = cartInfo[i].goodInfos.length;
       for(var j = 0 ; j < innerLength; j++){
         if (cartInfo[i].goodInfos[j]!=null&&cartInfo[i].goodInfos[j].selected==1) {
           price+=cartInfo[i].goodInfos[j].goodPrice*cartInfo[i].goodInfos[j].goodNum;
           console.log(price);
         }
         else{
           continue;
         }
       }
     }
     this.setData({
       zongE:price
     })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      Num:null
    })
    if (options.type==1) {
      this.getCartInfo();
      this.setData({
        type:1
      })
    }
    else if(options.type==3){
      this.getOrderInfo(options.orderHao);
      this.setData({
        type:3
      })
    }
    else if (options.type==4) {
      this.getOrderInfo(options.orderHao);
      this.setData({
        type:4
      })
    }
    else{
      var goodId = options.goodId;
      var goodNum = options.goodNum;
      this.setData({
        option2byNum:goodNum,
        goodId:goodId,
        Num:goodNum,
        type:2
      });
      this.getGoodInfo();
    }
  },
   getOrderInfo(orderHao){
    wx.request({
      url: 'http://localhost:8081/GET/OrderInfoByOrderHao/'+orderHao+'/'+app.globalData.userInfo.userId+'',
      method:'POST',
      header:{
        'content-type':'application/json'
      },
      success:(res)=>{
        var goodInfos = res.data.goodInfo;
        var orderHao=res.data.orderHao;
        var orderId=res.data.orderId;
        var orderPrice=res.data.orderPrice;
        console.log(res.data);
        this.setData({
          cartInfo:[{
            orderHao:orderHao,
            orderId:orderId,
            orderPrice:orderPrice,
            goodInfos:goodInfos
          }],
          goodInfo:null,
          zongE:res.data.orderPrice
        })
        console.log(this.data.hasConfirm);
        if (this.data.hasConfirm) {
          console.log(orderHao);
          this.deleteFormerOrder(orderHao);
         this.generateOrder(0);
        }
      }
    })
  },
  deleteFormerOrder(orderHao){
    console.log("删掉？")
    wx.request({
      url: 'http://localhost:8081/DELETE/Order',
      method:'POST',
      data:{
        userId:app.globalData.userInfo.userId,
        orderHao:orderHao
      },
      header:{'content-type':'application/x-www-form-urlencoded'},
      success:(res)=>{
        console.log(res.data);
        console.log('删除成功！')
      }
    })
  },
  getGoodInfo()
  {
    wx.request({
      url: 'http://localhost:8081/GET/GoodRoostById/'+this.data.goodId,
      method:'GET',
      header:{
        'content-type':'application/json'
      },
     success:(res)=>{
       console.log(res.data);
       this.setData({
         goodInfo:res.data,
         zongE:res.data[0].goodPrice*this.data.option2byNum
       })
       wx.request({
         url: 'http://localhost:8081/GET/User/ShopperId/'+this.data.goodInfo[0].shopperId+'',
         method:'GET',
         header:{
          'content-type':'application/json'
        },
        success:(res)=>{
          console.log(res.data);
          this.setData({
            shopperInfo:res.data,
          })
        }
       })
     }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },
  addAddress(){
    wx.navigateTo({
      url: '/pages/usercenter/address/list/index',
    })
    if(this.data.addressInfo!=null){
      this.setData({
        hasAddress:true
      });
    }
    console.log(this.data.addressInfo);
  },
  judge(){
    if (this.data.addressInfo!=null) {
      console.log(1)
      this.setData({
        hasAddress:true
      })
    }else{
      console.log(2)
      this.setData({
        hasAddress:false
      })
    }
  },
  /**
   * 生命周期函数--监听页面显示
   */
  async onShow() {
  await console.log(this.data.addressInfo);
  //  let pages = getCurrentPages();
  //  let currenPage=pages[pages.length-1];
  //  console.log(currenPage);
    this.judge();
    this.setData({
      hidden:true
    })
  },
  one(){
    var password = this.data.password;
    var length = password.length;
    console.log(length)
    password[length]=1;
    this.setData({
      password:password
    })
    if(length==5){
      this.confirm();
    }
   },
   confirm(){
     var inputPassword="";
     var password = this.data.password
     for(var i = 0 ; i < 6 ; i ++){
       inputPassword+=password[i];
     }
     if(inputPassword==="123456"){
       this.setData({
         hasConfirm:true
       })
       setTimeout(()=>{
         wx.showToast({
           icon:'loading'
         })
       },500)
       if (this.data.hasConfirm) {
        if(this.data.type==3){
          console.log(this.data.cartInfo[0].orderHao);
        this.deleteFormerOrder(this.data.cartInfo[0].orderHao);
        }
       this.generateOrder(0);
      }
        // 跳转到订单页面
        wx.navigateTo({
          url: '/pages/pay-success/indes?price='+this.data.zongE,
        })
        this.clear();
     }
     else{
       wx.showToast({
         title: '密码错误',
         icon:'error'
       })
       this.clear();
     }
   },
   clear(){
     this.setData({
       password:[]
     })
   },
   generateOrder(status){
     console.log(status);
    if(this.data.goodInfo!=null){
      var goodInfo = this.data.goodInfo;
      var newDateTime = Date.parse(new Date());
      var orderHao = app.globalData.userInfo.userId+''+newDateTime;
       wx.request({
         url: 'http://localhost:8081/POST/Order',
         method:'POST',
         data:{
            userId:app.globalData.userInfo.userId,
            goodId:goodInfo[0].goodId,
            goodNum:this.data.option2byNum,
            orderPrice:this.data.zongE+'.00',
            addressId:this.data.hasAddress?this.data.addressInfo.addressId:'0',
            orderHao:orderHao,
            orderStatus:status,
            shopperId:goodInfo[0].shopperId
         },
         header:{
          'content-type':'application/x-www-form-urlencoded'
      },success:(res)=>{
        console.log("订单创建成功！");
      }
    
       })
      }else{
        var cartInfo = this.data.cartInfo;
          var newDateTime = Date.parse(new Date());
      var orderHao = app.globalData.userInfo.userId+''+newDateTime;
        console.log(cartInfo);
        var length =cartInfo.length;
        for(var i = 0 ; i < length ; i ++){
          var innerLength =  cartInfo[i].goodInfos.length;
          for(var j = 0 ; j < innerLength ; j ++){
            if (cartInfo[i].goodInfos[j]!=null) {
              wx.request({
                url: 'http://localhost:8081/POST/Order',
                method:'POST',
                data:{
                  userId:app.globalData.userInfo.userId,
                  goodId:cartInfo[i].goodInfos[j].goodId,
                  goodNum:cartInfo[i].goodInfos[j].goodNum,
                  shopperId:cartInfo[i].goodInfos[j].shopperId==null?cartInfo[i].shopper_id:'0',
                  orderPrice:this.data.zongE,
                  addressId:this.data.hasAddress==false?'0':this.data.addressInfo.addressId,
                  orderHao:orderHao,
                  orderStatus:status
               },
               header:{
                'content-type':'application/x-www-form-urlencoded'
            },success:(res)=>{
              console.log(res.data);
              console.log('-------');
              console.log('订单创建成功！')
              console.log('-------');
              }
              })
            }
          }
        }
        if (this.data.type!=3 && this.data.type!=4 ) {
          if (this.data.type!=3 && this.data.type!=4 ) {
            console.log(this.data.type)
            this.delCart();
          }
        }
      }
   },
   hide(){
     this.setData({
       hidden:true
     })
   },
   delCart(){
     console.log('删除购物车确认页面的商品！');
     var cartInfo = this.data.cartInfo;
     var length = cartInfo.length;
    for(var i = 0 ; i < length ;i ++){
      var innerLength = cartInfo[i].goodInfos.length;
      for(var k = 0 ;k < innerLength ;k ++){
        console.log(cartInfo[i].goodInfos[k]);
    wx.request({
      url: 'http://localhost:8081/DELETE/CartGood',
      method:'POST',
      data:{
        userId:app.globalData.userInfo.userId,
        goodId:cartInfo[i].goodInfos[k].goodId
      },
      header:{ 'content-type':'application/x-www-form-urlencoded'},
      success:(res)=>{
        console.log(res.data);
        console.log("删除购物车数据！");
      }
    })
  }
}
   },
   two(){
     var password = this.data.password;
     var length = password.length;
     password[length]=2;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    three(){
     var password = this.data.password;
     var length = password.length;
     password[length]=3;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    four(){
     var password = this.data.password;
     var length = password.length;
     password[length]=4;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    five(){
     var password = this.data.password;
     var length = password.length;
     password[length]=5;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    six(){
     var password = this.data.password;
     var length = password.length;
     password[length]=6;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    seven(){
     var password = this.data.password;
     var length = password.length;
     password[length]=7;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    eight(){
     var password = this.data.password;
     var length = password.length;
     password[length]=8;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    nine(){
     var password = this.data.password;
     var length = password.length;
     password[length]=9;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    zero(){
     var password = this.data.password;
     var length = password.length;
     password[length]=0;
     this.setData({
       password:password
     })
     if(length==5){
       this.confirm();
     }
    },
    del(){
     var password = this.data.password;
     var length = password.length;
     var newPassword=[];
     for(var  i  = 0 ; i < length-1 ; i++ ){
       newPassword[i]=password[i];
     }
     this.setData({
       password:newPassword
     })
    },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {
    if (!this.data.hasConfirm) {
      console.log("添加待付款");
      this.generateOrder(3);
    }
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})