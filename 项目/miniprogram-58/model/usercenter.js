const userInfo = {
  avatarUrl:
    'https://cdn-we-retail.ym.tencent.com/miniapp/usercenter/icon-user-center-avatar@2x.png',
  nickName: null,
  phoneNumber: '',
  gender: 0,
};
const countsData = [
  {
    num: "",
    name: '积分',
    type: 'point',
  },
  {
    num: "",
    name: '优惠券',
    type: 'coupon',
  },
];

const orderTagInfos = [
  {
    orderNum: 0,
    tabType: 5,
  },
  {
    orderNum: 0,
    tabType: 10,
  },
  {
    orderNum: 0,
    tabType: 40,
  },
  {
    orderNum: 0,
    tabType: 0,
  },
];

const customerServiceInfo = {
  servicePhone: '4006336868',
  serviceTimeDuration: '每周三至周五 9:00-12:00  13:00-15:00',
};

export const genSimpleUserInfo = () => ({ ...userInfo });

export const genUsercenter = () => ({
  countsData,
  orderTagInfos,
  customerServiceInfo,
});
