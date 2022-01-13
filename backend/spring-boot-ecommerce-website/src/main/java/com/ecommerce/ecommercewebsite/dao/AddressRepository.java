package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM user_address WHERE user_id = :user_id", nativeQuery = true)
    List<Address> getAllAddressesByUserId(@Param("user_id") int user_id);

    @Query(value = "SELECT ud.street, ud.street_number, ud.postal_code, ud.city, ud.country FROM user_address ud JOIN users u ON ud.user_id = u.id WHERE u.email = :email", nativeQuery = true)
    List<Address> getAllAddressesByUserEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_address (user_id, city, postal_code, street, country, street_number) VALUES (:user_id, :city, :postal_code, :street, :country, :street_number)", nativeQuery = true)
    void insertAddress(@Param("user_id") Long user_id, @Param("city") String city, @Param("postal_code") String postal_code,
                       @Param("street") String street, @Param("country") String country, @Param("street_number") int street_number);
}
