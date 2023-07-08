package com.example.SecurityManagementSystem.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.exception.NoSuchUserException;
import com.example.SecurityManagementSystem.repository.UserRepository;
import com.example.SecurityManagementSystem.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

	@Override
	public User getDetails(String email) throws NoSuchUserException {
		User user = userRepository.findByEmail(email);
        if(user == null){
            throw new NoSuchUserException("No user with this email");
        }
        return user;
	}
    
}
