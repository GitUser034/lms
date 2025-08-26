//package com.Lms.LeaveManagementSystem.config;
//
//
//import org.modelmapper.ModelMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class ProjectConfig {
//
//      @Bean
//      public ModelMapper mapper(){
//            return new ModelMapper();
//      }
//
//      @Bean
//      public PasswordEncoder getEncoder(){
//            return new BCryptPasswordEncoder();
//      }
//
//
//      @Bean
//      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http.csrf().disable()
//                    .authorizeHttpRequests()
//                    .anyRequest().permitAll()
//                    .and()
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//            return http.build();
//      }
//
//}
