package com.example.SecurityManagementSystem.service;

import com.example.SecurityManagementSystem.entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface NotificationService {
    Notification addNotification(Notification notification);

    List<Notification> getAllNotifications();

    Optional<Notification> getNotificationById(Long notificationId, String flatNo);
}
