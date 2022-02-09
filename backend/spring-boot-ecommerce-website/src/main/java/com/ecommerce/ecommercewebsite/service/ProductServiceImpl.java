package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dao.OpinionRepository;
import com.ecommerce.ecommercewebsite.dao.ProductRepository;
import com.ecommerce.ecommercewebsite.dto.FilterRequest;
import com.ecommerce.ecommercewebsite.dto.ProductInfo;
import com.ecommerce.ecommercewebsite.dto.UpToDateProductInfoResponse;
import com.ecommerce.ecommercewebsite.entity.Opinion;
import com.ecommerce.ecommercewebsite.entity.Product;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommercewebsite.exception.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private final OpinionRepository opinionRepository;

    @PersistenceContext
    private EntityManager entityManager;

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
    public UpToDateProductInfoResponse getUpToDateProductsInfo(Set<Long> products, HashMap<Long, Integer> quantity) {
        UpToDateProductInfoResponse upToDateProductInfoResponse = new UpToDateProductInfoResponse();

        Set<Product> productSet = productRepository.getUpToDateProductsInfo(products);

        upToDateProductInfoResponse.setProducts(productSet);

        BigDecimal totalPrice = productSet.stream()
                .map(product -> product.getUnitPrice().multiply(BigDecimal.valueOf(quantity.get(product.getId()))))
                .reduce((a, b) -> a.add(b)).get();

        upToDateProductInfoResponse.setTotalPrice(totalPrice);

        return upToDateProductInfoResponse;
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

    @Override
    public int getProductQuantity() {
        return productRepository.getAllProductQuantity();
    }

    @Override
    public List<Product> getFilteredProducts(FilterRequest filterRequest) {
        Set<Integer> categories = (filterRequest.getCategories() == null || filterRequest.getCategories().isEmpty())
                ? Stream.of(1, 2, 3, 4, 5, 6, 7, 8).collect(Collectors.toSet()) : filterRequest.getCategories();
        int minPrice = ((Integer) filterRequest.getMinPrice() != null || String.valueOf(filterRequest.getMinPrice()).trim() != "")
                ? filterRequest.getMinPrice() : 0;
        int maxPrice = ((Integer) filterRequest.getMaxPrice() != null || String.valueOf(filterRequest.getMaxPrice()).trim() != "")
                ? filterRequest.getMaxPrice() : Integer.MAX_VALUE;
        int page = filterRequest.getPage();
        int size = filterRequest.getSize();
        String fieldToSortBy = ((filterRequest.getFieldToSortBy()).isEmpty()) ? null : filterRequest.getFieldToSortBy();
        String sortDirection = (filterRequest.getSortDirection().isEmpty()) ? "" : filterRequest.getSortDirection();
        String searchValue = (filterRequest.getSearchValue() == null) ? "" : filterRequest.getSearchValue();

        String nativeQuery;
        String searchQuery = "";
        List<Product> filteredProducts;
        if (maxPrice == 0) maxPrice = Integer.MAX_VALUE;

        if (!searchValue.isEmpty()) {
            searchQuery = "AND MATCH(name, description) AGAINST(:keyword IN NATURAL LANGUAGE MODE) ";
        }

        nativeQuery = "SELECT * FROM product WHERE unit_price >= " + minPrice + " AND unit_price <= " + maxPrice + " AND category_id IN :categories "
                + searchQuery + "ORDER BY " + fieldToSortBy + " " + sortDirection + " LIMIT " + size + " OFFSET " + page;

        try {
            Query query = entityManager.createNativeQuery(nativeQuery);
            query.setParameter("categories", categories);
            if (!searchValue.isEmpty()) {
                query.setParameter("keyword", searchValue);
            }

            //filteredProducts = query.getResultList();

            filteredProducts = (List<Product>) query.getResultStream().collect(Collectors.toList());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }

        return filteredProducts;
    }
}
