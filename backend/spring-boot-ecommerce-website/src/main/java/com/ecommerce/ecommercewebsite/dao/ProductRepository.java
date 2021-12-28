package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findAllProducts();

    @Query(value = "SELECT * FROM product WHERE id = :id", nativeQuery = true)
    Product getProductById(@Param("id") Long id);

    @Query(value = "SELECT * FROM product p JOIN categories c ON p.category_id = c.id WHERE c.name = :category_name", nativeQuery = true)
    List<Product> getProductsByCategory(@Param("category_name") String category_name);
}
