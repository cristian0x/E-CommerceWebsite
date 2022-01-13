package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dao.OrderRepository;
import com.ecommerce.ecommercewebsite.dao.OrderedProductRepository;
import com.ecommerce.ecommercewebsite.dao.UserRepository;
import com.ecommerce.ecommercewebsite.dto.Purchase;
import com.ecommerce.ecommercewebsite.dto.PurchaseResponse;
import com.ecommerce.ecommercewebsite.entity.OrderedProduct;
import com.ecommerce.ecommercewebsite.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;

    public CheckoutServiceImpl(UserRepository userRepository, OrderRepository orderRepository, OrderedProductRepository orderedProductRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
    }

    @Override
    public PurchaseResponse placeOrder(Purchase purchase, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        Set<OrderedProduct> orderedProducts = purchase.getOrderedProducts();

        String orderTrackingNumber = generateOrderTrackingNumber();

        orderRepository.insertOrder(orderTrackingNumber, purchase.getAddress().getUser_id(),
                purchase.getUser().getId(), purchase.getStatus(), purchase.getPayment_method_id(), purchase.getShipping_method_id());

        orderedProducts.forEach(orderedProduct -> {
            orderedProductRepository.insertOrderedProduct(orderedProduct.getOrder_id(), orderedProduct.getProduct_id(), orderedProduct.getQuantity());
        });

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
