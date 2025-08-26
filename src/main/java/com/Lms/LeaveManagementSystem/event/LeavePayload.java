package com.Lms.LeaveManagementSystem.event;

import com.Lms.LeaveManagementSystem.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class LeavePayload {
      private Long id;
      private Long userId;
      private String userName;
      private String userEmail;
      private LocalDate fromDate;
      private LocalDate toDate;
      private LeaveStatus status;
      private String actionedByName;
      private LocalDateTime actionedAt;

}
