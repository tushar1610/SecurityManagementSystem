package com.example.SecurityManagementSystem.controller;

import com.example.SecurityManagementSystem.entity.SocietyUser;
import com.example.SecurityManagementSystem.entity.Visitor;
import com.example.SecurityManagementSystem.exception.SocietyUserNotFoundException;
import com.example.SecurityManagementSystem.exception.UserNotAuthorizedException;
import com.example.SecurityManagementSystem.exception.VisitorNotCreatedException;
import com.example.SecurityManagementSystem.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    private boolean verifyAuthentication() throws UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            throw new UserNotAuthorizedException("Access denied. Unauthorized access.");
        }
        System.out.println(authentication);
        return true;
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('GUARD_USER') or hasRole('ADMIN')")
    @GetMapping("/get/all")
    public ResponseEntity<List<Visitor>> getAllVisitors() throws UserNotAuthorizedException {
        List<Visitor> visitors = new ArrayList<>();
        if (verifyAuthentication()) {
            visitors = visitorService.getAllVisitors();
        }
        System.out.println(visitors);
        return ResponseEntity.ok(visitors);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/all/age/{age}")
    public ResponseEntity<List<Visitor>> getAllVisitorsByAge(@PathVariable Integer age) throws UserNotAuthorizedException {
        List<Visitor> visitors = new ArrayList<>();
        if (verifyAuthentication()) {
            visitors = visitorService.getAllVisitorsByAge(age);
        }
        return ResponseEntity.ok(visitors);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/get/all/gender/{gender}")
    public ResponseEntity<List<Visitor>> getAllVisitorsByGender(@PathVariable String gender) throws UserNotAuthorizedException {
        List<Visitor> visitors = new ArrayList<>();
        if (verifyAuthentication()) {
            visitors = visitorService.getAllVisitorsByGender(gender);
        }
        return ResponseEntity.ok(visitors);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('SOCIETY_USER') or hasRole('ADMIN')")
    @GetMapping("/get/all/flatNo")
    public ResponseEntity<List<Visitor>> getAllVisitorsByFlatNo() throws UserNotAuthorizedException, SocietyUserNotFoundException {
        List<Visitor> visitors = new ArrayList<>();
        if (verifyAuthentication()) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            visitors = visitorService.getAllVisitorsByFlatNo(email);
        }
        return ResponseEntity.ok(visitors);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('GUARD_USER') or hasRole('ADMIN')")
    @GetMapping("/get/all/date")
    public ResponseEntity<List<Visitor>> getAllVisitorsByDate(@RequestParam("date") LocalDate date) throws UserNotAuthorizedException {
        List<Visitor> visitors = new ArrayList<>();
        if (verifyAuthentication()) {
            visitors = visitorService.getAllVisitorsByDate(date);
        }
        return ResponseEntity.ok(visitors);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    @PreAuthorize("hasRole('SOCIETY_USER') or hasRole('ADMIN')")
    @GetMapping("/get/all/date/and/flatNo")
    public ResponseEntity<List<Visitor>> getAllVisitorsByDateAndFlatNo(@RequestParam("date") LocalDate date) throws UserNotAuthorizedException, SocietyUserNotFoundException {
        List<Visitor> visitors = new ArrayList<>();
        if (verifyAuthentication()) {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            visitors = visitorService.getAllVisitorsByDateAndFlatNo(date, email);
        }
        return ResponseEntity.ok(visitors);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public ResponseEntity<Visitor> addVisitor(@RequestBody @Valid Visitor visitor) throws VisitorNotCreatedException {
        Visitor visitor1 = visitorService.addVisitor(visitor);
        if (visitor1 == null) {
            throw new VisitorNotCreatedException("Visitor cannot be created. Try again later.");
        }
        return ResponseEntity.ok(visitor1);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_GUARD_USER')")
    @PutMapping("/updateVisitorOutTime")
    public ResponseEntity<Visitor> updateVisitorOutTime(@RequestBody Visitor visitor) throws Exception {
        Visitor visitor1 = new Visitor();
        if (verifyAuthentication()) {
            visitor1 = visitorService.updateVisitorOutTime(visitor);
        }
        return ResponseEntity.ok(visitor1);
    }

    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
    @PreAuthorize("isAuthenticated() and !hasAuthority('ROLE_GUARD_USER')")
    @PutMapping("/update/approval/status/{visitorId}")
    public ResponseEntity<Boolean> updateVisitorApprovalStatus(@PathVariable Long visitorId, @RequestBody Visitor visitor) throws UserNotAuthorizedException {
        Boolean isApproved = false;
        if (verifyAuthentication()) {
            isApproved = visitorService.updateVisitorApprovalStatus(visitorId, visitor);
        }
        return ResponseEntity.ok(isApproved);
    }

}
