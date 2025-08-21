package com.pharmacy.utils;

import com.pharmacy.data.models.User;
import com.pharmacy.dtos.requests.AddUserRequest;

public class UserMapper {

    public static AddUserRequest toAddUserRequest(AddUserRequest addUserRequest) {

        AddUserRequest response = new AddUserRequest();
        response.setUserName(addUserRequest.getUserName());
        response.setPassword(addUserRequest.getPassword());
        return response;

    }

    public static User toEntity(AddUserRequest addUserRequest) {
        User entity = new User();
        entity.setUserName(addUserRequest.getUserName());
        entity.setPassword(addUserRequest.getPassword());

        return entity;
    }
}
