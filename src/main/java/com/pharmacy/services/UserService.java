package com.pharmacy.services;

import com.pharmacy.dtos.responses.UserResponse;
import com.pharmacy.dtos.requests.AddUserRequest;


public interface UserService {


    UserResponse signUp(AddUserRequest loginRequest);
    UserResponse login(AddUserRequest loginRequest);
}
