package com.Lms.LeaveManagementSystem.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveEvent {

      private UUID eventId=UUID.randomUUID();
      private Date eventDate=new Date();
      private String eventType;
      private LeavePayload payload;

      public LeaveEvent(String eventType, LeavePayload payload) {
            this.eventType = eventType;
            this.payload = payload;
      }

      public LeaveEvent(LeavePayload payload) {
            this.payload = payload;
      }
}
