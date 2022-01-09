package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dao.OpinionRepository;
import com.ecommerce.ecommercewebsite.dao.ProductRepository;
import com.ecommerce.ecommercewebsite.dto.ProductInfo;
import com.ecommerce.ecommercewebsite.entity.Opinion;
import com.ecommerce.ecommercewebsite.entity.Product;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommercewebsite.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private final OpinionRepository opinionRepository;

    public ProductServiceImpl(ProductRepository productRepository, OpinionRepository opinionRepository) {
        this.productRepository = productRepository;
        this.opinionRepository = opinionRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    @Override
    public List<Product> getAllProductsWithPagination(int size, int offset) {
        return productRepository.getAllProductsWithPagination(size, offset);
    }

    @Override
    public List<Product> getAllProductsWithPaginationAndSorting(int size, int offset, String fieldToSortBy, String sortDirection) {
        return productRepository.getAllProductsWithPaginationAndSorting(size, offset, fieldToSortBy, sortDirection);
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
    public ProductInfo getProductById(Long id) {
        ProductInfo productInfo = new ProductInfo();

        Optional<Product> product = Optional.ofNullable(productRepository.getProductById(id));
		if(product.isPresent()) {

            Product productById = productRepository.getProductById(id);
            List<Opinion> opinions = opinionRepository.getOpinionsByProductId(id);

            productInfo.setName(productById.getName());
            productInfo.setDescription(productById.getDescription());
            productInfo.setUnitPrice(productById.getUnitPrice());
            productInfo.setImagePath(productById.getImagePath());
            productInfo.setUnitsInStock(productById.getUnitsInStock());
            productInfo.setOpinionCount(opinions.size());
            productInfo.setOpinions(opinions);

			return productInfo;
		} else {
			throw new ResourceNotFoundException("Product with id: " + id + " does not exist");
		}
    }

}
