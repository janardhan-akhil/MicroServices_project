package com.user_service.service;

import com.user_service.entities.User;

import java.util.List;

public interface UserService {

    // user operations

    // create
    User saveUser(User user);

    // get All users
    List<User> getAllUsers();

    // get userById
    User getUserById(String userID);

    //TODO : Delete
    //TODO: Update


}
