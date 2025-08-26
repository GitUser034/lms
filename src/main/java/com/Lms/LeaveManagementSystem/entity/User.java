package com.Lms.LeaveManagementSystem.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long Id;

      private String name;

      @Column(unique = true, nullable = false)
      private String email;

      private String password;

      @ManyToOne(fetch = FetchType.EAGER)  //for spring security only i did this
      @JoinColumn(name = "role_id")
      private Role role;

      private Integer totalLeaves = 20; //for handling null i used INT

      private Integer leavesTaken = 0;

      @ManyToOne(fetch = FetchType.EAGER)
      @JoinColumn(name = "department_id")
      @ToString.Exclude
      private Department department;


      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.singleton(new SimpleGrantedAuthority(role.getRoleName()));
      }

      @Override
      public String getUsername() {
            return this.email;
      }

      @Override
      public String getPassword() {
            return this.password;
      }

      @Override
      public boolean isAccountNonExpired() {
            return true;
      }

      @Override
      public boolean isAccountNonLocked() {
            return true;
      }

      @Override
      public boolean isCredentialsNonExpired() {
            return true;
      }

      @Override
      public boolean isEnabled() {
            return true;
      }
}

