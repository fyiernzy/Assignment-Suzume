package Examples.ExampleUtils;

public class MapPrinter {
    public static void prettyPrint(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int size = 4;
        String format = "%" + size + "d";

        System.out.print(" ".repeat(size));

        for(int i = 0; i < numCols; i++) {
            System.out.printf(format, i);
        }

        System.out.println();

        for(int row = 0; row < numRows; row++) {
            System.out.printf(" %-" + (size - 1) + "d", row);
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }

    public static void printGrid(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int size = 2;
        String format = "%" + size + "d";

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }

    public static void printGrid(char[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int size = 3;
        String format = "%" + size + "c";

        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                System.out.printf(format, grid[row][col]);
            }
            System.out.println();
        }
    }


}
