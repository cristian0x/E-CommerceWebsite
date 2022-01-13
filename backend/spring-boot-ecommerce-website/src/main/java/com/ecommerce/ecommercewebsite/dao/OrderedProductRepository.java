package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, String> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ordered_products (order_tracking_number, product_id, quantity) VALUES (:order_tracking_number, :product_id, :quantity)", nativeQuery = true)
    void insertOrderedProduct(@Param("order_tracking_number") String order_tracking_number, @Param("product_id") Long product_id, @Param("quantity") int quantity);
}
