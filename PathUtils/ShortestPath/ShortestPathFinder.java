package PathUtils.ShortestPath;

import PixelMap.PixelMap;
import java.util.List;

public abstract class ShortestPathFinder {
    protected int[][] map;
    protected int numRows;
    protected int numCols;

    ShortestPathFinder(int[][] map) {
        setMap(map);
    }

    ShortestPathFinder(PixelMap pixelMap) {
        this(pixelMap.getPixelMap());
    }

    private void setMap(int[][] map) {
        this.map = map;
        this.numRows = map.length;
        this.numCols = map[0].length;
    }

    public abstract List<Cell> findPath();
}
