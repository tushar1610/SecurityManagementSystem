package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.GuardUser;
import com.example.SecurityManagementSystem.exception.GuardUserNotCreatedException;
import com.example.SecurityManagementSystem.exception.GuardUserNotFoundException;
import com.example.SecurityManagementSystem.exception.UserNotAuthorizedException;
import com.example.SecurityManagementSystem.service.GuardUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guard/user")
public class GuardUserController {

    @Autowired
    private GuardUserService guardUserService;

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() and !hasAuthority('ROLE_GUARD_USER')")
    @GetMapping("/get/all")
    public ResponseEntity<List<GuardUser>> getAllGuardUsers() throws UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        List<GuardUser> guardUsers = guardUserService.getAllGuardUsers();
        return ResponseEntity.ok(guardUsers);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @GetMapping("/get/{userId}")
    public ResponseEntity<GuardUser> getGuardUserByUserId(@PathVariable Long userId) throws GuardUserNotFoundException, UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        GuardUser guardUser = guardUserService.getGuardUserByUserId(userId);
        return ResponseEntity.ok(guardUser);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PostMapping("/add")
    public ResponseEntity<GuardUser> addGuardUser(@RequestBody @Valid GuardUser guardUser) throws GuardUserNotCreatedException {
        GuardUser guardUser1 = guardUserService.addGuardUser(guardUser);
        if (guardUser1 == null) {
            throw new GuardUserNotCreatedException("Guard member cannot be created. Try again later.");
        }
        return ResponseEntity.ok(guardUser1);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @PutMapping("/update/{userId}")
    public ResponseEntity<GuardUser> updateGuardUserByUserId(@PathVariable Long userId, @RequestBody GuardUser guardUser) throws GuardUserNotFoundException, UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        GuardUser guardUser1 = guardUserService.updateGuardUserByUserId(userId, guardUser);
        return ResponseEntity.ok(guardUser1);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteGuardUserByUserId(@PathVariable Long userId) throws GuardUserNotFoundException, UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        guardUserService.deleteGuardUserByUserId(userId);
        if (guardUserService.getGuardUserByUserId(userId) == null) {
            return ResponseEntity.ok("Guard User deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Could not delete guard user.");
    }

}
