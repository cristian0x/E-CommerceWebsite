package com.ecommerce.ecommercewebsite.dto;

import com.ecommerce.ecommercewebsite.entity.Address;
import com.ecommerce.ecommercewebsite.entity.OrderedProduct;
import com.ecommerce.ecommercewebsite.entity.User;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private User user;
    private Address address;
    private String status;
    private Long payment_method_id;
    private Long shipping_method_id;
    Set<OrderedProduct> orderedProducts;
}
