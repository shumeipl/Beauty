package com.pengling.beauty.mapper;

import com.pengling.beauty.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressMapper {
//    新增
    int addAddress(Address address);
//    查询
    List<Address> getAddress(Integer userId);
//    删除
    int deleteAddress(Integer addressId);
//    修改
    int updateAddress(Address address);
}
