package com.assignment.suzume.utils.monitor;

import java.util.*;

public class MonitorReportPrinter {
    public void printReport(String title, Map<String, Long> report) {
        System.out.println(title);
        for(Map.Entry<String, Long> entry : report.entrySet()) {
            System.out.println("\\--->  %-15s : %d".formatted(entry.getKey(), entry.getValue()));
        }
        System.out.println();
    }
}
