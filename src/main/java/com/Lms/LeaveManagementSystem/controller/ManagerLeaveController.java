package com.Lms.LeaveManagementSystem.controller;

import com.Lms.LeaveManagementSystem.dto.request.LeaveUpdateDto;
import com.Lms.LeaveManagementSystem.dto.response.LeaveResponseDto;
import com.Lms.LeaveManagementSystem.service.impl.ManagerLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/manager/leave")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerLeaveController {

      @Autowired
      private ManagerLeaveService managerLeaveService;

      // GET /api/manager/leave → list all pending requests
      @GetMapping
      public List<LeaveResponseDto> getAllPendingRequests(Principal principal) {
            return managerLeaveService.getAllPendingRequests(principal.getName());
      }

      // PUT /api/manager/leave/{id}/approve or reject
      @PutMapping("/{id}")
      public LeaveResponseDto updateLeaveStatus(@PathVariable Long id,
                                                @RequestBody LeaveUpdateDto dto,
                                                Principal principal) {
            System.out.println(principal.getName());
            return managerLeaveService.updateLeaveStatus(id, dto, principal.getName());
      }
}
