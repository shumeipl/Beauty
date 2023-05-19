// pages/edit-tel/edit-tel.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
     phoneNumber:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
  },
  
send(e){
  console.log(this.data.phoneNumber);
  if (!(/^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\d{8}$/.test(this.data.phoneNumber))) {
    wx.showToast({
    title: '手机号码有误',
    duration: 2000,
    icon:'none'
    });
  }
else{
 wx.navigateTo({
   url: '/pages/usercenter/phone-bind/phone-identify/phone-identify?phoneNumber='+this.data.phoneNumber,
 })
}
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
  getPhoneNumber(e){
    this.setData({
      phoneNumber:e.detail.value
    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})