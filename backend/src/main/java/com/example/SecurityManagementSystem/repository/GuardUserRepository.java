package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.GuardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardUserRepository extends JpaRepository<GuardUser, Long> {

    Optional<GuardUser> findByUserUserId(Long userId);

}
