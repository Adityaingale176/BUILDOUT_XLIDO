package com.crio.xlido.entities;

public class User {

    private final Long userId;
    private final String email;
    private final String password;

    public User(String email, String password){
        this.userId = null;
        this.email = email;
        this.password = password;
    }

    public User(Long id, User user){
        this.userId = id;
        this.email = user.email;
        this.password = user.password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getUserId() {
        return userId;
    }
    
}   
