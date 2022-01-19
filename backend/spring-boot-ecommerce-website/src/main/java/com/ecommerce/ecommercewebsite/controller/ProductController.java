package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.dto.ProductInfo;
import com.ecommerce.ecommercewebsite.dto.ProductsRequest;
import com.ecommerce.ecommercewebsite.dto.UpToDateProductsInfo;
import com.ecommerce.ecommercewebsite.entity.Product;
import com.ecommerce.ecommercewebsite.security.payload.response.MessageResponse;
import com.ecommerce.ecommercewebsite.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<?> getAllProducts(@Valid @RequestBody ProductsRequest productsRequest, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid parameters!"));
        }

        List<Product> response;

        if (productsRequest.getFieldToSortBy() == null) {
            response = productService.getAllProductsWithPagination(productsRequest.getSize(), productsRequest.getPage());
        } else {
            response = productService.getAllProductsWithPaginationAndSorting(productsRequest.getSize(), productsRequest.getPage(),
                    productsRequest.getFieldToSortBy(), productsRequest.getSortDirection());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/upToDateProductsInfo")
    public ResponseEntity<?> getUpToDateProductsInfo(@Valid @RequestBody UpToDateProductsInfo upToDateProductsInfo, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Invalid parameters!"));
        }

        Set<Integer> products = upToDateProductsInfo.getProducts();

        return ResponseEntity.ok(productService.getUpToDateProductsInfo(products));
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
