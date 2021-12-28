package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.entity.Product;
import com.ecommerce.ecommercewebsite.service.ProductService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product;
    }
}
