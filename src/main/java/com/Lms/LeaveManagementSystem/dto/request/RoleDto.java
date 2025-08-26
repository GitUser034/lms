package com.Lms.LeaveManagementSystem.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
      private Long roleId;
      private String roleName;
}
