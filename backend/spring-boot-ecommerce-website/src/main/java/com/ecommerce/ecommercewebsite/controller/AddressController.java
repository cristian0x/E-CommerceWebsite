package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.dao.UserRepository;
import com.ecommerce.ecommercewebsite.entity.Address;
import com.ecommerce.ecommercewebsite.service.AddressService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class AddressController {

    private final AddressService addressService;
    private final UserRepository userRepository;

    public AddressController(AddressService addressService, UserRepository userRepository) {
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    @GetMapping("addresses")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Address> getAllAddressesByUserEmail() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userRepository.getUserIdByEmail(userDetails.getUsername());

        return addressService.getAllAddressesByUserId(id);
    }

    @PostMapping("add-address")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public void insertAddress(@RequestBody Address address) {
        addressService.insertAddress(address.getUser_id(), address.getCity(), address.getPostal_code(), address.getStreet(),
                address.getCountry(), address.getStreet_number());
    }
}
