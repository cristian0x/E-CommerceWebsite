package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dao.ProductRepository;
import com.ecommerce.ecommercewebsite.entity.Product;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommercewebsite.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.getProductById(id));
		if(product.isPresent()) {
			return productRepository.getProductById(id);
		} else {
			throw new ResourceNotFoundException("Employee with id: " + id + " does not exist");
		}
    }

}
