package com.assignment.suzume.example.monitor;

import java.util.function.Consumer;
import java.util.Map;

public class PerformanceEvaluator {
    private final MonitorReportPrinter monitorReportPrinter;
    private final MemoryMonitor memoryMonitor;
    private final ExecutionTimeMonitor executionTimeMonitor;

    public PerformanceEvaluator() {
        this.monitorReportPrinter = new MonitorReportPrinter();
        this.memoryMonitor = new MemoryMonitor();
        this.executionTimeMonitor = new ExecutionTimeMonitor();
    }

    public <T> void evaluate(T obj, Consumer<T> function, int repeat) {
        Map<String, Long> memoryReport = memoryMonitor.generateReport(obj, function, repeat);
        Map<String, Long> executionTimeReport = executionTimeMonitor.generateReport(obj, function, repeat);
        monitorReportPrinter.printReport("Average Memory Usage Report", memoryReport);
        monitorReportPrinter.printReport("Average Execution Time Report", executionTimeReport);
    }

    public <T> void evaluate(T obj, Consumer<T> function) {
        evaluate(obj, function, 10);
    }
}
