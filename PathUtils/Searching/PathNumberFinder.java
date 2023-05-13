package PathUtils.Searching;

import PixelMap.PixelMap;
import static PixelMap.MapConst.*;
import PathUtils.AbstractPathFinder;

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

    // A helper method to check if a position is a station
    protected boolean isStation(int row, int col) {
        return STATION.is(map[row][col]);
    }
}
