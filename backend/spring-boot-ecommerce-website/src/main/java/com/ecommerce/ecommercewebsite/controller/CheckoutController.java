package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.dto.Purchase;
import com.ecommerce.ecommercewebsite.dto.PurchaseResponse;
import com.ecommerce.ecommercewebsite.service.CheckoutService;
import com.ecommerce.ecommercewebsite.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final OrderService orderService;

    public CheckoutController(CheckoutService checkoutService, OrderService orderService) {
        this.checkoutService = checkoutService;
        this.orderService = orderService;
    }

    @PostMapping("/purchase")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase, userDetails);
        return purchaseResponse;
    }
}
