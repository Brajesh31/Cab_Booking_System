package com.cabbookingsystem.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String name;
    private String email;
    private String phone;
    private String password;
}
