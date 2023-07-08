package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SocietyUserServiceTest {

    @Autowired
    private SocietyUserService societyUserService;

    @Mock
    private SocietyUserRepository societyUserRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetUserById() throws SocietyUserNotFoundException {
        User user = User.builder()
                .userId(1L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        SocietyUser societyUser = SocietyUser.builder()
                .sUserId(1L)
                .user(user)
                .flatNo("A101")
                .isAdmin(false)
                .ownerName("Tushar Bhalothia")
                .build();

        Mockito.when(societyUserRepository.findById(1L)).thenReturn(Optional.of(societyUser));

        SocietyUser result = societyUserService.getSocietyUserByUserId(1L);

        assertEquals(societyUser, result);
    }

    @Test
    public void testSaveUser(){
        User user = User.builder()
                .userId(1L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        SocietyUser societyUser = SocietyUser.builder()
                .sUserId(1L)
                .user(user)
                .flatNo("A101")
                .isAdmin(false)
                .ownerName("Tushar Bhalothia")
                .build();

        Mockito.when(societyUserRepository.save(societyUser)).thenReturn(societyUser);

        SocietyUser result = societyUserService.addSocietyUser(societyUser);

        assertEquals(societyUser, result);

    }

    @Test
    public void testUpdateUser() throws SocietyUserNotFoundException {
        User user = User.builder()
                .userId(1L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        SocietyUser societyUser = SocietyUser.builder()
                .sUserId(1L)
                .user(user)
                .flatNo("A101")
                .isAdmin(false)
                .ownerName("Tushar Bhalothia")
                .build();

        Mockito.when(societyUserRepository.findById(1L)).thenReturn(Optional.of(societyUser));
        Mockito.when(societyUserRepository.save(societyUser)).thenReturn(societyUser);

        User updatedUser = User.builder()
                .userId(1L)
                .userName("Tushar Bhalothia 2")
                .email("email@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        SocietyUser updatedSocietyUser = SocietyUser.builder()
                .sUserId(1L)
                .user(updatedUser)
                .flatNo("A101")
                .isAdmin(false)
                .ownerName("Tushar Bhalothia")
                .build();

        SocietyUser result = societyUserService.updateSocietyUserByUserId(1L, updatedSocietyUser);

        assertEquals(updatedSocietyUser, result);
    }

    @Test
    public void testDeleteUser() throws SocietyUserNotFoundException {
        User user = User.builder()
                .userId(1L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        SocietyUser societyUser = SocietyUser.builder()
                .sUserId(1L)
                .user(user)
                .flatNo("A101")
                .isAdmin(false)
                .ownerName("Tushar Bhalothia")
                .build();

        Mockito.when(societyUserRepository.findById(1L)).thenReturn(Optional.of(societyUser));

        societyUserService.deleteSocietyUser(1L);
    }

}