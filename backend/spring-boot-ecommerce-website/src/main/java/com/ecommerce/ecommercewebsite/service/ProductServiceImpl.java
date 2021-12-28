package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dao.ProductRepository;
import com.ecommerce.ecommercewebsite.entity.Product;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommercewebsite.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public List<Product> getProductsByCategory(String category_name) {
        Optional<List<Product>> productsByCategory = Optional.ofNullable(productRepository.getProductsByCategory(category_name));
        if(productsByCategory.isPresent()) {
            return productRepository.getProductsByCategory(category_name);
        } else {
            throw new ResourceNotFoundException("Category with name: " + category_name + " does not exist");
        }
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = Optional.ofNullable(productRepository.getProductById(id));
		if(product.isPresent()) {
			return productRepository.getProductById(id);
		} else {
			throw new ResourceNotFoundException("Product with id: " + id + " does not exist");
		}
    }

}
