package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
}
