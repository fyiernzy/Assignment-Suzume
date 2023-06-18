package com.assignment.suzume.map.shortest;

import java.util.List;

import com.assignment.suzume.map.AbstractPathFinder;
import com.assignment.suzume.map.PixelMap;

public abstract class ShortestPathFinder extends AbstractPathFinder {
    public ShortestPathFinder(int[][] map) {
        super(map);
    }

    public ShortestPathFinder(PixelMap pixelMap) {
        super(pixelMap);
    }

    public abstract List<List<String>> findAllShortestPaths();
}
