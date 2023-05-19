const utils = require('../../../utils/util');
const layoutMap = {
  0: 'vertical',
};
Page({
  data: {
    pageLoading: false,
    commentList: [],
    pageNum: 1,
    myPageNum: 1,
    pageSize: 10,
    commentList1:[],
    total: 0,
    myTotal: 0,
    hasLoaded: false,
    layoutText: layoutMap[0],
    loadMoreStatus: 0,
    myLoadStatus: 0,
    goodId:0,
    commentLevel: '',
    hasImage: '',
    commentType: '',
    totalCount: 0,
    countObj: {
      badCount: '0',
      commentCount: '0',
      goodCount: '0',
      middleCount: '0',
      hasImageCount: '0',
      uidCount: '0',
    },
  },
  onLoad(options) {
    console.log(options);
    this.setData({
      goodId:options.goodId
    })
    this.getCount(options);
    this.getComments(options);
  },
  async getCount(options) {
    // 总的评价数量
    wx.request({
      url: 'http://localhost:8081/GET/getCountofComment/'+this.data.goodId,
      method:"GET",
      header:{
        'content-type':'application/json'
        },
     success:(res)=>{
       var countObj=this.data.countObj;
       countObj.commentCount=res.data;
       this.setData({
         countObj:countObj
       })
     }
    });
    // 好的
    wx.request({
      url: 'http://localhost:8081/GET/getGreatCount/'+options.goodId,
      method:"GET",
      header:{
        'content-type':'application/json'
        },
     success:(res)=>{
       var countObj=this.data.countObj;
       countObj.goodCount=res.data;
       this.setData({
         countObj:countObj
       })
     }
    });
    // 中的
    wx.request({
      url: 'http://localhost:8081/GET/getMiddleCount/'+options.goodId,
      method:"GET",
      header:{
        'content-type':'application/json'
        },
     success:(res)=>{
       var countObj=this.data.countObj;
       countObj.middleCount=res.data;
       this.setData({
         countObj:countObj
       })
     }
    });
    // 差的
    wx.request({
      url: 'http://localhost:8081/GET/getBadCount/'+options.goodId,
      method:"GET",
      header:{
        'content-type':'application/json'
        },
     success:(res)=>{
       var countObj=this.data.countObj;
       countObj.badCount=res.data;
       this.setData({
         countObj:countObj
       })
     }
    });
  },
  async init(reset = true) {
   var commentLevel = this.data.commentLevel;
   var goodId=this.data.goodId;
   if (commentLevel==3) {
     wx.request({
       url: 'http://localhost:8081/GET/getGreatComment/'+goodId,
       method:'GET',
       header:{
        'content-type':'application/json'
        },
       success:(res)=>{
         console.log(res.data);
         this.setData({
           commentList:res.data
         })
         this.changeTimeFormat();
       }
     })
   }
   else if(commentLevel==2){
    wx.request({
      url: 'http://localhost:8081/GET/getMiddleComment/'+this.data.goodId,
      method:'GET',
      header:{
       'content-type':'application/json'
       },
      success:(res)=>{
        this.setData({
          commentList:res.data
        })
        this.changeTimeFormat();
      }
    })
   }
   else if(commentLevel==1){
    wx.request({
      url: 'http://localhost:8081/GET/getBadComment/'+this.data.goodId,
      method:'GET',
      header:{
       'content-type':'application/json'
       },
      success:(res)=>{
        this.setData({
          commentList:res.data
        })
        this.changeTimeFormat();
      }
    })
   }
   else{
     wx.request({
       url: 'http://localhost:8081/GET/getAllComment/'+goodId,
       method:'GET',
       header:{
        'content-type':'application/json'
        },
       success:(res)=>{
         console.log(res.data);
         this.setData({
          commentList:res.data
        })
        this.changeTimeFormat();
       }
     })
   }
  },
  changeTimeFormat(){
    console.log(1);
    var commentList = this.data.commentList;
    for(var i = 0 ; i < commentList.length ; i ++){
      commentList[i].commentDate=utils.formatDate(commentList[i].commentDate-0);
      console.log(1);
      console.log(commentList[i].commentDate)
    }
    this.setData({
      commentList:commentList
    })
  },
  getScoreArray(score) {
    var array = [];
    for (let i = 0; i < 5; i++) {
      if (i < score) {
        array.push(2);
      } else {
        array.push(0);
      }
    }
    return array;
  },
  getComments(options) {
    const commentLevel = -1;
    console.log(commentLevel)
    if (commentLevel !== -1) {
      this.setData({
        commentLevel: commentLevel,
      });
    }
    this.init(true);
  },
  changeTag(e) {
    var { commenttype } = e.currentTarget.dataset;
    var { commentType } = this.data;
    console.log(commentType);
    //  好评3 中评2 差评1
    if (commentType === commenttype) return;
    this.setData({
      loadMoreStatus: 0,
      commentList: [],
      total: 0,
      myTotal: 0,
      myPageNum: 1,
      pageNum: 1,
    });
    if (commenttype === '' || commenttype === '5') {
      this.setData({
        hasImage: '',
        commentLevel: '',
      });
    } else if (commenttype === '4') {
      this.setData({
        hasImage: '1',
        commentLevel: '',
      });
    } else {
      this.setData({
        hasImage: '',
        commentLevel: commenttype,
      });
    }
    if (commenttype === '5') {
      this.setData({
        myLoadStatus: 1,
        commentType: commenttype,
      });
      this.getMyCommentsList();
    } else {
      this.setData({
        myLoadStatus: 0,
        commentType: commenttype,
      });
      this.init(true);
    }
  },
  onReachBottom() {
    const { total = 0, commentList } = this.data;
    if (commentList.length === total) {
      this.setData({
        loadMoreStatus: 2,
      });
      return;
    }

    this.init(false);
  },
});
