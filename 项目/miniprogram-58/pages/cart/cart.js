// pages/cart/cart.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
  cartInfo:[],
  hasLogin:false,
  hasCartInfo:false,
  imgUrl: 'https://cdn-we-retail.ym.tencent.com/miniapp/template/empty-cart.png',
  tip: '购物车是空的',
  btnText: '去首页',
  cartIndex:'',
  quanxuan:false,
  zongJia:0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    if(app.globalData.openid!=null){
      this.setData({
        hasLogin:true,
      })
    }
    if(app.globalData.openid!=null){
      this.getCartInfo();
    }
  },
  // 更新数据库购物车数据
  updataDB(){
    var cartInfo = this.data.cartInfo;
    var length=cartInfo.length;
    for(var i = 0 ; i < length; i++){
      var innerLength=cartInfo[i].goodInfos.length;
      for(var j = 0 ;  j<innerLength ; j++){
        var userId= app.globalData.userInfo.userId;
        var goodId =cartInfo[i].goodInfos[j].goodId;
        var allSelected = cartInfo[i].allSelected;
        var selected = cartInfo[i].goodInfos[j].selected;
        var goodNum=cartInfo[i].goodInfos[j].goodNum;
        wx.request({
          url: 'http://localhost:8081/PATCH/CartGood',
          method:'POST',
          data:{
            userId:userId,
            goodId:goodId,
            allSelected:allSelected,
            selected:selected,
            goodNum:goodNum
          },
          header:{
          'content-type':'application/x-www-form-urlencoded'
            },
            success:(res)=>{
              if (res.data==1) {
                console.log("success");
              }
              this.setData({
                zongJia:goodNum*goodPrice
              })
            }
        })
      }
    }
  },
  handleClick() {
    wx.navigateTo({
      url: '/pages/index/index',
    })
  },
  gotoLogin(){
    wx.switchTab({
      url: '/pages/usercenter/usercenter',
    })
  },
  getCartInfo(){
    wx.request({
      url: 'http://localhost:8081/GET/Cart/'+app.globalData.userInfo.userId,
      method:'GET',
      header:{
      'content-type':'application/json'},
     success:(res)=>{
       if(res.data!=null){
         this.setData({
           hasCartInfo:true,
         })
       }
       this.setData({
         cartInfo:res.data
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
         if (cartInfo[i].goodInfos[j].selected==1) {
           price+=cartInfo[i].goodInfos[j].goodPrice*cartInfo[i].goodInfos[j].goodNum;
         }
         else{
           continue;
         }
       }
     }
     this.setData({
       zongJia:price
     })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    if(app.globalData.openid!=null){
      this.setData({
        hasLogin:true,
      })
    }
    if(app.globalData.openid!=null){
      this.getCartInfo();
    }
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

  },
  delete(e){
    let goodId = e.currentTarget.id;
    wx.request({
      url: 'http://localhost:8081/DELETE/CartGood',
      method:'POST',
      data:{
        userId:app.globalData.userInfo.userId,
        goodId:goodId
      },
      header:{ 'content-type':'application/x-www-form-urlencoded'},
      success:(res)=>{
        if(res.data==1)
        {
          this.getCartInfo();
        }
      }
    })
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

  },
  touchStart(e){
    var cartInfo= this.data.cartInfo;
    cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].startX=e.touches[0].clientX;
    this.setData({
      cartInfo:cartInfo
    });
  },
  touchMove(e){
    var cartInfo= this.data.cartInfo;
    if(e.touches[0].clientX<cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].startX){
      cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].active=true;
      this.setData({
        cartInfo:cartInfo
      })
    }else{
      cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].active=false;
      this.setData({
        cartInfo:cartInfo,
      })
    }
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {

  },
  getIndex(e){
   this.setData({
     index:e.currentTarget.dataset.index
   });
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  },
  desc(e){
    var cartInfo =this.data.cartInfo;
    var index = e.currentTarget.dataset.index; 
    if (cartInfo[this.data.index].goodInfos[index].goodNum>1) {
      cartInfo[this.data.index].goodInfos[index].goodNum-=1;
      // 请求
        this.setData({
          cartInfo:cartInfo
        })
        this.updataDB()
        this.countPrice();
    }
    else{
      wx.showToast({
        title: '该商品数量不能减少了',
        icon:'none'
      })
    }
   
  },
  add(e){
    var index = e.currentTarget.dataset.index;
    var cartInfo =this.data.cartInfo;
    var index = e.currentTarget.dataset.index; 
    if (cartInfo[this.data.index].goodInfos[index].goodNum<=cartInfo[this.data.index].goodInfos[index].goodStorage) {
      cartInfo[this.data.index].goodInfos[index].goodNum+=1;
      // 请求
        this.setData({
          cartInfo:cartInfo
        })
        this.updataDB();
        this.countPrice();
    }
    else{
      wx.showToast({
        title: '库存不足',
        icon:'none'
      })
      }
  },
  changeNumber(e){
    console.log(e.detail.value);
    var index =e.currentTarget.dataset.index;
    var cartInfo =this.data.cartInfo;
    if (e.detail.value<1) {
      wx.showToast({
        title: '无效数据',
        icon:'none'
      })
    }
    else if (e.detail.value>cartInfo[this.data.index].goodInfos[index].goodStorage) {
      wx.showToast({
        title: '库存不够，还剩'+cartInfo[this.data.index].goodInfos[index].goodStorage+'件',
        icon:'none'
      })
    }
    else{
      // 请求修改goodNum
    cartInfo[this.data.index].goodInfos[index].goodNum=e.detail.value;
    this.setData({
      cartInfo:cartInfo
    })
    this.countPrice();
    this.updataDB();
    }
  },
  btngoodIcon(e){
    var cartInfo=this.data.cartInfo;
    if(cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].selected==1){
      cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].selected=0;
      cartInfo[this.data.index].allSelected=0;
      // 请求
      this.setData({
        cartInfo:cartInfo,
        quanxuan:false
      });
    }
    else{
      cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].selected=1;
      var length =  cartInfo[this.data.index].goodInfos.length;
      var num=0;
      for(var i = 0 ; i < length; i++){
        if (cartInfo[this.data.index].goodInfos[i].selected==1) {
          num++;
        }
      }
      if (num==length) {
        cartInfo[this.data.index].allSelected=1;
      }
      // 请求
      this.setData({
        cartInfo:cartInfo
      });
      console.log(cartInfo);
    };
  },
   async btnGoodIcon(e){
   await this.btngoodIcon(e);
    this.countPrice()
    this.updataDB();
  },
  gotoGoodDetail(e){
    var cartInfo = this.data.cartInfo;
    wx.navigateTo({
      url: '/pages/goods/details/index?goodId='+cartInfo[this.data.index].goodInfos[e.currentTarget.dataset.index].goodId+''
    })
  },
  quanXuan(){
  console.log(2)
   var quanxuan=this.data.quanxuan;
   quanxuan=!quanxuan;
   var cartInfo = this.data.cartInfo;
   var length = cartInfo.length;
   if (quanxuan) {
     for(var  i = 0 ; i < length ;i ++){
       cartInfo[i].allSelected=1;
       var innerLength = cartInfo[i].goodInfos.length;
       for(var j =0  ; j < innerLength ; j++){
         cartInfo[i].goodInfos[j].selected=1;
       }
     }
   }
   else{
    for(var  i = 0 ; i < length ;i ++){
      cartInfo[i].allSelected=0;
      var innerLength = cartInfo[i].goodInfos.length;
       for(var j =0  ; j < innerLength ; j++){
         cartInfo[i].goodInfos[j].selected=0;
       }
    }
   }
   this.setData({
     quanxuan:quanxuan,
     cartInfo:cartInfo
   })
   this.countPrice();
   this.updataDB();
  },
  btnQuanXuan(e){
    var cartInfo = this.data.cartInfo;
    var length = cartInfo[e.currentTarget.dataset.index].goodInfos.length;
    if (cartInfo[e.currentTarget.dataset.index].allSelected==1) {
      cartInfo[e.currentTarget.dataset.index].allSelected=0;
      for(var  i = 0 ; i < length;i++){
        cartInfo[e.currentTarget.dataset.index].goodInfos[i].selected=0;
        this.setData({
          quanxuan:false
        })
      }
    }
    else{
      cartInfo[e.currentTarget.dataset.index].allSelected=1;
      var length = cartInfo.length;
      var num=0;
      for(var  i = 0 ; i < length ; i ++){
        if ( cartInfo[i].allSelected=1) {
          num++;
        }
      }
      if (num==length) {
        this.setData({
          quanxuan:true
        })
      }
      var length1 = cartInfo[this.data.index].goodInfos.length
      for(var  i = 0 ; i < length1;i++){
        cartInfo[e.currentTarget.dataset.index].goodInfos[i].selected=1;
      }
    }
    this.setData({
      cartInfo:cartInfo
    })
    this.countPrice();
    this.updataDB();
  },
  jieSuan(){
  wx.navigateTo({
    url: '/pages/order-confirm/index?type='+1+"",
  })
  }
})
