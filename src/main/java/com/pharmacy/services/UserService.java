package com.pharmacy.services;

import com.pharmacy.data.models.User;
import com.pharmacy.dtos.requests.AddUserRequest;


public interface UserService {

    void addDoctor(AddUserRequest userRequest);
    void addPharmacist(AddUserRequest userRequest);
    String loginResponse(AddUserRequest loginRequest); //same parameters with add user because they both have same fields. should return user id

}
