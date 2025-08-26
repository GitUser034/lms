package com.Lms.LeaveManagementSystem.service;

import com.Lms.LeaveManagementSystem.dto.request.CreateUserRequest;
import com.Lms.LeaveManagementSystem.dto.response.UserResponse;

public interface UserService {
      UserResponse create(CreateUserRequest request);
      UserResponse getById(Long id);


            UserResponse update(Long id, CreateUserRequest request);
      void delete(Long id);
}
