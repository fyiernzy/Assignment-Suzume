package com.assignment.suzume.example;

import java.util.List;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.map.searching.*;
import com.assignment.suzume.map.utils.MapUtils;

public class SearchingTester {
    public static void main(String[] args) {
        List<PixelMap> maps = MapUtils.getMapList();
        int numOfStation = 3;
        testFinderOnList(DFSFinder.class, maps, numOfStation);
        testFinderOnList(BFSFinder.class, maps, numOfStation);

        PixelMap map = MapUtils.getCombinedMap();
        numOfStation = 4; 
        testFinder(DFSFinder.class, map, numOfStation);
        testFinder(BFSFinder.class, map, numOfStation);
    }

    private static void testFinderOnList(Class<? extends PathNumberFinder> cls, List<PixelMap> maps, int numOfStation) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < maps.size(); i++) {
            testFinder(cls, maps.get(i), numOfStation);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time used: " + (endTime - startTime) + "ms\n");
    }

    private static void testFinder(Class<? extends PathNumberFinder> cls, PixelMap map, int numOfStation) {
        long startTime = System.currentTimeMillis();
        try {
            PathNumberFinder finder = cls.getDeclaredConstructor(PixelMap.class).newInstance(map);
            int count = finder.countPaths(numOfStation);
            System.out.printf("%s = %d, ", cls.getSimpleName(), count);
        } catch (ReflectiveOperationException ex) {
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time used: " + (endTime - startTime) + "ms");
    }
}
