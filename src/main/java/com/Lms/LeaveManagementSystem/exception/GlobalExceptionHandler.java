package com.Lms.LeaveManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


      @ExceptionHandler({UserNotFoundException.class, LeaveNotFoundException.class}) //can do grouping if required using {}
      public ResponseEntity<?> userNotFound(UserNotFoundException ex){
            Map<String, Object> error = new HashMap<>();
            error.put("timestamp", LocalDateTime.now());
            error.put("error", "Not Found");
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

      }


      @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<?> handleMethodArguments(MethodArgumentNotValidException ex){
            Map<String, Object> error = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(e-> error.put(e.getField(), e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
      }

      @ExceptionHandler(Exception.class)
      public ResponseEntity<?> handleGeneric(Exception ex){
            Map<String, Object> error =  new HashMap<>();
            error.put("timestamp", LocalDateTime.now());
            error.put("error", "Internal Server Error");
            error.put("message", ex.getMessage());

            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
      }


}
