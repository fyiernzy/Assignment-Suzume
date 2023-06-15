package com.assignment.suzume.example;

import java.util.*;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.utils.MapUtils;

public class PixelMapTester {
    public static void main(String[] args) {
        List<PixelMap> maps = MapUtils.getMapList();

        for(int i = 0; i < maps.size(); i++) {
            System.out.println("Map " + (i + 1) + ":");
            maps.get(i).showPixelMap();
            System.out.println();
        }
        
        PixelMap combinedMap = MapUtils.getCombinedMap();
        System.out.println("Combined Map:");
        combinedMap.showPixelMap();
    }
}
