package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocietyUserRepository extends JpaRepository<SocietyUser, Long> {
    Optional<SocietyUser> findByUserUserId(Long userId);

    SocietyUser findByFlatNo(String flatNo);

    SocietyUser findByUserEmail(String email);
}
