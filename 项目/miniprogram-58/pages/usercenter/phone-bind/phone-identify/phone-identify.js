// pages/edit-tel/identify/identify.js
const app =getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
  code:'',
  tel:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log(options.phoneNumber);
    this.setData({
      tel:options.phoneNumber
    }),
    wx.request({
      url: 'http://localhost:8081/PUT/SendCodeToUser',
      method:'POST',
      data:{
        tel:options.phoneNumber,
        userId:app.globalData.userInfo.userId
      },
      header:{
        'content-type':'application/x-www-form-urlencoded'
      },
      success:(res)=>{
        console.log("申请成功")
      }
    })
  },
  bind(){
    wx.request({
      url: 'http://localhost:8081/PUT/IdentifyCode',
      method:'POST',
      data:{
        userId:app.globalData.userInfo.userId
      },
      header:{
        'content-type':'application/x-www-form-urlencoded'
      },
      success:(res)=>{
        console.log(res);
        if(res.data==this.data.code){
          app.globalData.tel=this.data.tel
          wx.showToast({
            title: '绑定成功',
            icon:'success',
          })
          wx.request({
            url: 'http://localhost:8081/PUT/UpdateUserTel',
            method:'POST',
            data:{
              userId:app.globalData.userInfo.userId,
              tel:this.data.tel
            },
            header:{
              'content-type':'application/x-www-form-urlencoded'
            },
            successs:(res)=>{
              console.log('success');
            }
          })
          wx.navigateBack({
            delta:2
          })
        }
        else{
          wx.showToast({
            title: '验证码有误',
          })
        }
      }
    })
  },
  getIdentifyCode(e){
    console.log(e.detail.value);
    this.setData({
      code:e.detail.value
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