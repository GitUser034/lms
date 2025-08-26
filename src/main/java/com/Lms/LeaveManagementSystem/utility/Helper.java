package com.Lms.LeaveManagementSystem.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import com.Lms.LeaveManagementSystem.event.LeaveEvent;


@Component
public class Helper {

      private ModelMapper mapper;
      private LeaveEventProducer leaveEventProducer;

      @Autowired
      public Helper(ModelMapper mapper, LeaveEventProducer leaveEventProducer) {
            this.mapper = mapper;
            this.leaveEventProducer = leaveEventProducer;
      }



      //to provide hook , we can do it any phase of transaction
      public void publishAfterCommit(LeaveEvent event) {
            if (TransactionSynchronizationManager.isSynchronizationActive()) {
                  TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                              boolean ok = leaveEventProducer.publish(event);
                              if (!ok) {
                                    System.err.println("publishAfterCommit: sink.emit failed for event: " + event.getEventId());
                              }
                        }
                  });
            } else {
                  leaveEventProducer.publish(event);
            }
      }
}
