package com.aashish.todo_app_basic.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password) {
        boolean isValidUser = username.equalsIgnoreCase("aash");
        boolean isValidPass = password.equals("bath");
        return isValidUser && isValidPass;
    }
}
