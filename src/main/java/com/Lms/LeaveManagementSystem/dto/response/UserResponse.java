package com.Lms.LeaveManagementSystem.dto.response;

import com.Lms.LeaveManagementSystem.dto.request.DepartmentDto;
import com.Lms.LeaveManagementSystem.dto.request.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

      private Long id;
      private String name;
      private String email;
      private RoleDto role;
      private Integer totalLeaves;
      private Integer leavesTaken;
      private DepartmentDto department;

}
