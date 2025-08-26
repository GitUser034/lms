package com.Lms.LeaveManagementSystem.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;

      private String name;

//      @OneToOne
//      @JoinColumn(name = "manager_id")
//      private User manager;

      @OneToMany(mappedBy = "department")
      @ToString.Exclude
      private List<User> employees = new ArrayList<>();
}

