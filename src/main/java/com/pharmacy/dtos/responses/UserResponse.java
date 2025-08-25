package com.pharmacy.dtos.responses;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String username;
    private String password;
    private String role;

    @Override
    public String toString() {
        return "Username: "+ getUsername()+"\nRole: "+ getRole();
    }

}
