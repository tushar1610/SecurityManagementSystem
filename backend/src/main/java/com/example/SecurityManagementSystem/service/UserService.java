package com.example.SecurityManagementSystem.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.exception.NoSuchUserException;

@Service
public interface UserService {

	User getDetails(String email) throws NoSuchUserException;
    
}
