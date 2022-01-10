package com.ecommerce.ecommercewebsite.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_tracking_number")
    private String orderTrackingNumber;

    @Column(name = "user_address_id")
    private int user_address_id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "status")
    private String status;

    @Column(name = "payment_method_id")
    private int payment_method_id;

    @Column(name = "date")
    @CreationTimestamp
    private Date date;
}
