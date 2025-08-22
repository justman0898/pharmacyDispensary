package com.pharmacy.data.repositories;

import com.pharmacy.data.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);
    void delete(User user);
    Optional<User> findByUsernameAndPassword(String username, String password);
    List<User> findAll();
    int checkIfUserExists(String username);
}
