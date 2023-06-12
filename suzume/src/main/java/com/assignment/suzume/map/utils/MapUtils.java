package com.assignment.suzume.map.utils;

import java.util.List;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.map.reader.MapProcessor;
import com.assignment.suzume.map.reader.MapReader;

public class MapUtils {
    private static final String IMAGE_PATH = "./src/main/java/com/assignment/suzume/map/Images/";
    private static final String IMAGE_SUFFIX = ".png";
    private static final int[][] NUM_OF_PATH = { { 16, 41 }, { 38, 27 } };
    private static List<PixelMap> list;
    private static PixelMap combinedMap;

    /**
     * Retrieves a specific map from the map list based on the given index.
     *
     * @param index The index of the map to retrieve.
     * @return The map at the specified index.
     */
    public static PixelMap getMap(int index) {
        return getMapList().get(index);
    }

    /**
     * Retrieves the list of maps.
     *
     * @return The list of maps.
     */
    public static List<PixelMap> getMapList() {
        if (list == null) {
            MapReader reader = new MapReader();
            list = reader.readImages(IMAGE_PATH, IMAGE_SUFFIX);
            list.forEach(map -> map.transform(64));
        }
        return list;
    }

    /**
     * Retrieves the combined map by combining multiple maps from the map list.
     *
     * @return The combined map.
     */
    public static PixelMap getCombinedMap() {
        if (combinedMap == null) {
            List<PixelMap> list = getMapList();

            MapProcessor processor = new MapProcessor();
            combinedMap = processor.combinePixelMaps(NUM_OF_PATH, list);

            combinedMap.obstacles(map -> {
                final int numRow = map.size()[0] / 2;
                final int numCol = map.size()[1] / 2;
                int[][] grid = map.getPixelMap();

                for (int i = 0; i < 3; i++) {
                    int targetRow = (i / 2 + 1) * numRow - 1;
                    int targetCol = (i % 2 + 1) * numCol - 1;
                    grid[targetRow][targetCol] = 1;
                }
                return map;
            });
        }

        return combinedMap;
    }

    /**
     * Creates a deep copy of a two-dimensional character array map.
     *
     * @param map The original two-dimensional character array map to be cloned.
     * @return A cloned copy of the input map.
     */
    public static char[][] getClonedMap(char[][] map) {
        int numOfRow = map.length;
        int numOfCol = map[0].length;

        char[][] cloned = new char[numOfRow][numOfCol];
        for (int i = 0; i < numOfRow; i++) {
            System.arraycopy(map[i], 0, cloned[i], 0, numOfCol);
        }
        return cloned;
    }

    /**
     * Transforms a two-dimensional integer array map into a corresponding character
     * array grid.
     * Each integer value in the map is mapped to a specific character value in the
     * grid.
     *
     * @param map The two-dimensional integer array map to be transformed.
     * @return A character array grid representing the transformed map.
     */
    public static char[][] transformMap(int[][] map) {
        int numOfRow = map.length;
        int numOfCol = map[0].length;
        char[][] grid = new char[numOfRow][numOfCol];

        for (int row = 0; row < numOfRow; row++) {
            for (int col = 0; col < numOfCol; col++) {
                switch (map[row][col]) {
                    case 0 -> grid[row][col] = ' ';
                    case 1 -> grid[row][col] = '|';
                    case 2 -> grid[row][col] = '@';
                    case 3 -> grid[row][col] = 'X';
                }
            }
        }
        return grid;
    }

    /**
     * Prints the two-dimensional integer array grid in a formatted and visually
     * appealing manner.
     * The format size specifies the width of each element in the grid.
     * By default, the format size is set to 4.
     *
     * @param grid The two-dimensional integer array to be printed.
     */
    public static void prettyPrint(int[][] grid) {
        prettyPrint(grid, 4);
    }

    /**
     * Prints the two-dimensional integer array grid in a formatted and visually
     * appealing manner.
     * The format size specifies the width of each element in the grid.
     *
     * @param grid       The two-dimensional integer array to be printed.
     * @param formatSize The width of each element in the grid.
     */
    public static void prettyPrint(int[][] grid, int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        String format = "%" + formatSize + "d";

        System.out.print(" ".repeat(formatSize));

        for (int i = 0; i < numCols; i++) {
            System.out.printf(format, i);
        }

        System.out.println();

        for (int row = 0; row < numRows; row++) {
            System.out.printf(" %-" + (formatSize - 1) + "d", row);
            for (int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * Prints the two-dimensional integer array grid without any formatting.
     * By default, the format size is set to 2.
     *
     * @param grid The two-dimensional integer array to be printed.
     */
    public static void printGrid(int[][] grid) {
        printGrid(grid, 2);
    }

    /**
     * Prints the two-dimensional integer array grid without any formatting.
     *
     * @param grid       The two-dimensional integer array to be printed.
     * @param formatSize The width of each element in the grid.
     */
    public static void printGrid(int[][] grid, int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        String format = "%" + formatSize + "d";

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }

    /**
     * Prints the two-dimensional character array grid without any formatting.
     * By default, the format size is set to 3.
     *
     * @param grid The two-dimensional character array to be printed.
     */
    public static void printGrid(char[][] grid) {
        printGrid(grid, 3);
    }

    /**
     * Prints the two-dimensional character array grid without any formatting.
     *
     * @param grid       The two-dimensional character array to be printed.
     * @param formatSize The width of each element in the grid.
     */
    public static void printGrid(char[][] grid, int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        String format = "%" + formatSize + "c";

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }
}
