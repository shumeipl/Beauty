import Toast from 'tdesign-miniprogram/toast/index';
const app = getApp()
Page({
  data: {
    userInfo: {
      avatarUrl: '',
      nickName: '',
      gender: '',
      phoneNumber: '',
    },
    showUnbindConfirm: false,
    pickerOptions: [
      {
        name: '女',
        code: '0',
      },
      {
        name: '男',
        code: '1',
      },
    ],
    typeVisible: false,
    genderMap: ['女', '男', ''],
  },
  onLoad() {
    this.init();
  },
  onShow(){
    this.init();
  },
  init() {
    this.fetchData();
  },
  fetchData() {
      this.setData({
        userInfo:{avatarUrl:app.globalData.userInfo.avatarUrl,nickName:app.globalData.userInfo.nickName,gender:app.globalData.userInfo.gender,
          phoneNumber:app.globalData.userInfo.phoneNumber},
      });
  },
  onClickCell({ currentTarget }) {
    if(app.globalData.openid!=null){
    const { dataset } = currentTarget;
    const { nickName } = this.data.userInfo;
    console.log(currentTarget);
    switch (dataset.type) {
      case 'gender':
        this.setData({
          typeVisible: true,
        });
        break;
      case 'name':
        wx.navigateTo({
          url: `/pages/usercenter/name-edit/index?name=${nickName}`,
        });
        break;
      case 'avatarUrl':
        this.toModifyAvatar();
        break;
      case 'phoneNumber':
        this.toBindPhoneNumber();
        break;
      default: {
        break;
      }
    }
  }
  else{
    wx.showToast({
      title: '未登录状态',
      icon:'error'
    })
  }
  },
  toBindPhoneNumber(){
   console.log("绑定手机号！")
   wx.navigateTo({
     url: '/pages/usercenter/phone-bind/phone-bind',
   })
  },
  onClose() {
    this.setData({
      typeVisible: false,
    });
  },
  onConfirm(e) {
    const { value } = e.detail;
    console.log(value)
    wx.request({
      url: 'http://localhost:8081/PUT/ChangeUserGender',
      method:'POST',
      data:{
        gender:value,
        userId:app.globalData.userInfo.userId
      },
        header:{
          'content-type':'application/x-www-form-urlencoded'
      },
      success:(res)=>{
        console.log('改变性别成功'),
        app.globalData.userInfo.gender=value
      }
    }),
    this.setData(
      {
        typeVisible: false,
        'userInfo.gender': value,
      },
      () => {
        Toast({
          context: this,
          selector: '#t-toast',
          message: '设置成功',
          theme: 'success',
        });
      },
    );
  },
  loginOut(){
    this.setData({
      userInfo:{},
    });
    app.globalData.openid=null,
    app.globalData.userInfo.gender=3,
    app.globalData.userInfo.avatarUrl="https://cdn-we-retail.ym.tencent.com/miniapp/usercenter/icon-user-center-avatar@2x.png",
    app.globalData.userInfo.nickName="请登录",
    app.globalData.tel=''
  },
  async toModifyAvatar() {
    try {
      const tempFilePath = await new Promise((resolve, reject) => {
        wx.chooseImage({
          count: 1,
          sizeType: ['compressed'],
          sourceType: ['album', 'camera'],
          success: (res) => {
            const { path, size } = res.tempFiles[0];
            var tempFilePaths=res.tempFilePaths
            if (size <= 10485760) {
              resolve(path);
            } else {
              reject({ errMsg: '图片大小超出限制，请重新上传' });
            }
          const fs=wx.getFileSystemManager();
          var that=this;
          fs.readFile({
            filePath:tempFilePaths[0],
            encoding:'base64',
            success:function(data){
              var sd="data:image/png;base64,"+data.data;
              console.log(sd);
              console.log(app.globalData.userInfo);
              wx.request({
                url: 'http://localhost:8081/PUT/ChangeUserAvatar',
                method:'POST',
                data:{
                  userId:app.globalData.userInfo.userId,
                  avatar:sd
                },
                header:{
                  'content-type':'application/x-www-form-urlencoded'
                },
                success:(res)=>{
                  console.log('修改头像成功')
                  app.globalData.userInfo.avatarUrl=sd;
                  that.setData({
                    "userInfo.avatarUrl":sd,
                  })
                }
              })
            }
          })

          },
          
          fail: (err) => reject(err),
        });
      });
      const tempUrlArr = tempFilePath.split('/');
      const tempFileName = tempUrlArr[tempUrlArr.length - 1];
      Toast({
        context: this,
        selector: '#t-toast',
        message: `已选择图片-${tempFileName}`,
        theme: 'success',
      });
    } catch (error) {
      if (error.errMsg === 'chooseImage:fail cancel') return;
      Toast({
        context: this,
        selector: '#t-toast',
        message: error.errMsg || error.msg || '修改头像出错了',
        theme: 'error',
      });
    }
  },
});
