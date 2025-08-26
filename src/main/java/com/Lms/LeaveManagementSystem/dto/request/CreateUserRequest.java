package com.Lms.LeaveManagementSystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Builder
public class CreateUserRequest {

      @NotBlank
      private String name;

      @Email
      @NotBlank
      private String email;

      @NotBlank
      @Size(min = 6, message = "password must be at least 6 characters")
      private String password;

      @NotNull
      private RoleDto role;

      @Min(0)
      //@Builder.Default
      private Integer totalLeavesPerYear = 20;  //used Integer for  Validation logic and to represent null

      //@Builder.Default
      private Integer leavesTaken = 0;

      private DepartmentDto department;

}
