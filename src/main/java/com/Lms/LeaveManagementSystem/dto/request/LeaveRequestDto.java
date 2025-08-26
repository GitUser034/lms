package com.Lms.LeaveManagementSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {

      @NotNull
      private LocalDate fromDate;

      @NotNull
      private LocalDate toDate;

      @NotBlank
      private String reason;
}

