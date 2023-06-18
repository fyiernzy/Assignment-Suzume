package com.assignment.suzume.map.searching;

import com.assignment.suzume.map.AbstractPathFinder;
import com.assignment.suzume.map.PixelMap;

public abstract class PathNumberFinder extends AbstractPathFinder {
    public PathNumberFinder(int[][] map) {
        super(map);
    }

    public PathNumberFinder(PixelMap map) {
        super(map);
    }

    public int countPaths() {
        return countPaths(3);
    }
    
    public abstract int countPaths(int numOfStation);    
}
