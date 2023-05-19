import { fetchHome } from '../../services/home/home';
import Toast from 'tdesign-miniprogram/toast/index';

Page({
  data: {
    imgSrcs: [],
    tabList: [],
    goodsList: [],
    goodsListLoadStatus: 0,
    pageLoading: false,
    swiperInfo:null,
    current: 1,
    autoplay: true,
    duration: '500',
    interval: 5000,
    navigation: { type: 'dots' },
    swiperImageProps: { mode: 'scaleToFill' },
    goodTypeIndex:'',
    goodTypeText:''
  },

  goodListPagination: {
    index: 0,
    num: 20,
  },

  privateData: {
    tabIndex: 0,
  },

  onShow() {
  },

  onLoad() {
    this.init();
  },

  onReachBottom() {
    if (this.data.goodsListLoadStatus === 0) {
      this.loadGoodsList();
    }
  },

  onPullDownRefresh() {
    this.init();
  },

  init() {
    this.loadHomePage();
    },
 
    loadHomePage() {
      wx.stopPullDownRefresh();
  
      this.setData({
        pageLoading: true,
      });
      wx.request({
        url: 'http://localhost:8081/GET/getHotGood',
        method:'GET',
        header:{
          'content-type':'application/json'
        },
        success:(res)=>{
          console.log(res.data)
          this.setData({
            swiperInfo:res.data,
          });
          var imgSrcs=[];
          for(var i = 0 ;i < res.data.length ;i++){
            imgSrcs.push(res.data[i].goodImg1);
          }
          this.setData({
            imgSrcs:imgSrcs,
            pageLoading: false,
          })
          fetchHome().then(({ tabList }) => {
            this.setData({
              tabList
            });
          this.loadGoodsList(true);
        })
        }
      })
  },
  bangdan(e){
    console.log(e);
    wx.navigateTo({
      url: '/pages/bangdan/bangdan?text='+e.currentTarget.dataset.text+'&name='+e.currentTarget.dataset.name+'',
    })
  },

  tabChangeHandle(e) {
    // this.privateData.tabIndex = e.detail;
    console.log(e.detail);
    this.setData({
      goodTypeIndex:e.detail.value,
      goodTypeText:e.detail.label,
    });
    this.loadGoodsList(true);
  },

  onReTry() {
    this.loadGoodsList();
  },

  async loadGoodsList(fresh = false) {
    if (fresh) {
      wx.pageScrollTo({
        scrollTop: 0,
      });
    }

    this.setData({ goodsListLoadStatus: 1 });

    const pageSize = this.goodListPagination.num;
    let pageIndex = this.privateData.tabIndex * pageSize + this.goodListPagination.index + 1;
    if (fresh) {
      pageIndex = 1;
    }

    try {
      let nextList={};
      var that = this;
      console.log(this.data.goodTypeText);
      if(this.data.goodTypeIndex==0){
      wx.request({
        url: 'http://localhost:8081/GET/GoodBySelected/'+pageIndex+'/'+pageSize+'',
        method:'GET',
        header:{
          'content-type':'application/json'
        },
        success(res){
          nextList=res.data.resultList;
          that.setData({
            goodsList: fresh ? nextList : that.data.goodsList.concat(nextList[0]),
          })
        }
      });
      }
      else {
        wx.request({
          url: 'http://localhost:8081/GET/GoodByFeature/'+this.data.goodTypeText+'',
          method:'GET',
          header:{
            'content-type':'application/json'
          },
          success(res){
            nextList=res.data;
            that.setData({
              goodsList: fresh ? nextList : that.data.goodsList.concat(nextList[0]),
            })
          }
        });
      }
      this.setData({
        goodsListLoadStatus: 0,
      });
      this.goodListPagination.index = pageIndex;
      // this.goodListPagination.num = pageSize;
    } catch (err) {
      this.setData({ goodsListLoadStatus: 3 });
    }
  },
  goodListClickHandle(e) {
    const { index } = e.detail;
    const { goodId } = this.data.goodsList[index];
    const shopperId=this.data.goodsList[e.detail.index].shopperId;
    wx.navigateTo({
      url: '/pages/goods/details/index?goodId='+goodId+'&shopperId='+shopperId+'',
    });
  },
  goodListAddCartHandle() {
    Toast({
      context: this,
      selector: '#t-toast',
      message: '点击加入购物车',
    });
  },

  navToSearchPage() {
    wx.navigateTo({ url: '/pages/goods/search/index' });
  },

  navToActivityDetail({ detail }) {
    const { index: promotionID = 0 } = detail || {};
    wx.navigateTo({
      url: `/pages/promotion-detail/index?promotion_id=${promotionID}`,
    });
  },
});
