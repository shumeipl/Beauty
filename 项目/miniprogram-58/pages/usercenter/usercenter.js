// pages/usercenter/usercenter.js
const menuData = [
  [
    {
      title: '收货地址',
      tit: '',
      url: '',
      type: 'address',
    },
    {
      title: '成为卖家',
      tit: '',
      url: '',
      type: 'coupon',
    },
    // {
    //   title: '积分',
    //   tit: '',
    //   url: '',
    //   type: 'point',
    // },
  ],
  [
    // {
    //   title: '帮助中心',
    //   tit: '',
    //   url: '',
    //   type: 'help-center',
    // },
    {
      title: '客服热线',
      tit: '',
      url: '',
      type: 'service',
      icon: 'service',
    },
  ],
];

const orderTagInfos = [
  {
    title: '待付款',
    iconName: 'wallet',
    orderNum: 0,
    tabType: 5,
    status: 1,
  },
  {
    title: '待发货',
    iconName: 'wallet',
    orderNum: 0,
    tabType: 10,
    status: 1,
  },
  {
    title: '待收货',
    iconName: 'wallet',
    orderNum: 0,
    tabType: 40,
    status: 1,
  },
  {
    title: '待评价',
    iconName: 'wallet',
    orderNum: 0,
    tabType: 60,
    status: 1,
  },
];
const getDefaultData = () => ({
  showMakePhone: false,
  userInfo: {
    avatarUrl: '',
    nickName: '正在登录...',
    phoneNumber: '',
  },
  menuData,
  orderTagInfos,
  customerServiceInfo: {},
  currAuthStep: 1,
  showKefu: true,
  versionNo: '',
});
const app =getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    defaultAvatarUrl:
    'https://cdn-we-retail.ym.tencent.com/miniapp/usercenter/icon-user-center-avatar@2x.png',
    AuthStepType:"AuthStepType.TWO",
    openid:null,
    userInfo:{},
    menuData,
    orderTagInfos,
    customerServiceInfo: {},
    showKefu: true,
    versionNo: '',
    hasLogin:false,
    showMakePhone: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getVersionInfo();
    this.setData({
      userInfo:app.globalData.userInfo,
      openid:app.globalData.openid
    })
  },

  getVersionInfo() {
    const versionInfo = wx.getAccountInfoSync();
    const { version, envVersion = __wxConfig } = versionInfo.miniProgram;
    this.setData({
      versionNo: envVersion === 'release' ? version : envVersion,
    });
  },

  login(){
    wx.getUserProfile({
      desc: '展示用户信息',
    }).then(res=>{
      const avatarUrl1 = res.userInfo.avatarUrl;
      const nickName1=res.userInfo.nickName;
      const gender1=res.userInfo.gender;              
       wx.login({
         //成功放回
         success:(res)=>{
           let code=res.code
           // 通过code换取openId
           wx.request({
             url: 'https://api.weixin.qq.com/sns/jscode2session',
             method:'GET',
             data:{
              appid:'wx4ee3c06d12b23d97',
              js_code:code,
              secret:'be2f825fb35bedba4d65704c91997c2a',
               grant_type:'authorization_code'
             },
             success:(res)=>{
              //  接口追寻openid是否已经存在
              this.setData({
                openid:res.data.openid
              })
              console.log(this.data.openid)
              app.globalData.openid=res.data.openid
              wx.request({
                url: 'http://localhost:8081/GET/UserByOpenId/'+this.data.openid+'',
                method:'GET',
                header:{
                  'content-type':'application/json'
                },
                success:(res)=>{
                  console.log(res.data);
                  if(res.data==null){
                    wx.request({
                      url: 'http://localhost:8081/PUT/User',
                      method:'POST',
                      data: {
                          openid:this.data.openid,
                          nickName:nickName1,
                            avatarUrl:avatarUrl1,
                            gender:gender1
                      },
                      header:{
                        'content-type':'application/x-www-form-urlencoded'
                      },
                      success:(res)=>{
                        console.log('新增成功')
                        app.globalData.userInfo=this.data.userInfo;       
                      }
                    })
                  }
                  else{
                    this.setData({
                      userInfo:{nickName:res.data.userNickname,
                      avatarUrl:res.data.userAvatar}
                    });
                    console.log(1);
                    console.log(this.data.userInfo);
                    app.globalData.userInfo={nickName:res.data.userNickname,gender:res.data.userGender,
                      avatarUrl:res.data.userAvatar,userId:res.data.userId,phoneNumber:res.data.userTel}
                  }
                }
              });
             },
             fail:(err)=>{
              console.log('失败');
             }
           })
         }
       })
  })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },
  jumpAllOrder(){
    wx.navigateTo({ url: '/pages/order/order-list/index' });
  },
  gotoUserEditPage() {
      wx.navigateTo({ url: '/pages/usercenter/person-info/index' });
  },
  jumpNav(e) {
    const status = e.detail.tabType;

    if (status === 0) {
      wx.navigateTo({ url: '/pages/order/shohou/shouhou' });
    } else {
      wx.navigateTo({ url: `/pages/order/order-list/index?status=${status}` });
    }
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {
    this.init();
  },
  init() {
    this.fetchUserInfo();
  },
  fetchUserInfo(){
    this.setData({
      userInfo:app.globalData.userInfo
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

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.init();
  },
  
  onClickCell({ currentTarget }) {
    const { type } = currentTarget.dataset;

    switch (type) {
      case 'address': {
        wx.navigateTo({ url: '/pages/usercenter/address/list/index' });
        break;
      }
      case 'service': {
        this.openMakePhone();
        break;
      }
      case 'help-center': {
       wx.showToast({
         title: '帮助中心',
       })
        break;
      }
      case 'point': {
        wx.showToast({
          title: '积分',
        })
        break;
      }
      case 'coupon': {
        wx.navigateTo({ url: '/pages/cooperation/cooperation' });
        break;
      }
      default: {
        Toast({
          context: this,
          selector: '#t-toast',
          message: '未知跳转',
          icon: '',
          duration: 1000,
        });
        break;
      }
    }
  },
  openMakePhone() {
    this.setData({ showMakePhone: true });
  },

  closeMakePhone() {
    this.setData({ showMakePhone: false });
  },
  call() {
    wx.makePhoneCall({
      phoneNumber: '17752696277', 
    });
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