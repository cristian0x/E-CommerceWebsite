package com.ecommerce.ecommercewebsite.dto;

import lombok.Data;

@Data
public class ProductsRequest {
    Long product_id;
    int page; // offset
    int size; // items per page
    String fieldToSortBy;
    String sortDirection;
}
