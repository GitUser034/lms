package com.Lms.LeaveManagementSystem.repository;


import com.Lms.LeaveManagementSystem.entity.Department;
import com.Lms.LeaveManagementSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

      Optional<User> findByEmail(String email);
      Optional<User> findByName(String name);
      boolean existsByEmail(String email);
      User findByDepartmentAndRole_RoleName(Department department, String roleName);

}

