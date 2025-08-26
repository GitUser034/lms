package com.Lms.LeaveManagementSystem.repository;


import com.Lms.LeaveManagementSystem.entity.Department;
import com.Lms.LeaveManagementSystem.entity.LeaveRequest;
import com.Lms.LeaveManagementSystem.entity.User;
import com.Lms.LeaveManagementSystem.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{

      //for user's purpose
      List<LeaveRequest> findByUser(User user);

      //for manager purpose
      List<LeaveRequest> findByStatus(LeaveStatus status);





      //below concept is correct but LeaveRequest doesn't have department column so it will not work

      //List<LeaveRequest> findByStatusAndDepartment(LeaveStatus status, Department department);
      // we could have also used
      //findByStatusAndDepartmentId(LeaveStatus status, Long departmentId)
      //here when we say DepartmentId then it first of all there are two terms Department and Id seperated by camel case
      //or we can do it like Department_Id it means go to Department table and search for proerty Id.


      //let's try another way
      @Query("SELECT lr FROM LeaveRequest lr " +
              "WHERE lr.status = :status " +
              "AND lr.user.department.id = :deptId")
      List<LeaveRequest> findPendingByDepartmentId(@Param("status") LeaveStatus status,
                                                   @Param("deptId") Long deptId);


      //or we could have used department object directly
}