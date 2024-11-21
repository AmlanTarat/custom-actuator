package com.amlan.actuator.custom_actuator.endpoints;



import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.Info.Builder;

import com.amlan.actuator.custom_actuator.util.CpuUsagePerThreadUtil;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Endpoint(id="cpuUsage")
public class CpuUsageEnpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(CpuUsageEnpoint.class);
    private static final Marker DEFAULT_MARKER_FACTORY = MarkerFactory.getMarker("ALWAYS");

    @PostConstruct
    protected void init(){
        LOGGER.info(DEFAULT_MARKER_FACTORY,"@PostConstruct cpuUsage");
    }

    @PreDestroy
    protected void shutDown(){
        LOGGER.warn(DEFAULT_MARKER_FACTORY,"shutDown cpuUsage [callerInfo={}]", "@PreDestroy");
    }

    @ReadOperation
    public Info cpuInfo(){
        List<String> usageInfo = Collections.emptyList();
        try{
            usageInfo = CpuUsagePerThreadUtil.usage();
        }catch(Throwable t){
            LOGGER.warn("Throwable getting CPUUsage issue");
        }
        return new Builder().withDetail("cpuUsage", usageInfo).build();
    }

}
