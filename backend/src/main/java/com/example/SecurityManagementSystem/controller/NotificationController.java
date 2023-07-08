package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Notification;
import com.example.SecurityManagementSystem.exception.NotificationNotFoundException;
import com.example.SecurityManagementSystem.exception.UserNotAuthorizedException;
import com.example.SecurityManagementSystem.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public Notification addNotification(@RequestBody @Valid Notification notification){
        return notificationService.addNotification(notification);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
    @GetMapping("/get/all")
    public ResponseEntity<List<Notification>> getAllNotifications() throws UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        List<Notification> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() and !hasAuthority('ROLE_GUARD_USER')")
    @GetMapping("/get/{notificationId}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long notificationId, @RequestParam("flatNo") String flatNo) throws NotificationNotFoundException, UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        Optional<Notification> notification = notificationService.getNotificationById(notificationId, flatNo);
        if (!notification.isPresent()){
            throw new NotificationNotFoundException("No such notification");
        }
        return ResponseEntity.ok(notification.get());
    }

}
