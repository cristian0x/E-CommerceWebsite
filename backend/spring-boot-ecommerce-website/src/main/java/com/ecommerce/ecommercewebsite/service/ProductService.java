package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dto.ProductInfo;
import com.ecommerce.ecommercewebsite.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getAllProductsWithPagination(int size, int offset);
    List<Product> getAllProductsWithPaginationAndSorting(int size, int offset, String fieldToSortBy, String sortDirection);
    List<Product> getProductsByCategory(String category_name);
    ProductInfo getProductById(Long id);
}
