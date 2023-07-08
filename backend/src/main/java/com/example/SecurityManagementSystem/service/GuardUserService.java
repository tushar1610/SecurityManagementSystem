package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.exception.GuardUserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GuardUserService {
    List<GuardUser> getAllGuardUsers();

    GuardUser getGuardUserByUserId(Long userId) throws GuardUserNotFoundException;

    GuardUser addGuardUser(GuardUser guardUser);

    GuardUser updateGuardUserByUserId(Long userId, GuardUser guardUser) throws GuardUserNotFoundException;

    void deleteGuardUserByUserId(Long userId) throws GuardUserNotFoundException;
}
