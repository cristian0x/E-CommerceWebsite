package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dto.BasicUserInfo;
import com.ecommerce.ecommercewebsite.dto.OrdersInfo;
import com.ecommerce.ecommercewebsite.dto.Purchase;
import com.ecommerce.ecommercewebsite.dto.PurchaseResponse;
import com.ecommerce.ecommercewebsite.entity.OrderedProduct;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        BasicUserInfo basicUserInfo = purchase.getBasicUserInfo();
        OrdersInfo ordersInfo = purchase.getOrdersInfo();

        Set<OrderedProduct> orderedProductSet = purchase.getOrdersInfo().getOrderedProducts();

        String orderTrackingNumber = generateOrderTrackingNumber();

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        //generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
