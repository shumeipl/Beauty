package com.pengling.beauty.service;

import com.pengling.beauty.entity.Address;

import java.util.List;

public interface AddressServiceInt {
    //    新增
    int addAddress(Address address);
    //    查询
    List<Address> getAddress(Integer userId);
    //    删除
    int deleteAddress(Integer addressId);
    //    修改
    int updateAddress(Address address);
}
