package com.Lms.LeaveManagementSystem.service.impl;

import com.Lms.LeaveManagementSystem.dto.request.LeaveRequestDto;
import com.Lms.LeaveManagementSystem.dto.response.LeaveResponseDto;
import com.Lms.LeaveManagementSystem.entity.LeaveRequest;
import com.Lms.LeaveManagementSystem.entity.User;
import com.Lms.LeaveManagementSystem.enums.LeaveStatus;
import com.Lms.LeaveManagementSystem.event.LeaveEvent;
import com.Lms.LeaveManagementSystem.event.LeavePayload;
import com.Lms.LeaveManagementSystem.repository.LeaveRequestRepository;
import com.Lms.LeaveManagementSystem.repository.UserRepository;

import com.Lms.LeaveManagementSystem.utility.Helper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveService {

      @Autowired
      private LeaveRequestRepository leaveRequestRepository;
      @Autowired
      private UserRepository userRepository;


      @Autowired
      private ModelMapper mapper;


      // Apply for leave
   
      public LeaveResponseDto applyLeave(String email, LeaveRequestDto dto) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            //checking the leave balance first
            int leaveRequestCount = (int)ChronoUnit.DAYS.between(dto.getFromDate(), dto.getToDate()) + 1;
            if(user.getTotalLeaves()-user.getLeavesTaken() < leaveRequestCount){
                  throw new RuntimeException("Insufficient leave balance");
            }

            LeaveRequest map = mapper.map(dto, LeaveRequest.class);
            map.setUser(user);
            map.setStatus(LeaveStatus.PENDING);
            LeaveRequest save = leaveRequestRepository.save(map);
            return mapper.map(save, LeaveResponseDto.class);

      }

      // Get all leaves for logged-in user
      public List<LeaveResponseDto> getMyLeaves(String email) {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return leaveRequestRepository.findByUser(user)
                    .stream()
                    .map(lr -> new ModelMapper().map(lr, LeaveResponseDto.class)
                    )
                    .collect(Collectors.toList());
      }

      // Get leave by ID
      public LeaveResponseDto getLeaveById(String email, Long leaveId) {

            LeaveRequest lr = leaveRequestRepository.findById(leaveId)
                    .orElseThrow(() -> new RuntimeException("Leave not found"));

            if (!lr.getUser().getEmail().equals(email)) {
                  throw new RuntimeException("Access denied");
            }

            return mapper.map(lr, LeaveResponseDto.class);
      }


}


