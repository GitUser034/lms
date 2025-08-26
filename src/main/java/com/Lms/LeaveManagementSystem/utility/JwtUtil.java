package com.Lms.LeaveManagementSystem.utility;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

      private final String SECRET = "45a7c73f1eea4b7e8bc24a5d2496f3d1d1b6c5de2b7f23f4ce5a7e3ab78d55fc";


      public String generateAccessToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, userDetails.getUsername(),5 * 60 * 1000);
      }

      public String generateRefreshToken(UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, userDetails.getUsername(), 7 * 24 * 60 * 60 * 1000);
      }

      private String createToken(Map<String, Object> claims, String subject, long expiryInMillis) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + expiryInMillis))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
      }

      public boolean validateToken(String token, UserDetails userDetails) {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
      }

      public String extractUsername(String token) {
            return extractClaims(token).getSubject();
      }

      private boolean isTokenExpired(String token) {
            return extractClaims(token).getExpiration().before(new Date());
      }

      private Claims extractClaims(String token) {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
      }
}

