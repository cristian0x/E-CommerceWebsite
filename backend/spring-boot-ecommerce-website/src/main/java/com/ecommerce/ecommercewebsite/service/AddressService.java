package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddressesByUserId(int user_id);
    void insertAddress(Long user_id, String city, String postal_code, String street, String country, int street_number);
}
