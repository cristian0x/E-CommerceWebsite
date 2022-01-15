package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.entity.Address;
import com.ecommerce.ecommercewebsite.service.AddressService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("add-address")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void insertAddress(@RequestBody Address address) {
        addressService.insertAddress(address.getUser_id(), address.getCity(), address.getPostal_code(), address.getStreet(),
                address.getCountry(), address.getStreet_number());
    }
}
