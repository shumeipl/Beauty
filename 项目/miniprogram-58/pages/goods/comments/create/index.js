// import { getCommentDetail } from '../../../../services/good/comments/fetchCommentDetail';
const app = getApp();
import Toast from 'tdesign-miniprogram/toast/index';
Page({
  data: {
    serviceRateValue: 1,
    goodRateValue: 1,
    conveyRateValue: 1,
    orderHao:null,
    isAnonymous: false,
    uploadFiles: [],
    gridConfig: {
      width: 218,
      height: 218,
      column: 3,
    },
    isAllowedSubmit: false,
    imgUrl: '',
    goodInfo:null,
    commentInfo:[],
    title: '',
    goodsDetail: '',
    imageProps: {
      mode: 'aspectFit',
    },
  },

  onLoad(options) {
    this.getOrderInfo(options.orderHao);
    this.setData({
      orderHao:options.orderHao
    });
  },
  getOrderInfo(orderHao){
    wx.request({
      url: 'http://localhost:8081/GET/OrderInfoByOrderHao/'+orderHao+'/'+app.globalData.userInfo.userId+'',
      method:'POST',
      header:{
        'content-type':'application/json'
      },
      success:(res)=>{
        var goodInfo = res.data;
        this.setData({
          goodInfo:res.data.goodInfo
        })
      }
    })
  },

  onRateChange(e) {
    var index = e.currentTarget.dataset.index;
    const { value } = e?.detail;
    var goodInfo = this.data.goodInfo;
    goodInfo[index].goodRateValue=value
    this.setData({
      goodInfo:goodInfo
    });
    const item = e?.currentTarget?.dataset?.item;
    this.setData({ [item]: value }, () => {
      this.updateButtonStatus();
    });
  },

  onAnonymousChange(e) {
    var index = e.currentTarget.dataset.index;
    const status = !!e?.detail?.checked;
    var goodInfo = this.data.goodInfo
    var Status=status?1:0;
    goodInfo[index].status=Status
    this.setData({
      goodInfo:goodInfo
    });
    this.setData({ isAnonymous: status });
  },
  handleSuccess(e) {
    const { files } = e.detail;
    this.setData({
      uploadFiles: files,
    });
  },

  handleRemove(e) {
    const { index } = e.detail;
    const { uploadFiles } = this.data;
    uploadFiles.splice(index, 1);
    this.setData({
      uploadFiles,
    });
  },
  changeOrderStatus(){
    wx.request({
      url: 'http://localhost:8081/PATCH/OrderStatus',
      method:"POST",
      data:{
        orderHao:this.data.orderHao,
        userId:app.globalData.userInfo.userId,
        orderStatus:4
      },
      header:{
        'content-type':'application/x-www-form-urlencoded'
      },
      success:(res)=>{
        wx.showToast({
          title: '评价提交成功',
          icon:'none'
        })
      }
    })
  },
  generateComment(){
    var goodInfo = this.data.goodInfo;
    console.log(goodInfo);
    var length = goodInfo.length;
    for(var i = 0 ; i < length ; i ++){
      wx.request({
        url: 'http://localhost:8081/POST/Comment',
        method:'POST',
        data:{
          userId:app.globalData.userInfo.userId,
          goodId:goodInfo[i].goodId,
          commentLevel:goodInfo[i].goodRateValue,
          commentContent:goodInfo[i].isAllowedSubmit,
          orderHao:this.data.orderHao,
          commentWay:1,
        },
        header:{
          'content-type':'application/x-www-form-urlencoded'
       },
       success:(res)=>{
         console.log(i);
         console.log(this.data.goodInfo[i-1].goodId);
         console.log(res.data);
         wx.request({
           url: 'http://localhost:8081/PATCH/Score',
           method:'POST',
           data:{
            goodId:this.data.goodInfo[i-1].goodId,
            feature:this.data.goodInfo[i-1].feature,
            addScore:this.data.goodInfo[i-1].goodRateValue
           },
           header:{
           'content-type':'application/x-www-form-urlencoded'},
           success:(res)=>{
             if (res.data==1) {
               console.log("添加分数成功！");
             }
           }
         })
       }
      })
    }
    this.changeOrderStatus();
  },
  onTextAreaChange(e) {
    // 评价内容
    const value = e?.detail?.value;
    var index = e.currentTarget.dataset.index;
    var goodInfo=this.data.goodInfo;
    goodInfo[index].isAllowedSubmit=value
    this.setData({
      goodInfo:goodInfo
    });
    this.textAreaValue = value;
    this.updateButtonStatus();
  },

  updateButtonStatus() {
    const { serviceRateValue, goodRateValue, conveyRateValue, isAllowedSubmit } = this.data;
    const { textAreaValue } = this;
    const temp = serviceRateValue && goodRateValue && conveyRateValue && textAreaValue;
    if (temp !== isAllowedSubmit) {this.setData({ isAllowedSubmit: temp });
  };
  },

  onSubmitBtnClick(e) {
    const { isAllowedSubmit } = this.data;
    if (!isAllowedSubmit) return;
    this.generateComment();
    Toast({
      context: this,
      selector: '#t-toast',
      message: '评价提交成功',
      icon: 'check-circle',
    });
    wx.navigateBack();
  },
});
