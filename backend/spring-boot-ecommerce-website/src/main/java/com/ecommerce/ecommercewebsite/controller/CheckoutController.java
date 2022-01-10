package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.dto.Purchase;
import com.ecommerce.ecommercewebsite.dto.PurchaseResponse;
import com.ecommerce.ecommercewebsite.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return purchaseResponse;
    }
}
