package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.Notification;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByFlatNo(String flatNo);

    Optional<Notification> findByNotificationIdAndFlatNo(Long notificationId, String flatNo);

}
