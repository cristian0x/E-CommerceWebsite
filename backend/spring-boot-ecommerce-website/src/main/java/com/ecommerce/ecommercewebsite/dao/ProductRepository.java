package com.ecommerce.ecommercewebsite.dao;

import com.ecommerce.ecommercewebsite.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product LIMIT 10", nativeQuery = true)
    List<Product> findAllProducts();

    @Query(value = "SELECT * FROM product LIMIT :size OFFSET :offset", nativeQuery = true)
    List<Product> getAllProductsWithPagination(@Param("size") int size, @Param("offset") int offset);

    @Query(value = "SELECT * FROM product ORDER BY :fieldToSortBy :sortDirection LIMIT :size OFFSET :offset", nativeQuery = true)
    List<Product> getAllProductsWithPaginationAndSorting(@Param("size") int size, @Param("offset") int offset,
                                                         @Param("fieldToSortBy") String fieldToSortBy, @Param("sortDirection") String sortDirection);

    @Query(value = "SELECT * FROM product WHERE id = :id", nativeQuery = true)
    Product getProductById(@Param("id") Long id); // optional

    @Query(value = "SELECT * FROM product p JOIN categories c ON p.category_id = c.id WHERE c.name = :category_name", nativeQuery = true)
    List<Product> getProductsByCategory(@Param("category_name") String category_name);
}
