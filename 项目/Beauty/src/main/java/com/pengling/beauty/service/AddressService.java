package com.pengling.beauty.service;

import com.pengling.beauty.entity.Address;
import com.pengling.beauty.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AddressService implements AddressServiceInt{
    @Autowired
    private AddressMapper addressMapper;
    @Override
    public int addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    @Override
    public List<Address> getAddress(Integer userId) {
        return addressMapper.getAddress(userId);
    }

    @Override
    public int deleteAddress(Integer addressId) {
        return addressMapper.deleteAddress(addressId);
    }

    @Override
    public int updateAddress(Address address) {
        return addressMapper.updateAddress(address);
    }
}
