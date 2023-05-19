const app = getApp();
Page({
  data: {
    nameValue: '',
  },
  onLoad(options) {
    const { name } = options;
    this.setData({
      nameValue: name,
    });
  },
  onSubmit() {
    // 更改数据库  更改昵称
    app.globalData.userInfo.nickName=this.data.nameValue;
    console.log(this.data.nameValue)
    wx.request({
      url: 'http://localhost:8081/PUT/ChangeUserNickname',
      method:'POST',
      data:{
        nickName:this.data.nameValue,
        userId:app.globalData.userInfo.userId,
        avatarUrl:app.globalData.userInfo.avatarUrl
      },
      header:{
        'content-type':'application/x-www-form-urlencoded'
      },
      success:(res)=>{
        console.log('新增成功')
      }
    })
    wx.navigateBack({ backRefresh: true });
  },
  clearContent() {
    this.setData({
      nameValue: '',
    });
  },
});
