package com.sakila.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStaffRequest {
    private String firstName;
    private String lastName;
    private Short addressId;
    private String email;
    private Byte storeId;
    private Boolean active;
    private String username;
    private String password;
}