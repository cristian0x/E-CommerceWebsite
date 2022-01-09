package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders LIMIT 10", nativeQuery = true)
    List<Order> getAllOrders();
}
