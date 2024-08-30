package com.crio.xlido.services;

import com.crio.xlido.entities.User;
import com.crio.xlido.repositories.IUserRepository;

public class UserService{

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }   

    public User CREATE_USER(String email, String password){
        User user = new User(email, password);
        return userRepository.save(user);
    }

}