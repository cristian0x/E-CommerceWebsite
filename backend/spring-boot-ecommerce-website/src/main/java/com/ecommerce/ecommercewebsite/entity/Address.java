package com.ecommerce.ecommercewebsite.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "street")
    private String street;

    @Column(name = "country")
    private String country;

    @Column(name = "street_number")
    private int street_number;
}
