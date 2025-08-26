package com.Lms.LeaveManagementSystem.controller;

import com.Lms.LeaveManagementSystem.dto.request.LeaveRequestDto;
import com.Lms.LeaveManagementSystem.dto.response.LeaveResponseDto;
import com.Lms.LeaveManagementSystem.service.impl.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

      @Autowired
      private LeaveService leaveService;

      // POST /api/leave → apply for leave
      @PostMapping
      public ResponseEntity<LeaveResponseDto> applyLeave(
              @RequestBody LeaveRequestDto dto,
              Authentication authentication) {

            String email = authentication.getName();
            return ResponseEntity.ok(leaveService.applyLeave(email, dto));
      }

      // GET /api/leave/my → get all my leave requests
      @GetMapping("/my")
      public ResponseEntity<List<LeaveResponseDto>> getMyLeaves(Authentication authentication) {
            String email = authentication.getName();
            return ResponseEntity.ok(leaveService.getMyLeaves(email));
      }

      // GET /api/leave/{id} → get status of a specific leave
      @GetMapping("/{id}")
      public ResponseEntity<LeaveResponseDto> getLeaveById(
              @PathVariable Long id,
              Authentication authentication) {

            String email = authentication.getName();
            return ResponseEntity.ok(leaveService.getLeaveById(email, id));
      }
}
