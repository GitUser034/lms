package com.Lms.LeaveManagementSystem.dto.request;

import com.Lms.LeaveManagementSystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

      private Long id;
      private String name;
      //private ManagerDto manager;


      //private User manager;//so here again cyclic dependecuy got created because again we are fetching User
      //from Department so what i will do is instead of printing this full User i will only print required info like
      //id, name, email because if we will map full User then User contains department so again  cyclic dependency will occur

}
