package com.assignment.suzume.utils.monitor;

import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractMonitor {

    public abstract <T> Map<String, Long> generateReport(long value);

    public <T> Map<String, Long> generateReport(T obj, Consumer<T> function) {
        return generateReport(monitorAveragePerformance(obj, function));
    }

    public <T> Map<String, Long> generateReport(T obj, Consumer<T> function, int repeat) {
        long value = monitorAveragePerformance(obj, function, repeat);
        return generateReport(value);
    }
    
    public abstract <T> long monitorSinglePerformance(T obj, Consumer<T> function);

    public <T> long monitorAveragePerformance(T obj, Consumer<T> function) {
        return monitorAveragePerformance(obj, function, 10);
    }

    public <T> long monitorAveragePerformance(T obj, Consumer<T> function, int repeat) {
        long total = 0;
        for(int i = 0; i < repeat; i++) {
            total += monitorSinglePerformance(obj, function);
        }
        return total / repeat;
    }
}
