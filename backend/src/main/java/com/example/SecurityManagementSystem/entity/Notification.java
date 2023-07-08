package com.example.SecurityManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    @NotNull
    private String message;
    private LocalDateTime timestamp;
    @NotNull
    private String flatNo;
    @OneToOne
    @JoinColumn(name = "visitorId" , referencedColumnName = "visitorId")
    private Visitor visitor;
}
