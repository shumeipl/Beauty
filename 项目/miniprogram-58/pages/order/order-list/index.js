const app = getApp();
import { OrderStatus } from '../config';
const utils = require('../../../utils/util');
Page({
  page: {
    size: 5,
    num: 1,
  },

  data: {
    tabs: [
      { key: -1, text: '全部' },
      { key: OrderStatus.PENDING_PAYMENT, text: '待付款', info: '' },
      { key: OrderStatus.PENDING_DELIVERY, text: '待发货', info: '' },
      { key: OrderStatus.PENDING_RECEIPT, text: '待收货', info: '' },
      { key: OrderStatus.COMPLETE, text: '已完成', info: '' },
    ],
    curTab: -1,
    orderList: [],
    listLoading: 0,
    flag:false,
    pullDownRefreshing: false,
    timer:null,
    emptyImg:
      'https://cdn-we-retail.ym.tencent.com/miniapp/order/empty-order-list.png',
    backRefresh: false,
    status: -1,
    flag1:0,
  },

  onLoad(query) {
    let status = parseInt(query.status);
    status = this.data.tabs.map((t) => t.key).includes(status) ? status : -1;
    this.init(status);
    this.pullDownRefresh = this.selectComponent('#wr-pull-down-refresh');
  },

  onShow() {
    if (!this.data.backRefresh) return;
    this.onRefresh();
    this.setData({ backRefresh: false });
  },

  onReachBottom() {
    if (this.data.listLoading === 0) {
      this.getOrderList(this.data.curTab);
    }
  },

  onPageScroll(e) {
    this.pullDownRefresh && this.pullDownRefresh.onPageScroll(e);
  },
  reOrder(e){
    var orderHao=e.currentTarget.dataset.orderhao;
    console.log(orderHao);
    wx.navigateTo({
      url: '../../order-confirm/index?type=4&orderHao='+orderHao+'',
    })
    },
  onPullDownRefresh_(e) {
    const { callback } = e.detail;
    this.setData({ pullDownRefreshing: true });
    this.refreshList(this.data.curTab)
      .then(() => {
        this.setData({ pullDownRefreshing: false });
        callback && callback();
      })
      .catch((err) => {
        this.setData({ pullDownRefreshing: false });
        Promise.reject(err);
      });
  },

  init(status) {
    status = status !== undefined ? status : this.data.curTab;
    this.setData({
      status,
    });
    this.refreshList(status);
  },

  onReTryLoad() {
    this.getOrderList(this.data.curTab);
  },

  onTabChange(e) {
    const { value } = e.detail;
    this.setData({
      status: value,
    });
    this.refreshList(value);
  },
  refreshList(status = -1) {
    this.page = {
      // size: this.page.size,
      num: 1,
    };
    this.getOrderList();
    this.setData({ curTab: status});

    return Promise.all([
      this.getOrderList(status, true),
    ]);
  },
 getOrderList(){
 // 全部
 if (this.data.status==-1) {
 wx.request({
  url: 'http://localhost:8081/GET/OrderInfo/'+app.globalData.userInfo.userId+'',
  method:'GET',
  header:{
    'content-type':'application/json'
  },
  success:(res)=>{
    this.setData({
      curTab:this.data.status,
      orderList:res.data
    })
  }
})
 }
 else if (this.data.status==5) {
  wx.request({
    url: 'http://localhost:8081/GET/OrderInfo/'+app.globalData.userInfo.userId+'/3',
    method:'GET',
    header:{
      'content-type':'application/json'
    },
    success:(res)=>{
      console.log(res.data);
      this.setData({
        flag:true,
        curTab:this.data.status,
        orderList:res.data
      })
    //  this.getTime();
    if (res.data!=[]) {
      this.data.timer=setInterval(()=>{
        this.getTime();
     },1000) 
    }
    }
  })
 }
//  待发货
 else if(this.data.status==10){
  wx.request({
    url: 'http://localhost:8081/GET/OrderInfo/'+app.globalData.userInfo.userId+'/0',
    method:'GET',
    header:{
      'content-type':'application/json'
    },
    success:(res)=>{
      console.log(res.data);
      this.setData({
        curTab:this.data.status,
        orderList:res.data
      })
    }
  })
 }
//  待收货
 else if(this.data.status==40){
  wx.request({
    url: 'http://localhost:8081/GET/OrderInfo/'+app.globalData.userInfo.userId+'/1',
    method:'GET',
    header:{
      'content-type':'application/json'
    },
    success:(res)=>{
      console.log(res.data);
      this.setData({
        curTab:this.data.status,
        orderList:res.data
      })
    }
  })
 }
//  已收货 完成
 else if(this.data.status==50){
  wx.request({
    url: 'http://localhost:8081/GET/OrderInfo/'+app.globalData.userInfo.userId+'/2',
    method:'GET',
    header:{
      'content-type':'application/json'
    },
    success:(res)=>{
      console.log(res.data);
      this.setData({
        curTab:this.data.status,
        orderList:res.data
      });
      var orderList=this.data.orderList;
      wx.request({
        url: 'http://localhost:8081/GET/OrderInfo/'+app.globalData.userInfo.userId+'/4',
        method:'GET',
        header:{
          'content-type':'application/json'
        },
        success:(res1)=>{
          
          console.log(res1.data);
          this.setData({
            curTab:this.data.status,
            orderList:this.data.orderList.concat(res1.data)
          })
        }
      })
    }
  })
 }
 },
  onRefresh() {
    this.refreshList(this.data.curTab);
  },
  cancelOrder03(e){
    wx.request({
      url: 'http://localhost:8081/DELETE/Order',
      method:'POST',
      data:{
        userId:app.globalData.userInfo.userId,
        orderHao:e.currentTarget.dataset.orderHao
      },
      header:{'content-type':'application/x-www-form-urlencoded'},
      success:(res)=>{
        console.log('删除成功！')
      }
    })
  },
  cancelOrder(e){
    console.log(e);
    wx.request({
      url: 'http://localhost:8081/PATCH/OrderStatus',
      method:'POST',
      data:{
        userId:app.globalData.userInfo.userId,
        orderHao:e.currentTarget.dataset.orderhao,
        orderStatus:-1
      },
      header:{'content-type':'application/x-www-form-urlencoded'},
      success:(res)=>{
        console.log(res.data);
        wx.showToast({
          title: '取消成功！',
          icon:"none"
        })
        this.getOrderList();
      }
    })
  },
  getTime(){
    if (this.data.curTab==5) {
    var orderList = this.data.orderList;
    for(var i = 0 ; i < orderList.length ;i ++){
      var date=orderList[i].orderDate;
      // var orderEnd=parseInt(date)+12*60*60*1000;
      var orderEnd=parseInt(date)+12*60*60*1000;
      var orderSY=orderEnd-new Date().getTime();
      // 小时
      orderList[i].SYhour = parseInt(orderSY/1000/60/60);
      // 分钟
      orderList[i].SYmin=parseInt(orderSY/1000/60%60);
      // 秒
      orderList[i].SYsecond=parseInt(orderSY/1000%60);
      this.setData({
        orderList:orderList
      });
     if (orderSY<=0) {
     wx.request({
      url: 'http://localhost:8081/PATCH/OrderStatus',
      method:'POST',
      data:{
        userId:app.globalData.userInfo.userId,
        orderHao:orderList[i].orderHao,
        orderStatus:-1
      },
      header:{'content-type':'application/x-www-form-urlencoded'},
      success:(res)=>{
        if (res.data==1) {
          this.getOrderList();
        }
      }
    })
    this.setData({
      orderList:orderList,
    });
    }
  }
}
},
  toPay(e){
    wx.navigateTo({
      url: '/pages/order-confirm/index?type=3&orderHao='+e.currentTarget.dataset.orderhao,
    })
  },

onUnload(){
 if (this.data.timer) {
   clearInterval(this.data.timer);
 }
}
,
  onOrderCardTap(e) {
    const { order } = e.currentTarget.dataset;
    wx.navigateTo({
      url: `/pages/order/order-detail/index?orderNo=${order.orderNo}`,
    });
  },
  comment(e){
    var orderHao = e.currentTarget.dataset.orderhao;
    wx.navigateTo({
      url: '../../goods/comments/create/index?orderHao='+orderHao+'',
    })
  },
  ack(e){
    console.log(e);
    wx.request({
      url: 'http://localhost:8081/PATCH/OrderStatus',
      method:"POST",
      data:{
        orderHao:e.currentTarget.dataset.orderhao,
        userId:app.globalData.userInfo.userId,
        orderStatus:2
      },
      header:{
        'content-type':'application/x-www-form-urlencoded'
      },
      success:(res)=>{
        wx.showToast({
          title: '您已确认收货',
          icon:'none'
        }),
        this.refreshList();
      }
    })
  }
});
