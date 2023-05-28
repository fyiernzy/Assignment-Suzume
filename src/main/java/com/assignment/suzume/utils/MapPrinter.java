package com.assignment.suzume.utils;

public class MapPrinter {

    public static void prettyPrint(int[][] grid) {
        prettyPrint(grid, 4);
    }

    public static void prettyPrint(int[][] grid, int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        String format = "%" + formatSize + "d";

        System.out.print(" ".repeat(formatSize));

        for(int i = 0; i < numCols; i++) {
            System.out.printf(format, i);
        }

        System.out.println();

        for(int row = 0; row < numRows; row++) {
            System.out.printf(" %-" + (formatSize - 1) + "d", row);
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }

    public static void printGrid(int[][] grid) {
        printGrid(grid, 2);
    }

    public static void printGrid(int[][] grid, int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        String format = "%" + formatSize + "d";

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }

    public static void printGrid(char[][] grid) {
        printGrid(grid, 3);
    }

    public static void printGrid(char[][] grid, int formatSize) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        String format = "%" + formatSize + "c";

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }
}
