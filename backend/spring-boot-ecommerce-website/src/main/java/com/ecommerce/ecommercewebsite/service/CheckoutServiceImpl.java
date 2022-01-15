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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderedProductRepository orderedProductRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public CheckoutServiceImpl(UserRepository userRepository, OrderRepository orderRepository, OrderedProductRepository orderedProductRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderedProductRepository = orderedProductRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase, UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername());
        Set<OrderedProduct> orderedProducts = purchase.getOrderedProducts();

        Query query = entityManager.createNativeQuery("SELECT UUID()");
        String UUID = query.getSingleResult().toString();

        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        orderRepository.insertOrder(UUID, purchase.getAddress().getUser_id(), user.getId(),
                purchase.getStatus(), purchase.getPayment_method_id(), purchase.getShipping_method_id(),
                purchase.getCity(), purchase.getPostal_code(), purchase.getStreet(), purchase.getCountry(),
                purchase.getStreet_number(), date);

        orderedProducts.forEach(orderedProduct -> {
            orderedProductRepository.insertOrderedProduct(UUID, orderedProduct.getProduct_id(), orderedProduct.getQuantity());
        });

        return new PurchaseResponse(UUID);
    }
}
