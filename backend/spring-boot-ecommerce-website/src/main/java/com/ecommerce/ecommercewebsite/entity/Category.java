package com.ecommerce.ecommercewebsite.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
// @Data - known bug
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    //private Set<Product> products;
}
