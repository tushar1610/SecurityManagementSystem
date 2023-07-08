package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SocietyUserService {
    SocietyUser getSocietyUserByUserId(Long userId) throws SocietyUserNotFoundException;

    SocietyUser addSocietyUser(SocietyUser societyUser);

    SocietyUser updateSocietyUserByUserId(Long userId, SocietyUser societyUser) throws SocietyUserNotFoundException;

    void deleteSocietyUser(Long userId) throws SocietyUserNotFoundException;

    List<SocietyUser> getAllSocietyUsers();
}
