package com.Lms.LeaveManagementSystem.dto.response;


import com.Lms.LeaveManagementSystem.enums.LeaveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveResponseDto {

      private Long id;

      private LocalDate fromDate;
      private LocalDate toDate;

      private LeaveStatus status;

      private String reason;

      private LocalDateTime appliedAt;
      private LocalDateTime actionedAt;

}

