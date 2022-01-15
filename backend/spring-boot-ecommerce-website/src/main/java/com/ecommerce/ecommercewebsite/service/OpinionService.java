package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.entity.Opinion;

import java.math.BigDecimal;
import java.util.List;

public interface OpinionService {
    List<Opinion> getAllOpinions();
    void insertOpinion(String description, Long product_id, Long user_id, BigDecimal rating, java.sql.Timestamp date);
}
