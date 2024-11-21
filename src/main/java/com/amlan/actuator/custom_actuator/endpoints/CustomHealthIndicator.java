package com.amlan.actuator.custom_actuator.endpoints;

import java.time.ZonedDateTime;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

public class CustomHealthIndicator implements HealthIndicator{
    private final String messageKey = "ApplicationReady";
    private static boolean APPLICATION_READY = false;
    private String startTime;
    private boolean started = false;
    
    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event){
        if(event instanceof ApplicationReadyEvent){
            this.startTime=ZonedDateTime.now().toString();
            this.started=true;
            CustomHealthIndicator.APPLICATION_READY=true;
        }
        
    }

    @Override
    public Health health() {
        try{
            if(!CustomHealthIndicator.APPLICATION_READY){
                return Health.outOfService().withDetail(messageKey, "Still Starting").build();
            }
        }catch(Exception e){
            throw new UnsupportedOperationException("Unimplemented method 'health'"+e.getMessage());
        }
        return Health.up().withDetail(messageKey, startTime).build();
        
    }

}
