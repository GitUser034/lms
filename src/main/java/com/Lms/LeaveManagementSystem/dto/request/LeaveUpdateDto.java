package com.Lms.LeaveManagementSystem.dto.request;

import com.Lms.LeaveManagementSystem.enums.LeaveStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LeaveUpdateDto {
      @NotNull
      private LeaveStatus status;
}

