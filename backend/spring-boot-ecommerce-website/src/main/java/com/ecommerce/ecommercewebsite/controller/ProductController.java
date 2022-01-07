package com.ecommerce.ecommercewebsite.controller;

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

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{category_name}")
    public List<Product> getProductsByCategory(@PathVariable String category_name) {
        return productService.getProductsByCategory(category_name);
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

}
