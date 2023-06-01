package com.assignment.suzume.connecting;

public class MapUtils {
    public static void visualizeMap(char[][] grid) {
        visualizeMap(grid, 2);
    }

    public static void visualizeMap(char[][] grid, int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        String format = "%" + formatSize + "d";

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

    public static char[][] transformMap(int[][] map) {
        int numOfRow = map.length;
        int numOfCol = map.length;
        char[][] grid = new char[numOfRow][numOfCol];

        for(int row = 0; row < numOfRow; row++) {
            for(int col = 0; col < numOfCol; col++) {
                switch(grid[row][col]) {
                    case 0 -> grid[row][col] = ' ';
                    case 1 -> grid[row][col] = '|';
                    case 2 -> grid[row][col] = '@';
                    case 3 -> grid[row][col] = 'X';
                }
            }
        }
        return grid;
    }

    public static char[][] getClonedMap(char[][] map) {
        int numOfRow = map.length;
        int numOfCol = map[0].length;

        char[][] cloned = new char[numOfRow][numOfCol];
        for(int i = 0; i < numOfRow; i++) {
            System.arraycopy(map[i], 0, cloned[i], 0, numOfCol);
        }
        return cloned;
    }
}
