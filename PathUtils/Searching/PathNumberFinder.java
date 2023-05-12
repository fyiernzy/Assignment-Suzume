package PathUtils.Searching;

import PixelMap.PixelMap;

public abstract class PathNumberFinder {
    protected static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    protected final int[][] map;
    protected final int rows;
    protected final int cols;

    public PathNumberFinder(int[][] map) {
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
    }

    public PathNumberFinder(PixelMap map) {
        this(map.getPixelMap());
    }

    public int countPaths() {
        return countPaths(3);
    }
    
    public abstract int countPaths(int numOfStation);

    // A helper method to check if a position is a station
    protected boolean isStation(int row, int col) {
        return map[row][col] == 2;
    }

    // A helper method to check if a position is the destination
    protected boolean isDestination(int row, int col) {
        return row == rows - 1 && col == cols - 1;
    }

    protected boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols && map[row][col] != 1;
    }
}
