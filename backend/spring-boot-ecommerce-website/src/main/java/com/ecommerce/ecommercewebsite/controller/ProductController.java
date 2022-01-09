package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.dto.ProductInfo;
import com.ecommerce.ecommercewebsite.dto.ProductsRequest;
import com.ecommerce.ecommercewebsite.entity.Product;
import com.ecommerce.ecommercewebsite.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public List<Product> getAllProducts(@RequestBody ProductsRequest productsRequest) {

        List<Product> response;

        if (productsRequest.getFieldToSortBy() == null) {
            response = productService.getAllProductsWithPagination(productsRequest.getSize(), productsRequest.getPage());
        } else {
            response = productService.getAllProductsWithPaginationAndSorting(productsRequest.getSize(), productsRequest.getPage(),
                    productsRequest.getFieldToSortBy(), productsRequest.getSortDirection());
        }

        return response;
    }

    @GetMapping("/category/{category_name}")
    public List<Product> getProductsByCategory(@PathVariable String category_name) {
        return productService.getProductsByCategory(category_name);
    }

    @GetMapping("/product/{id}")
    public ProductInfo getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

}
