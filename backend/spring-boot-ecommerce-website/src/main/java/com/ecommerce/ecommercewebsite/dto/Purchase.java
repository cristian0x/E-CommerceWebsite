package com.ecommerce.ecommercewebsite.dto;

import lombok.Data;

@Data
public class Purchase {
    private BasicUserInfo basicUserInfo;
    private OrdersInfo ordersInfo;
}
