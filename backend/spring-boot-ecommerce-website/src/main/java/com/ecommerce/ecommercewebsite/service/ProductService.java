package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category_name);
    Product getProductById(Long id);
}
