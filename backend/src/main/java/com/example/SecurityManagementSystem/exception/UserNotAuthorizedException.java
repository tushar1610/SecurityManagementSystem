package com.example.SecurityManagementSystem.exception;

public class UserNotAuthorizedException extends Exception {

    public UserNotAuthorizedException(String message){
        super(message);
    }
    
}
