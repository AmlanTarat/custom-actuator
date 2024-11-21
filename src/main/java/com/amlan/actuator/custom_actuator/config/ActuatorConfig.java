package com.amlan.actuator.custom_actuator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import com.amlan.actuator.custom_actuator.endpoints.CpuUsageEnpoint;
import com.amlan.actuator.custom_actuator.endpoints.CustomHealthIndicator;
import com.amlan.actuator.custom_actuator.endpoints.CustomInfoActuator;

@AutoConfiguration
@Configuration(proxyBeanMethods = false)
public class ActuatorConfig {

    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    @Bean
    @Lazy
    @ConditionalOnProperty(value="custom.actuator.info.enabled", havingValue = "true", matchIfMissing = true)
    public CustomInfoActuator customInfoActuator(final @Lazy @Autowired Environment environment){
        return new CustomInfoActuator(environment);
    }

    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    @Bean(destroyMethod = "shutdown")
    @Lazy(false)
    @ConditionalOnProperty(value="custom.actuator.cpuusage", havingValue = "true", matchIfMissing = true)
    public CpuUsageEnpoint customCPUUsage(){
        return new CpuUsageEnpoint();
    }

    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    @Bean
    @Lazy(false)
    @ConditionalOnProperty(value="custom.actuator.health", havingValue = "true", matchIfMissing = true)
    public CustomHealthIndicator customHealthIndicator(){
        return new CustomHealthIndicator();
    }

}
