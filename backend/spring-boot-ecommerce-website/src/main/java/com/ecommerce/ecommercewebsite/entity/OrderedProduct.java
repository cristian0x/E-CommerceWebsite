package com.ecommerce.ecommercewebsite.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ordered_products")
@Data
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "product_id")
    private int product_id;

    @Column(name = "quantity")
    private int quantity;
}
