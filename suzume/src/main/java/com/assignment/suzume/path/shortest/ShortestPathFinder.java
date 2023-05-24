package com.assignment.suzume.path.shortest;

import java.util.List;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.AbstractPathFinder;

public abstract class ShortestPathFinder extends AbstractPathFinder {
    public ShortestPathFinder(int[][] map) {
        super(map);
    }

    public ShortestPathFinder(PixelMap pixelMap) {
        super(pixelMap);
    }

    public abstract List<List<String>> findAllShortestPaths();
}
