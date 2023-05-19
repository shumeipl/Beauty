// pages/bangdan/bangdan.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    scoreList:null,
    feature:null,
    name:null
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    console.log(options.text);
    this.setData({
      feature:options.text,
      name:options.name
    });
    this.getScoreList();
  },
  getScoreList(){
    wx.request({
      url: 'http://localhost:8081/GET/TopTen/'+this.data.feature,
      method:'GET',
      header:{
        'content-type':'application/json'
        },
      success:(res)=>{
        console.log(res.data);
        this.setData({
          scoreList:res.data
        })
      }
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
  gotoDetail(e){
    console.log(e);
    var index = e.currentTarget.dataset.index;
    wx.navigateTo({
      url: '/pages/goods/details/index?goodId='+index+'',
    })
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