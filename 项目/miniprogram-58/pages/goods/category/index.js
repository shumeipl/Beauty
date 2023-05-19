import { getCategoryList } from '../../../services/good/fetchCategoryList';
Page({
  data: {
    list: [],
  },
  async init() {
    try {
      const result = await getCategoryList();
      this.setData({
        list: result,
      });
    } catch (error) {
      console.error('err:', error);
    }
  },

  onShow() {
  
  },
  onChange(e) {
    console.log("onChange:"+e.detail.item.groupId);
    wx.navigateTo({
      url: '/pages/goods/list/index?groupId='+e.detail.item.groupId,
    });
  },
  onLoad() {
    this.init(true);
  },
});
