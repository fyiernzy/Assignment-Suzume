package com.assignment.suzume;

import java.util.*;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.searching.*;
import static com.assignment.suzume.utils.MapGetter.*;

// Reference: https://github.com/ziflhigan/WIA1002-G303-GA/blob/main/MapSolution/MapDecipher.java#L202

public class SearchingExample {
    public static void main(String[] args) {
        List<PixelMap> maps = getMapList();
        int numOfStation = 3;
        testFinderOnList(DFSFinder.class, maps, numOfStation);
        testFinderOnList(BFSFinder.class, maps, numOfStation);

        PixelMap map = getCombinedMap();
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
