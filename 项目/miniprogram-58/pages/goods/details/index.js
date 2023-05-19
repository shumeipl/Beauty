const app = getApp();
import Toast from 'tdesign-miniprogram/toast/index';
import { fetchGood } from '../../../services/good/fetchGood';
import { fetchActivityList } from '../../../services/activity/fetchActivityList';
import {
  getGoodsDetailsCommentList,
  getGoodsDetailsCommentsCount,
} from '../../../services/good/fetchGoodsDetailsComments';

import { cdnBase } from '../../../config/index';

const imgPrefix = `${cdnBase}/`;

const recLeftImg = `${imgPrefix}common/rec-left.png`;
const recRightImg = `${imgPrefix}common/rec-right.png`;
const obj2Params = (obj = {}, encode = false) => {
  const result = [];
  Object.keys(obj).forEach((key) =>
    result.push(`${key}=${encode ? encodeURIComponent(obj[key]) : obj[key]}`),
  );

  return result.join('&');
};

Page({
  data: {
    images:[],
    goodDetail:'',
    shopperId:'',
    commentsList: [],
    commentsStatistics: {
      badCount: 0,
      commentCount: 0,
      goodCount: 0,
      goodRate: 0,
      hasImageCount: 0,
      middleCount: 0,
    },
    isShowPromotionPop: false,
    activityList: [],
    recLeftImg,
    recRightImg,
    details: {},
    goodsTabArray: [
      {
        name: '商品',
        value: '', // 空字符串代表置顶
      },
      {
        name: '详情',
        value: 'goods-page',
      },
    ],
    storeLogo: `${imgPrefix}common/store-logo.png`,
    jumpArray: [
      {
        title: '首页',
        url: '/pages/home/home',
        iconName: 'home',
      },
      {
        title: '购物车',
        url: '/pages/cart/cart',
        iconName: 'cart',
        showCartNum: true,
      },
    ],
    isStock: true,
    cartNum: 0,
    soldout: false,
    buttonType: 1,
    buyNum: 1,
    selectedAttrStr: '',
    skuArray: [],
    primaryImage: '',
    specImg: '',
    isSpuSelectPopupShow: false,
    isAllSelectedSku: false,
    buyType: 0,
    outOperateStatus: false, // 是否外层加入购物车
    operateType: 0,
    selectSkuSellsPrice: 0,
    maxLinePrice: 0,
    minSalePrice: 0,
    maxSalePrice: 0,
    list: [],
    goodId: '',
    navigation: { type: 'fraction' },
    current: 0,
    autoplay: true,
    duration: 500,
    interval: 5000,
    soldNum: 0, // 已售数量
  },

  handlePopupHide() {
    this.setData({
      isSpuSelectPopupShow: false,
    });
  },

  showSkuSelectPopup(type) {
    this.setData({
      buyType: type || 0,
      outOperateStatus: type >= 1,
      isSpuSelectPopupShow: true,
    });
  },

  buyItNow() {
    this.showSkuSelectPopup(1);
  },

  toAddCart() {
    this.showSkuSelectPopup(2);
  },

  toNav(e) {
    const { url } = e.detail;
    wx.switchTab({
      url: url,
    });
  },

  showCurImg(e) {
    const { index } = e.detail;
    const { images } = this.data.details;
    wx.previewImage({
      current: images[index],
      urls: images, // 需要预览的图片http链接列表
    });
  },

  // 获取已选择的sku名称

  addCart(e) {
    let userId=app.globalData.userInfo.userId;
    wx.request({
      url: 'http://localhost:8081/PUT/GoodToCart',
      method:'POST',
      data:{
        userId:userId,
        shopperId:this.data.shopperId,
        goodId:this.data.goodId,
        goodNum:this.data.buyNum
      },
      header:{
        'content-type':'application/x-www-form-urlencoded'
      },
      success:(res)=>{
        console.log(res.data);
        if(res.data=='1'){
          this.handlePopupHide();
        }
        if(res.data.status==500){
          wx.showToast({
            title: '请登录',
            icon:'error'
          })
        }
      }
    })
    
  },

  gotoBuy(type) {
  wx.navigateTo({
    url: '/pages/order-confirm/index?type=2&goodId='+this.data.goodId+'&goodNum='+this.data.buyNum+'',
  })
  },

  specsConfirm() {
    const { buyType } = this.data;
    if (buyType === 1) {
      this.gotoBuy();
    } else {
      this.addCart();
    }
    // this.handlePopupHide();
  },

  changeNum(e) {
    this.setData({
      buyNum: e.detail.buyNum,
    });
  },

  closePromotionPopup() {
    this.setData({
      isShowPromotionPop: false,
    });
  },

  promotionChange(e) {
    const { index } = e.detail;
    wx.navigateTo({
      url: `/pages/promotion-detail/index?promotion_id=${index}`,
    });
  },

  showPromotionPopup() {
    this.setData({
      isShowPromotionPop: true,
    });
  },
  getDetail(goodId) {
    var that=this;
    wx.request({
      url: 'http://localhost:8081/GET/GoodRoostById/'+goodId+'',
      method:'GET',
      header:{
        'content-type':'application/json'
      },
      success(res){
      const details =res.data;
      const isPutOnSale=1;
      var goodImg1 = details[0].goodImg1;
      var goodImg2 = details[0].goodImg2;
      var goodImg3 = details[0].goodImg3;
      var goodDetail = details[0].goodDetail;
      that.setData({
        details:details[0],
        goodDetail:goodDetail,
        isStock: details[0].goodStorage > 0,
        maxSalePrice: details[0].goodPrice,
        maxLinePrice: details[0].goodPrice ,
        minSalePrice: details[0].goodPrice ,
        primaryImage:details[0].goodImg1,
        soldout: isPutOnSale === 0,
        soldNum:details[0].goodSoldNum,
        images:[goodImg1,goodImg2,goodImg3]
      });
      console.log(that.data.images);
    }
  })
},

  async getCommentsList(goodId) {
    wx.request({
      url: 'http://localhost:8081/GET/getNewestComment/'+goodId,
      method:'GET',
      header:{
        'content-type':'application/json'
        },
      success:(res)=>{
        console.log(res.data);
        this.setData({
          commentsList:res.data
        })
      }
      }
    )
    
    // try {
    //   const code = 'Success';
    //   const data = await getGoodsDetailsCommentList();
    //   const { homePageComments } = data;
    //   if (code.toUpperCase() === 'SUCCESS') {
    //     const nextState = {
    //       commentsList: homePageComments.map((item) => {
    //         return {
    //           goodsSpu: item.spuId,
    //           userName: item.userName || '',
    //           commentScore: item.commentScore,
    //           commentContent: item.commentContent || '用户未填写评价',
    //           userHeadUrl: item.isAnonymity
    //             ? this.anonymityAvatar
    //             : item.userHeadUrl || this.anonymityAvatar,
    //         };
    //       }),
    //     };
    //     this.setData(nextState);
    //   }
    // } catch (error) {
    //   console.error('comments error:', error);
    // }
      },
  /** 获取评价统计 */
  getCommentsStatistics(goodId) {
    wx.request({
      url: 'http://localhost:8081/GET/getCountofComment/'+goodId,
      method:'GET',
      header:{
        'content-type':'application/json'
        },
      success:(res)=>{
        console.log(res.data);
        var commentsStatistics = this.data.commentsStatistics;
        commentsStatistics.commentCount=res.data
        this.setData({
          commentsStatistics:commentsStatistics
        });
      }
    });
    wx.request({
      url: 'http://localhost:8081/GET/getGreatRate/'+goodId,
      method:'GET',
      header:{
        'content-type':'application/json'
      },
      success:(res)=>{
        console.log(res.data)
        var commentsStatistics=this.data.commentsStatistics;
        commentsStatistics.goodRate=res.data;
        this.setData({
          commentsStatistics:commentsStatistics
        })
      }
    });

  },

  /** 跳转到评价列表 */
  navToCommentsListPage() {
    wx.navigateTo({
      url: `/pages/goods/comments/index?goodId=${this.data.goodId}`,
    });
  },
  onShow(){
  this.getDetail(this.data.shopperId);
  },
  onLoad(query) {
    const { goodId } = query;
    console.log(query)
    this.setData({
      goodId: goodId,
      shopperId:query.shopperId,
    });
    this.getCommentsStatistics(goodId);
    console.log(query.shopperId);
    this.getDetail(goodId);
    this.getCommentsList(goodId);
  },
});
