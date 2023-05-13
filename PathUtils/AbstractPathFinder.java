package PathUtils;

import PixelMap.PixelMap;
import static PixelMap.MapConst.*;

public abstract class AbstractPathFinder {
    // Up, Down, Left, Right
    protected static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }};
    protected int[][] map;
    protected int numRows;
    protected int numCols;

    public AbstractPathFinder(int[][] map) {
        setMap(map);
    }

    public AbstractPathFinder(PixelMap pixelMap) {
        this(pixelMap.getPixelMap());
    }

    private void setMap(int[][] map) {
        this.map = map;
        this.numRows = map.length;
        this.numCols = map[0].length;
    }

    /**
     * Check if a given location is the destination.
     *
     * @param row the row of the location.
     * @param col the column of the location.
     * @return true if the location is the destination, false otherwise.
     */
    protected boolean isDestination(int row, int col) {
        return DESTINATION.is(map[row][col]);
    }

    /**
     * Check if a given location is an obstacle.
     *
     * @param row the row of the location.
     * @param col the column of the location.
     * @return true if the location is an obstacle, false otherwise.
     */
    private boolean isObstacle(int row, int col) {
        return OBSTACLE.is(map[row][col]);
    }

    /**
     * Check if a given location is within the map boundaries.
     *
     * @param row the row of the location.
     * @param col the column of the location.
     * @return true if the location is within the map boundaries, false otherwise.
     */
    private boolean isWithinMap(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    /**
     * Check if a given location is valid, i.e., within the map boundaries and not
     * an obstacle.
     *
     * @param row the row of the location.
     * @param col the column of the location.
     * @return true if the location is valid, false otherwise.
     */
    protected boolean isValidLocation(int row, int col) {
        return isWithinMap(row, col) && !isObstacle(row, col);
    }

    /**
     * Compute the key of a given location.
     *
     * @param row the row of the location.
     * @param col the column of the location.
     * @return the key of the location.
     */
    protected int getKey(int row, int col) {
        return row * numCols + col;
    }
}
