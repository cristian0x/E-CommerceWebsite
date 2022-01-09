package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddressesByUserId(int user_id);
}
