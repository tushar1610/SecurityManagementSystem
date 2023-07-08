package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.User;
import com.example.SecurityManagementSystem.exception.GuardUserNotFoundException;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.repository.GuardUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuardUserServiceTest {

    @Autowired
    private GuardUserService guardUserService;

    @MockBean
    private GuardUserRepository guardUserRepository;

    @BeforeEach
    void setUp(){

        User user = User.builder()
                .userId(2L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        GuardUser guardUser = GuardUser.builder()
                .address("Address")
                .shiftTime("shiftTime")
                .user(user)
                .build();

        Mockito.when(guardUserRepository.findByUserUserId(1L)).thenReturn(Optional.ofNullable(guardUser));

    }

    @Test
    void getGuardUserByUserId() {
        Long userId = 1L;
        GuardUser guardUser = guardUserRepository.findByUserUserId(userId).get();
        assertEquals(userId, guardUser.getUser().getUserId());
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

        GuardUser guardUser = GuardUser.builder()
                .gUserId(1L)
                .address("address")
                .user(user)
                .shiftTime("08:00am to 08:00pm")
                .build();

        Mockito.when(guardUserRepository.save(guardUser)).thenReturn(guardUser);

        GuardUser result = guardUserService.addGuardUser(guardUser);

        assertEquals(guardUser, result);

    }

    @Test
    public void testUpdateUser() throws GuardUserNotFoundException {
        User user = User.builder()
                .userId(2L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        GuardUser guardUser = GuardUser.builder()
                .gUserId(1L)
                .address("address")
                .user(user)
                .shiftTime("08:00am to 08:00pm")
                .build();

        Mockito.when(guardUserRepository.findById(1L)).thenReturn(Optional.of(guardUser));
        Mockito.when(guardUserRepository.save(guardUser)).thenReturn(guardUser);

        User updatedUser = User.builder()
                .userId(2L)
                .userName("Tushar Bhalothia")
                .email("email@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        GuardUser updatedGuardUser = GuardUser.builder()
                .gUserId(1L)
                .address("address")
                .user(updatedUser)
                .shiftTime("08:30am to 08:00pm")
                .build();

        GuardUser result = guardUserService.updateGuardUserByUserId(2L, updatedGuardUser);

        assertEquals(updatedGuardUser, result);
    }

    @Test
    public void testDeleteUser() throws GuardUserNotFoundException {
        User user = User.builder()
                .userId(2L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        GuardUser guardUser = GuardUser.builder()
                .gUserId(1L)
                .address("address")
                .user(user)
                .shiftTime("08:00am to 08:00pm")
                .build();

        Mockito.when(guardUserRepository.findById(1L)).thenReturn(Optional.of(guardUser));

        guardUserService.deleteGuardUserByUserId(1L);
    }

}