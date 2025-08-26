package com.Lms.LeaveManagementSystem.config;


import com.Lms.LeaveManagementSystem.service.impl.CustomUserDetailsService;
import com.Lms.LeaveManagementSystem.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RefreshTokenFilter extends OncePerRequestFilter {

      @Autowired
      private JwtUtil jwtUtil;
      @Autowired private CustomUserDetailsService userDetailsService;

      @Override
      protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                      FilterChain filterChain) throws ServletException, IOException, java.io.IOException {

            if (request.getRequestURI().equals("/api/users/refresh-token")) {
                  Cookie[] cookies = request.getCookies();
                  if (cookies != null) {
                        for (Cookie cookie : cookies) {
                              if ("refreshToken".equals(cookie.getName())) {
                                    String refreshToken = cookie.getValue();
                                    try {
                                          String username = jwtUtil.extractUsername(refreshToken);
                                          UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                                          if (jwtUtil.validateToken(refreshToken, userDetails)) {
                                                String newAccessToken = jwtUtil.generateAccessToken(userDetails);

                                                response.setContentType("application/json");
                                                response.getWriter().write("{\"token\": \"" + newAccessToken + "\"}");
                                                return; // Stop here, don’t go to controller
                                          }

                                    } catch (Exception e) {
                                          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                          return;
                                    }
                              }
                        }
                  }

                  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                  return;
            }

            filterChain.doFilter(request, response);
      }
}

