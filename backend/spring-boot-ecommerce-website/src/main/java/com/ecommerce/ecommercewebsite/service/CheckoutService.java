package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dto.Purchase;
import com.ecommerce.ecommercewebsite.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
