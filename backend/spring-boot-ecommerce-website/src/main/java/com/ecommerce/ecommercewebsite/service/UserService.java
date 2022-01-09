package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dto.BasicUserInfo;

public interface UserService {
    BasicUserInfo getBasicUserInfo(String email);
}
