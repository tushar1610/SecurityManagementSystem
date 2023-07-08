package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GuardUserRepositoryTest {

    @Autowired
    private GuardUserRepository guardUserRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp(){
        User user = User.builder()
                .userId(4L)
                .userName("Tushar Bhalothia")
                .email("tushar.bhalothia@gmail.com")
                .age(22)
                .password("password")
                .contactNo("9337403997")
                .gender("male")
                .build();

        GuardUser guardUser = GuardUser.builder()
                .gUserId(3L)
                .address("address")
                .user(user)
                .shiftTime("08:00am to 08:00pm")
                .build();

        entityManager.persist(guardUser);
    }

    @Test
    public void testGetGuardUserById(){

        GuardUser guardUser = guardUserRepository.findById(3L).get();
        assertEquals(guardUser.getGUserId(), 3L);
    }



}