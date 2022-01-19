package com.ecommerce.ecommercewebsite.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class UpToDateProductsInfo {

    @NotEmpty(message = "Cannot be empty!")
    Set<Integer> products;
}
