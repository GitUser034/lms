package com.Lms.LeaveManagementSystem.controller;

import com.Lms.LeaveManagementSystem.dto.request.AuthRequest;
import com.Lms.LeaveManagementSystem.dto.request.AuthResponse;
import com.Lms.LeaveManagementSystem.dto.request.CreateUserRequest;
import com.Lms.LeaveManagementSystem.dto.response.UserResponse;
import com.Lms.LeaveManagementSystem.service.UserService;
import com.Lms.LeaveManagementSystem.utility.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

      @Autowired
      private PasswordEncoder passwordEncoder;

      @Autowired
      private AuthenticationManager authenticationManager;

      @Autowired
      private JwtUtil jwtUtil;


      @GetMapping("/{id}")
      public ResponseEntity<UserResponse> get(@PathVariable Long id) {
            return ResponseEntity.ok(userService.getById(id));
      }

      @PostMapping
      public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
            UserResponse response = userService.create(request);
            return ResponseEntity.status(201).body(response);
      }

      @PutMapping("/{id}")
      public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody CreateUserRequest request) {
            return ResponseEntity.ok(userService.update(id, request));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> delete(@PathVariable Long id) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
      }


      @PostMapping("/generate-token")
      public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String accessToken = jwtUtil.generateAccessToken(userDetails);
            String refreshToken = jwtUtil.generateRefreshToken(userDetails);

            Cookie refreshCookie = new Cookie("refreshToken", refreshToken);
            refreshCookie.setHttpOnly(true);
            refreshCookie.setPath("/");
            refreshCookie.setMaxAge(7 * 24 * 60 * 60);
            response.addCookie(refreshCookie);

            return ResponseEntity.ok(new AuthResponse(accessToken));
      }
}
