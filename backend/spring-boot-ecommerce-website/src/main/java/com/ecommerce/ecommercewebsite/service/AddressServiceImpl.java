package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dao.AddressRepository;
import com.ecommerce.ecommercewebsite.entity.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddressesByUserId(int user_id) {
        return addressRepository.getAllAddressesByUserId(user_id);
    }

    @Override
    public void insertAddress(Long user_id, String city, String postal_code, String street, String country, int street_number) {
        addressRepository.insertAddress(user_id, city, postal_code, street, country, street_number);
    }
}
