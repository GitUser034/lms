package com.Lms.LeaveManagementSystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerDto {
      private Long id;
      private String name;
      private String email;
}
