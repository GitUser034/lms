package com.Lms.LeaveManagementSystem.service.impl;

import com.Lms.LeaveManagementSystem.dto.request.LeaveUpdateDto;
import com.Lms.LeaveManagementSystem.dto.response.LeaveResponseDto;
import com.Lms.LeaveManagementSystem.entity.LeaveRequest;
import com.Lms.LeaveManagementSystem.entity.User;
import com.Lms.LeaveManagementSystem.enums.LeaveStatus;
import com.Lms.LeaveManagementSystem.event.LeaveEvent;
import com.Lms.LeaveManagementSystem.event.LeavePayload;
import com.Lms.LeaveManagementSystem.exception.LeaveNotFoundException;
import com.Lms.LeaveManagementSystem.exception.UserNotFoundException;
import com.Lms.LeaveManagementSystem.repository.LeaveRequestRepository;
import com.Lms.LeaveManagementSystem.repository.UserRepository;
import com.Lms.LeaveManagementSystem.utility.Helper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagerLeaveService {

      @Autowired
      private LeaveRequestRepository leaveRequestRepository;
      @Autowired
      private UserRepository userRepository;
      @Autowired
      private ModelMapper modelMapper;
  

      public List<LeaveResponseDto> getAllPendingRequests(String managerUsername) {

            User manager = userRepository.findByEmail(managerUsername)
                    .orElseThrow(() -> new UserNotFoundException("Manager not found"));

            Long departmentId = manager.getDepartment().getId();
            List<LeaveRequest> requests = leaveRequestRepository.findPendingByDepartmentId(LeaveStatus.PENDING, departmentId);
            return requests.stream()
                    .map(req -> modelMapper.map(req, LeaveResponseDto.class))
                    .collect(Collectors.toList());
      }
 
      public LeaveResponseDto updateLeaveStatus(Long leaveId, LeaveUpdateDto dto, String managerUsername) {
            LeaveRequest leave = leaveRequestRepository.findById(leaveId)
                    .orElseThrow(() -> new LeaveNotFoundException("Leave request not found"));

            User manager = userRepository.findByEmail(managerUsername)
                    .orElseThrow(() -> new UserNotFoundException("Manager not found"));

            if(leave.getStatus() != LeaveStatus.PENDING){
                  throw new RuntimeException("Only pending leaves can be approved");
            }

            User user = leave.getUser();
            int days = (int)ChronoUnit.DAYS.between(leave.getFromDate(), leave.getToDate())+1;
            user.setLeavesTaken(user.getLeavesTaken() + days);
            userRepository.save(user);

            leave.setStatus(dto.getStatus());
            leave.setActionedBy(manager);
            leave.setActionedAt(LocalDateTime.now());

            LeaveRequest updated = leaveRequestRepository.save(leave);

            
            return modelMapper.map(updated, LeaveResponseDto.class);
      }
}

