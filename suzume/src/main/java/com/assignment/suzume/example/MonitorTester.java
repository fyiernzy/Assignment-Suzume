package com.assignment.suzume.example;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.map.shortest.*;
import com.assignment.suzume.map.utils.MapUtils;
import com.assignment.suzume.example.monitor.PerformanceEvaluator;

public class MonitorTester {
    public static void main(String[] args) {
        PixelMap combinedMap = MapUtils.getCombinedMap();
        evaluateShortestPathFinder(DFSFinder.class, combinedMap);
        evaluateShortestPathFinder(BFSFinder.class, combinedMap);
    }

    public static <T extends ShortestPathFinder> void evaluateShortestPathFinder(Class<T> finderClass, PixelMap map) {
        System.out.println("The evaluation of " + finderClass.getSimpleName());
        System.out.println("=".repeat(18 + finderClass.getSimpleName().length()));

        try {
            ShortestPathFinder finder = finderClass.getDeclaredConstructor(PixelMap.class).newInstance(map);
            PerformanceEvaluator evaluator = new PerformanceEvaluator();
            evaluator.evaluate(finder, ShortestPathFinder::findAllShortestPaths);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
        }
    }
}
