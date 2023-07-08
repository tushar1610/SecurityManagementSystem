package com.example.SecurityManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long visitorId;
    @NotNull
    @Size(max = 100, min = 3, message = "Length must be greater than 3 and less than 100")
    private String visitorName;
    private Integer age;
    private String contactNo;
    private String gender;
    @NotNull
    private String purpose;
    private LocalDate date;
    private LocalTime inTime;
    private LocalTime outTime;
    private LocalTime visitDuration;
    @NotNull
    private String guardName;
    private Boolean isApproved;
    private String approverName;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = SocietyUser.class)
    @JoinColumn(
            name = "flatNo",
            referencedColumnName = "flatNo"
    )
    private SocietyUser societyUser;
}
