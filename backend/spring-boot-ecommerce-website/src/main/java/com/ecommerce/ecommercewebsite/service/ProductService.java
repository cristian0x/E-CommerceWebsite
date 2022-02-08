package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dto.FilterRequest;
import com.ecommerce.ecommercewebsite.dto.ProductInfo;
import com.ecommerce.ecommercewebsite.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getAllProductsWithPagination(int size, int offset);
    List<Product> getAllProductsWithPaginationAndSorting(int size, int offset, String fieldToSortBy, String sortDirection);
    List<Product> getProductsByCategory(String category_name);
    Set<Product> getUpToDateProductsInfo(Set<Integer> products);
    ProductInfo getProductById(Long id);
    int getProductQuantity();
    List<Product> getFilteredProducts(FilterRequest filterRequest);
}
