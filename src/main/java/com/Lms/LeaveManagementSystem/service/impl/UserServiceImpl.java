package com.Lms.LeaveManagementSystem.service.impl;

import com.Lms.LeaveManagementSystem.dto.request.CreateUserRequest;
import com.Lms.LeaveManagementSystem.dto.response.UserResponse;
import com.Lms.LeaveManagementSystem.entity.Department;
import com.Lms.LeaveManagementSystem.entity.Role;
import com.Lms.LeaveManagementSystem.entity.User;
import com.Lms.LeaveManagementSystem.exception.UserNotFoundException;
import com.Lms.LeaveManagementSystem.repository.DepartmentRepository;
import com.Lms.LeaveManagementSystem.repository.RoleRepository;
import com.Lms.LeaveManagementSystem.repository.UserRepository;
import com.Lms.LeaveManagementSystem.service.UserService;
import com.Lms.LeaveManagementSystem.utility.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

      @Autowired
      private UserRepository userRepository;

      @Autowired
      private PasswordEncoder passwordEncoder;

      @Autowired
      private RoleRepository roleRepository;

      @Autowired
      private DepartmentRepository departmentRepository;

      @Autowired
      private ModelMapper mapper;

      @Autowired
      private Helper helper;

      @Override
      public UserResponse create(CreateUserRequest request) {

            if(userRepository.existsByEmail(request.getEmail())){
                  throw new IllegalArgumentException("Email already exist");
            }
            User map = mapper.map(request, User.class);
            Role role = roleRepository.findById(request.getRole().getRoleId())
                    .orElseThrow(() -> new RuntimeException("Invalid Role ID: " + request.getRole().getRoleId()));
            Department department = departmentRepository.findById(request.getDepartment().getId())
                    .orElseThrow(() -> new RuntimeException("Invalid Department ID: " + request.getDepartment().getId()));
            map.setRole(role);
            map.setDepartment(department);
            map.setPassword(passwordEncoder.encode(request.getPassword()));
            User save = userRepository.save(map);
            return mapper.map(save, UserResponse.class);
      }

      @Override
      public UserResponse getById(Long id) {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
            return mapper.map(user, UserResponse.class);
      }


      @Override
      public UserResponse update(Long id, CreateUserRequest request) {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
            User map = mapper.map(request, User.class);
            User save = userRepository.save(map);
            return mapper.map(save, UserResponse.class);
      }

      @Override
      public void delete(Long id) {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
            userRepository.deleteById(id);
      }
}
