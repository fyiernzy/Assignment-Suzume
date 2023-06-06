package com.assignment.suzume.map;

public class MapVisualizer {
    private static final int MAP_SIZE = 2;
    char[][] grid;

    public MapVisualizer(char[][] grid) {
        this.grid = grid;
    }

    public void visualizeMap() {
        visualizeMap(MAP_SIZE);
    }

    public void visualizeMap(int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        String format = "%" + formatSize + "s";

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }

        System.out.println("\n\n");
    }
}
