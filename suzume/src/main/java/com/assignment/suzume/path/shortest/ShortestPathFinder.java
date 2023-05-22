package com.assignment.suzume.path.shortest;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.AbstractPathFinder;

public abstract class ShortestPathFinder extends AbstractPathFinder {
    ShortestPathFinder(int[][] map) {
        super(map);
    }

    ShortestPathFinder(PixelMap pixelMap) {
        super(pixelMap);
    } 
}
