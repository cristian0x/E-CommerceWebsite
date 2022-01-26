package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "SELECT * FROM orders LIMIT 10", nativeQuery = true)
    List<Order> getAllOrders();

    @Query(value = "SELECT * FROM orders WHERE user_id = :user_id", nativeQuery = true)
    List<Order> getAllOrdersByUserId(@Param("user_id") Long user_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO orders (order_tracking_number, user_id, status, payment_method_id, shipping_method_id, city, postal_code, street, country, street_number, date) " +
            "VALUES (:order_tracking_number, :user_id, :status, :payment_method_id, :shipping_method_id, :city, :postal_code, :street, :country, :street_number, :date)", nativeQuery = true)
    void insertOrder(@Param("order_tracking_number") String order_tracking_number, @Param("user_id") Long user_id, @Param("status") String status,
                     @Param("payment_method_id") Long payment_method_id, @Param("shipping_method_id") Long shipping_method_id, @Param("city") String city,
                     @Param("postal_code") String postal_code, @Param("street") String street, @Param("country") String country, @Param("street_number") int street_number,
                     @Param("date") java.sql.Timestamp date);
}
