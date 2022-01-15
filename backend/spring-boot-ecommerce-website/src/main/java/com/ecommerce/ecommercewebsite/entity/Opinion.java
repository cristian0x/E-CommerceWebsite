package com.ecommerce.ecommercewebsite.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "opinions")
@Data
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "date")
    @CreationTimestamp
    private Date date;
}
