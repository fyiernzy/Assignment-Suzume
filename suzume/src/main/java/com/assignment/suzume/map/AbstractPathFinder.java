package com.assignment.suzume.map;

import static com.assignment.suzume.constants.MapConst.*;

/**
 * The {@code AbstractPathFinder} class is an abstract base class for
 * path-finding algorithms
 * in a two-dimensional map. It provides common functionality and utility
 * methods used by concrete
 * path-finding implementations.
 *
 * <p>
 * The class encapsulates methods for checking the validity of locations,
 * identifying obstacles,
 * and determining whether a location is the destination. Concrete path-finding
 * algorithms can extend
 * this class and implement their specific logic for traversing the map and
 * finding optimal paths.
 *
 * <p>
 * The {@code AbstractPathFinder} class includes a constant array
 * {@code DIRECTIONS} that represents
 * the possible movements in the map, such as moving up, down, left, or right.
 * This array is used by
 * concrete path-finding algorithms to explore neighboring locations.
 *
 * <p>
 * Key features and functions of the {@code AbstractPathFinder} class:
 * <ul>
 * <li>Providing constructors to initialize the map data either directly or
 * through a {@code PixelMap} object.</li>
 * <li>Storing the map data in a two-dimensional integer array.</li>
 * <li>Determining the number of rows and columns in the map.</li>
 * <li>Verifying whether a given location is the destination.</li>
 * <li>Checking if a location is an obstacle within the map.</li>
 * <li>Ensuring that a location falls within the map boundaries.</li>
 * <li>Validating whether a location is both within the map boundaries and not
 * an obstacle.</li>
 * <li>Computing a unique key for a given location based on its row and column
 * coordinates.</li>
 * </ul>
 *
 * <p>
 * <strong>Note:</strong> This class is abstract and should be extended by
 * concrete path-finding
 * algorithms to provide their specific implementations.
 *
 * @see PixelMap
 */

public abstract class AbstractPathFinder {
    // Up, Down, Left, Right
    protected static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
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
    protected boolean isObstacle(int row, int col) {
        return OBSTACLE.is(map[row][col]);
    }

    /**
     * Checks if the given coordinates represent a station on the map.
     *
     * @param row The row index of the coordinate.
     * @param col The column index of the coordinate.
     * @return {@code true} if the coordinates represent a station, {@code false}
     *         otherwise.
     */
    protected boolean isStation(int row, int col) {
        return STATION.is(map[row][col]);
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
