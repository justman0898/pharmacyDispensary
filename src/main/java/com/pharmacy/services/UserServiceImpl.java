package com.pharmacy.services;

import com.pharmacy.data.models.User;
import com.pharmacy.data.repositories.UserRepository;
import com.pharmacy.dtos.UserResponse;
import com.pharmacy.dtos.requests.AddUserRequest;
import com.pharmacy.exceptions.UserNotFoundException;
import com.pharmacy.utils.UserMapper;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserResponse signUp(AddUserRequest loginRequest) {
        User user = userRepository.save(UserMapper.toEntity(loginRequest));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse login(AddUserRequest loginRequest) {
        Optional<User> user = userRepository.findByUsernameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
        User foundUser = user.orElseThrow(()-> new UserNotFoundException("User not found"));
        return UserMapper.toResponse(foundUser);
    }
}
