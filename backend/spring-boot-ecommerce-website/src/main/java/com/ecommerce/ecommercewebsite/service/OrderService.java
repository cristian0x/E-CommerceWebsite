package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUserId(Long user_id);
}
