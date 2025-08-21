package com.pharmacy.utils;

import com.pharmacy.data.models.User;
import com.pharmacy.dtos.UserResponse;
import com.pharmacy.dtos.requests.AddUserRequest;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setUsername(user.getUserName());
        response.setPassword(user.getPassword());
        response.setRole(user.getRole().toString());
        return response;
    }

    public static User toEntity(AddUserRequest addUserRequest) {
        User entity = new User();
        entity.setUserName(addUserRequest.getUserName());
        entity.setPassword(addUserRequest.getPassword());

        return entity;
    }
}
