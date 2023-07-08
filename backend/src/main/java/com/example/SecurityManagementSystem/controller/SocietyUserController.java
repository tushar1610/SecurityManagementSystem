package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.Login;
import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.exception.SocietyUserNotCreatedException;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.exception.UserNotAuthorizedException;
import com.example.SecurityManagementSystem.service.SocietyUserService;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("society/user")
public class SocietyUserController {

    @Autowired
    private SocietyUserService societyUserService;

    public static final Logger logger = LoggerFactory.getLogger(SocietyUserController.class);

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("isAuthenticated() and !hasAuthority('ROLE_GUARD_USER')")
    @GetMapping("/get/{userId}")
    public SocietyUser getSocietyUserByUserId(@PathVariable Long userId) throws SocietyUserNotFoundException, UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        return societyUserService.getSocietyUserByUserId(userId);
    }

//    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_SOCIETY_USER')")
//    @GetMapping("/index")
//    public String index() {
//        return "index";
//    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/all")
    public ResponseEntity<List<SocietyUser>> getAllSocietyUsers() throws UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        List<SocietyUser> societyUsers = societyUserService.getAllSocietyUsers();
        return ResponseEntity.ok(societyUsers);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PostMapping("/add")
    public ResponseEntity<SocietyUser> addSocietyUser(@RequestBody @Valid SocietyUser societyUser) throws SocietyUserNotCreatedException {
        SocietyUser societyUser1 = societyUserService.addSocietyUser(societyUser);
        if (societyUser1 == null) {
            throw new SocietyUserNotCreatedException("Society member cannot be created. Try again later.");
        }
        return ResponseEntity.ok(societyUser1);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() and !hasAuthority('ROLE_GUARD_USER')")
    @PutMapping("/update/{userId}")
    public ResponseEntity<SocietyUser> updateSocietyUserByUserId(@PathVariable Long userId, @RequestBody @Valid SocietyUser societyUser) throws SocietyUserNotFoundException, UserNotAuthorizedException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }

        SocietyUser societyUser1 = societyUserService.updateSocietyUserByUserId(userId, societyUser);
        return ResponseEntity.ok(societyUser1);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteSocietyUser(@PathVariable Long userId) throws UserNotAuthorizedException, SocietyUserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        societyUserService.deleteSocietyUser(userId);
        return ResponseEntity.ok("Deletion successful");
    }

}
