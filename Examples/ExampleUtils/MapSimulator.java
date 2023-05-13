package Examples.ExampleUtils;

import java.util.*;
import static Examples.ExampleUtils.MapPrinter.*;
import PathUtils.ShortestPath.*;

public class MapSimulator {
    public void simulate(int[][] grid, List<String> steps) {
        int row = 0, col = 0;
        char[][] newGrid = transform(grid);
        newGrid[row][col] = '*';
        printGrid(newGrid);

        for(String step : steps) {
            int[] direction = getDirection(step);
            row += direction[0];
            col += direction[1];

            if(row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
                newGrid[row][col] = '*';
            }

            printGrid(newGrid);
            System.out.println("\n\n");

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void simulateUsingNode(int[][] grid, List<Node> list) {
        char[][] newGrid = transform(grid);
        printGrid(newGrid);
        for(Node node : list) {
            newGrid[node.row][node.col] = '*';
            printGrid(newGrid);
            System.out.println("\n\n");

            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int[] getDirection(String step) {
        switch(step) {
            case "Up": return new int[] {-1, 0};
            case "Down": return new int[] {1, 0};
            case "Left": return new int[] {0, -1};
            case "Right": return new int[] {0, 1};
            default: return new int[] {0, 0};
        }
    }

    public char[][] transform(int[][] grid) {
        char[][] newGrid = new char[grid.length][grid[0].length];

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == 3) {
                    newGrid[row][col] = 'X';
                    continue;
                }
                newGrid[row][col] = grid[row][col] == 0 ? ' ' : '|';
            }
        }

        return newGrid;
    }
}
