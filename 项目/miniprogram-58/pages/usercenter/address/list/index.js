/* eslint-disable no-param-reassign */
const app = getApp();
import { fetchDeliveryAddressList } from '../../../../services/address/fetchAddress';
import Toast from 'tdesign-miniprogram/toast/index';
import { resolveAddress, rejectAddress } from './util';
import { getAddressPromise } from '../edit/util';

Page({
  data: {
    addressList: [],
    deleteID: '',
    showDeleteConfirm: false,
    isOrderSure: false,
    openid:null
  },

  /** 选择模式 */
  selectMode: true,
  /** 是否已经选择地址，不置为true的话页面离开时会触发取消选择行为 */
  hasSelect: false,

  onLoad(query) {
    const { selectMode = '', isOrderSure = '', id = '' } = query;
    this.setData({
      isOrderSure: !!isOrderSure,
      id,
      openid:app.globalData.openid
    });
    this.selectMode = !!selectMode;
    this.init();
  },
  gotoLogin(){
    wx.switchTab({
      url: '/pages/usercenter/usercenter',
    })
  },

  init() {
    this.getAddressList();
  },
  onUnload() {
    if (this.selectMode && !this.hasSelect) {
      rejectAddress();
    }
  },
  getAddressList() {
    const userId = app.globalData.userInfo.userId;
    wx.request({
      url: 'http://localhost:8081/GET/AddressList/'+userId+'',
      method:'GET',
      success:(res)=>{
        let addressList=[];
        let address={};
        for(let i = 0 ; i < res.data.length;i++){
          let a = res.data[i];
          address={addressId:a.addressId,phone:a.addressTel,
          name:a.addressConsignee,countryName:a.countryName,
          provinceName:a.provinceName,cityName:a.cityName,
          districtName:a.districtName,detailAddress:a.detailAddress,
          addressTag:a.addressTag,isDefault:a.addressDefault
          }
          addressList.push(address);
        }
        this.setData({
          addressList:addressList
        })

      }
    })
  },
  onShow(){
    this.init();
  },
  getWXAddressHandle() {
    wx.chooseAddress({
      success: (res) => {
        if (res.errMsg.indexOf('ok') === -1) {
          Toast({
            context: this,
            selector: '#t-toast',
            message: res.errMsg,
            icon: '',
            duration: 1000,
          });
          return;
        }
        Toast({
          context: this,
          selector: '#t-toast',
          message: '添加成功',
          icon: '',
          duration: 1000,
        });
        const { length: len } = this.data.addressList;
        this.setData({
          [`addressList[${len}]`]: {
            name: res.userName,
            phoneNumber: res.telNumber,
            address: `${res.provinceName}${res.cityName}${res.countryName}${res.detailInfo}`,
            isDefault: 0,
            tag: '微信地址',
            id: len,
          },
        });
      },
    });
  },
  confirmDeleteHandle({ detail }) {
    const { id } = detail || {};
    if (id !== undefined) {
      this.setData({ deleteID: id, showDeleteConfirm: true });
      Toast({
        context: this,
        selector: '#t-toast',
        message: '地址删除成功',
        theme: 'success',
        duration: 1000,
      });
    } else {
      Toast({
        context: this,
        selector: '#t-toast',
        message: '需要组件库发新版才能拿到地址ID',
        icon: '',
        duration: 1000,
      });
    }
  },
  deleteAddressHandle(e) {
    const { id } = e.currentTarget.dataset;
    console.log(e.detail.addressId);
    wx.request({
      url: 'http://localhost:8081/DELETE/Address',
      method:'POST',
      data:{
        addressId:e.detail.addressId
      },
      header:{
        'content-type':'application/x-www-form-urlencoded'
    },
    success:(res)=>{
      if(res.data==1){
        this.getAddressList();
      }
    }
    })
    // this.setData({
    //   addressList: this.data.addressList.filter((address) => address.id !== id),
    //   deleteID: '',
    //   showDeleteConfirm: false,
    // });
  },
  editAddressHandle({ detail }) {
    this.waitForNewAddress();
    // const { id } = detail || {};
    wx.navigateTo({
      url: '/pages/usercenter/address/edit/index?addressId='+detail.addressId+'&phone='+detail.phone+'&name='+detail.name+'&provinceName='+detail.provinceName+'&cityName='+detail.cityName+
      '&districtName='+detail.districtName+'&detailAddress='+detail.detailAddress+'&addressTag='+
      detail.addressTag+"&isDefault="+detail.isDefault+"&addressId="+detail.addressId,
    })
  },
  selectHandle({ detail }) {
    console.log("selectHandle");
    this.selectMode=true;
    if (this.selectMode) {
      this.hasSelect = true;
      resolveAddress(detail);
      let pages=getCurrentPages();
      let prevPage =pages[pages.length-2];
      prevPage.setData({
        addressInfo:detail
      }),
      wx.navigateBack({ delta: 1 });
    } else {
      console.log(1)
      wx.navigateBack({ delta: 1 });
      // this.editAddressHandle({ detail });
    }
  },
  createHandle() {
    this.waitForNewAddress();
    wx.navigateTo({ url: '/pages/usercenter/address/edit/index' });
  },

  waitForNewAddress() {
    getAddressPromise()
      .then((newAddress) => {
        let addressList = [...this.data.addressList];

        newAddress.phoneNumber = newAddress.phone;
        newAddress.address = `${newAddress.provinceName}${newAddress.cityName}${newAddress.districtName}${newAddress.detailAddress}`;
        newAddress.tag = newAddress.addressTag;

        if (!newAddress.addressId) {
          newAddress.id = `${addressList.length}`;
          newAddress.addressId = `${addressList.length}`;

          if (newAddress.isDefault === 1) {
            addressList = addressList.map((address) => {
              address.isDefault = 0;

              return address;
            });
          } else {
            newAddress.isDefault = 0;
          }

          addressList.push(newAddress);
        } else {
          addressList = addressList.map((address) => {
            if (address.addressId === newAddress.addressId) {
              return newAddress;
            }
            return address;
          });
        }

        addressList.sort((prevAddress, nextAddress) => {
          if (prevAddress.isDefault && !nextAddress.isDefault) {
            return -1;
          }
          if (!prevAddress.isDefault && nextAddress.isDefault) {
            return 1;
          }
          return 0;
        });

        this.setData({
          addressList: addressList,
        });
      })
      .catch((e) => {
        if (e.message !== 'cancel') {
          Toast({
            context: this,
            selector: '#t-toast',
            message: '地址编辑发生错误',
            icon: '',
            duration: 1000,
          });
        }
      });
  },
});
