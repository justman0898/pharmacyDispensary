package com.pharmacy.data.models;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private Role role;
}
