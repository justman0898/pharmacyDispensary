package com.pharmacy.services;

import com.pharmacy.data.models.User;
import com.pharmacy.data.repositories.UserRepository;
import com.pharmacy.dtos.requests.AddUserRequest;
import com.pharmacy.utils.UserMapper;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AddUserRequest addUser(AddUserRequest addUserRequest);
    User user = UserMapper.toEntity(UserM);


    @Override
    public void addDoctor(AddUserRequest userRequest) {

    }

    @Override
    public void addPharmacist(AddUserRequest userRequest) {

    }

    @Override
    public String loginResponse(AddUserRequest loginRequest) {
        return "";
    }
}
