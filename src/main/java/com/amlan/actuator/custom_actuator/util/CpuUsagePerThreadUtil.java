package com.amlan.actuator.custom_actuator.util;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CpuUsagePerThreadUtil {
    private CpuUsagePerThreadUtil(){

    }

    public static List<String> usage(){
// This is code is just a demo. Not a working one
        int sampleTime =1000;
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        RuntimeMXBean runTimeMXBean = ManagementFactory.getRuntimeMXBean();
        OperatingSystemMXBean osMXBean = ManagementFactory.getOperatingSystemMXBean();
        threadMXBean.getCurrentThreadCpuTime();
        ThreadInfo[] info = threadMXBean.dumpAllThreads(false,false );
        List<String> list = new ArrayList<>();
        list.add(info[0].getThreadId()+""+info[0].getThreadName());
        return list;
    }
}
