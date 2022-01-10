package com.ecommerce.ecommercewebsite.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    private String password;

    @NotBlank
    private String first_name;

    @NotBlank
    private String last_name;

    @NotBlank
    private String phone_number;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}