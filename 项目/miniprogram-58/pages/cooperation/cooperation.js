// pages/cooperation/cooperation.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    name:null,
    company:null,
    duty:null,
    address:null,
    telphone:null,
    department:null,
    userId:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.setData({
      userId:app.globalData.userInfo.userId
    })
  },
  gotoLogin(){
    wx.navigateBack({
      delta:1
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },
  changeName(e){
    this.setData({
      name:e.detail.value
    })
  },
  changeCompany(e){
    this.setData({
      company:e.detail.value
    })
  },
  changeDepartment(e){
    this.setData({
      department:e.detail.value
    })
  },
  changeDuty(e){
    this.setData({
      duty:e.detail.value
    })
  },
  changeAddress(e){
    this.setData({
      address:e.detail.value
    })
  },
  changeTel(e){
    this.setData({
      telphone:e.detail.value
    })
  },
  submit(){
    var name=this.data.name;
    var company = this.data.company;
    var duty = this.data.duty;
    var address=this.data.address;
    var telphone=this.data.telphone;
    var department=this.data.department;
    if(name!=null&&company!=null&&duty!=null&&address!=null&&telphone!=null&&department!=null){
      wx.request({
        url: 'http://localhost:8081/POST/Cooperation',
        method:'POST',
        data:{name:name,company:company,department:department,duty:duty,address:address,telphone:telphone,userId:app.globalData.userInfo.userId},
        header:{
          'content-type':'application/x-www-form-urlencoded'
       },
       success:(res)=>{
        
          wx.showToast({
            title: '提交成功',
            icon:'success'
          })
          // wx.navigateBack({
          //   delta:1
          // })
         }
      })
      
    }
    else{
      wx.showToast({
        title: '请填写完整信息',
        icon:'error'
      })
    }
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