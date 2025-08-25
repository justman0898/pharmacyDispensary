package com.pharmacy.utils;

import com.pharmacy.data.models.Role;
import com.pharmacy.data.models.User;
import com.pharmacy.dtos.responses.UserResponse;
import com.pharmacy.dtos.requests.AddUserRequest;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getUserId());
        response.setUsername(user.getUserName());
        response.setPassword(user.getUserPassword());
        response.setRole(user.getRole().toString());
        return response;
    }

    public static User toEntity(AddUserRequest addUserRequest) {
        User entity = new User();
        entity.setUserName(addUserRequest.getUserName());
        entity.setUserPassword(addUserRequest.getPassword());
        entity.setRole(Role.valueOf(addUserRequest.getRole()));
        return entity;
    }
}
