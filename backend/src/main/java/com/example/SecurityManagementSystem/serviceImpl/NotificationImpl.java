package com.example.SecurityManagementSystem.serviceImpl;

import com.example.SecurityManagementSystem.entity.Notification;
import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.repository.NotificationRepository;
import com.example.SecurityManagementSystem.repository.SocietyUserRepository;
import com.example.SecurityManagementSystem.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SocietyUserRepository societyUserRepository;

    @Override
    public Notification addNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        SocietyUser societyUser = societyUserRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return notificationRepository.findAllByFlatNo(societyUser.getFlatNo());
    }

    @Override
    public Optional<Notification> getNotificationById(Long notificationId, String flatNo) {
        return notificationRepository.findByNotificationIdAndFlatNo(notificationId, flatNo);
    }
}
