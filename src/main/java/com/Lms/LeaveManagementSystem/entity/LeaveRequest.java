package com.Lms.LeaveManagementSystem.entity;


import com.Lms.LeaveManagementSystem.enums.LeaveStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long Id;

      @ManyToOne
      @JoinColumn(name = "user_id", nullable = false)
      private User user;

      private LocalDate fromDate;

      private LocalDate toDate;

      //private String type; will implement later

      //@Builder.Default
      @Enumerated(EnumType.STRING)
      private LeaveStatus status = LeaveStatus.PENDING;

      private String reason;

      //@Builder.Default
      private LocalDateTime appliedAt = LocalDateTime.now();

      private LocalDateTime actionedAt;

      @ManyToOne
      @JoinColumn(name = "actioned_by")
      private User actionedBy;




}

