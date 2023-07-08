package com.example.SecurityManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long userId;
    @NotNull
    @Size(max = 100, min = 3, message = "Length must be greater than 3 and less than 100")
    @Column(unique = true)
    private String userName;
    private Integer age;
    private String contactNo;
    @Column(unique = true)
    @Email
    private String email;
    private String password;
    private String gender;
    private String role;
}
