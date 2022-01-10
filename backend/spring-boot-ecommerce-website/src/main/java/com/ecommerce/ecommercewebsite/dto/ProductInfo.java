package com.ecommerce.ecommercewebsite.dto;

import com.ecommerce.ecommercewebsite.entity.Opinion;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductInfo {
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private String imagePath;
    private int unitsInStock;
    private int opinionCount;
    private List<Opinion> opinions;
}