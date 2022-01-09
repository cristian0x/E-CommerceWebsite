package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM user_address WHERE user_id = :user_id", nativeQuery = true)
    List<Address> getAllAddressesByUserId(@Param("user_id") int user_id);

    @Query(value = "SELECT ud.street, ud.street_number, ud.postal_code, ud.city, ud.country FROM user_address ud JOIN users u ON ud.user_id = u.id WHERE u.email = :email", nativeQuery = true)
    List<Address> getAllAddressesByUserEmail(@Param("email") String email);
}
