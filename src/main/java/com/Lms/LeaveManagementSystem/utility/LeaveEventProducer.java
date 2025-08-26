package com.Lms.LeaveManagementSystem.utility;

import com.Lms.LeaveManagementSystem.event.LeaveEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;


import java.util.function.Supplier;

@Configuration
public class LeaveEventProducer {

      private Sinks.Many<LeaveEvent> sink = Sinks.many().multicast().onBackpressureBuffer();

      @Bean
      public Supplier<Flux<LeaveEvent>> leaveEvent(){
            return sink::asFlux;
      }

      public boolean publish(LeaveEvent event) {
            Sinks.EmitResult result = sink.tryEmitNext(event);
            if (result.isFailure()) {
                  System.err.println("Failed to emit LeaveEvent: " + result);
                  return false;
            }
            return true;
      }
}
