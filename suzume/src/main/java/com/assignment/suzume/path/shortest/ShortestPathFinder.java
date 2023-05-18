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

    protected String getDirectionName(int row, int col) {
        if(row == 1 && col == 0) {
            return "Down";
        } else if(row == -1 && col == 0) {
            return "Up";
        } else if(row == 0 && col == 1) {
            return "Right";
        } else if(row == 0 && col == -1) {
            return "Left";
        } else {
            throw new IllegalArgumentException("Invalid direction");
        }
    }
}
