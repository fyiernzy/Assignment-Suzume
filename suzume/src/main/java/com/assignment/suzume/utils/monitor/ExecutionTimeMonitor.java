package com.assignment.suzume.utils.monitor;

import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class ExecutionTimeMonitor extends AbstractMonitor {

    @Override
    public <T> Map<String, Long> generateReport(long value) {
        long nanoseconds = value;
        return new TreeMap<>(Map.of(
            "nanoseconds", nanoseconds,
            "milliseconds", nanoseconds / 1000000,
            "seconds", nanoseconds / 1000000000
        ));
    }
    
    @Override
    public <T> long monitorSinglePerformance(T obj, Consumer<T> function) {
        long startTime = System.nanoTime();
        function.accept(obj);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}
