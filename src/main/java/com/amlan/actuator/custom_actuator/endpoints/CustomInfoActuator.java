package com.amlan.actuator.custom_actuator.endpoints;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import jakarta.annotation.PostConstruct;

import org.springframework.boot.actuate.info.InfoContributor;


public class CustomInfoActuator implements InfoContributor{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomInfoActuator.class);
    private static final Logger ROOTLOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    private final Environment environment;

    public CustomInfoActuator (final @Lazy Environment environment){
        this.environment=environment;
    }

    @PostConstruct
    private void init(){
        LOGGER.trace("Finished creating",this.getClass());
    }
    
    @Override
    public void contribute(Builder builder) {
        // TODO Auto-generated method stub
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        Map<String,Object> config = new HashMap<>();
        config.put(environment.getProperty("spring.cloud.config.starttime"), runtimeMXBean.getStartTime());
        config.put(environment.getProperty("spring.cloud.config.uptime"), runtimeMXBean.getUptime());
        
        builder.withDetail("spring.cloud.config", config);
        throw new UnsupportedOperationException("Unimplemented method 'contribute'");
    }

}
