package com.assignment.suzume.utils.monitor;

import java.util.Map;
import java.util.TreeMap;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.function.Consumer;

public class MemoryMonitor extends AbstractMonitor {

    @Override
    public <T> Map<String, Long> generateReport(long val) {
        long bytes = val;
        return new TreeMap<>(Map.of(
            "bytes", bytes,
            "kilobytes", bytes / 1024,
            "megabytes", bytes / 1024 / 1024
        ));
    }

    @Override
    public <T> long monitorSinglePerformance(T obj, Consumer<T> function) {
        MemoryUsage beforeMemory = getMemoryUsage();
        function.accept(obj);
        MemoryUsage afterMemory = getMemoryUsage();
        long memoryUsed = afterMemory.getUsed() - beforeMemory.getUsed();
        return memoryUsed;
    }

    private MemoryUsage getMemoryUsage() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        return memoryMXBean.getHeapMemoryUsage();
    }
}
