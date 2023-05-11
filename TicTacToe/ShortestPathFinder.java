package TicTacToe;

import java.util.*;
import PixelMap.*;

// Define a class for each cell
class Cell {
    int x; // x coordinate
    int y; // y coordinate
    Cell parent; // parent cell
    int g; // actual cost from start cell to this cell
    int h; // estimated cost from this cell to goal cell
    int f; // total cost (g + h)

    // Constructor
    Cell(int x, int y, Cell parent, int g, int h) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.g = g;
        this.h = h;
        this.f = g + h;
    }

    // Override equals method to compare cells by coordinates
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Cell))
            return false;
        Cell other = (Cell) obj;
        return this.x == other.x && this.y == other.y;
    }

    // Override hashCode method to use coordinates as hash code
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}

public class ShortestPathFinder {
    private int[][] map;
    private int numRows;
    private int numCols;

    ShortestPathFinder(int[][] map) {
        this.map = map;
        this.numRows = map.length;
        this.numRows = map[0].length;
    }

    ShortestPathFinder(PixelMap pixelMap) {
        this(pixelMap.getPixelMap());
    }

    int heuristic(Cell n, Cell goal) {
        return Math.abs(n.x - goal.x) + Math.abs(n.y - goal.y);
    }

    boolean isValid(int row, int col) {
        return (row >= 0 && row < numRows)
                && (col >= 0 && col < numCols)
                && map[row][col] != 1;
    }

    
    List<Cell> findPath(int[][] grid, Cell start, Cell goal) {
        PriorityQueue<Cell> openSet = new PriorityQueue<>((a, b) -> a.f - b.f);

        // Create a hash set to store the closed set of cells
        HashSet<Cell> closedSet = new HashSet<>();

        // Add the start cell to the open set
        openSet.add(start);

        // Loop until the open set is empty or the goal is reached
        while (!openSet.isEmpty()) {
            // Get the cell with the lowest f(n) value from the open set and remove it
            Cell current = openSet.poll();

            // Add it to the closed set
            closedSet.add(current);

            // Check if it is the goal cell
            if (current.equals(goal)) {
                // Return the path by tracing back the parent cells
                List<Cell> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path); // reverse the order of the path
                return path;
            }

            // Generate the neighbors of the current cell
            int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // up, down, left, right directions
            for (int[] dir : dirs) {
                int x = current.x + dir[0]; // neighbor's x coordinate
                int y = current.y + dir[1]; // neighbor's y coordinate

                // Check if the neighbor is valid and not in the closed set
                if (isValid(grid, x, y) && !closedSet.contains(new Cell(x, y, null, 0, 0))) {
                    // Calculate the g(n), h(n) and f(n) values for the neighbor
                    int g = current.g + 1; // assume unit cost for each step
                    int h = heuristic(new Cell(x, y, null, 0, 0), goal);
                    int f = g + h;

                    // Check if the neighbor is in the open set and has a lower f(n) value
                    boolean better = openSet.stream()
                            .filter(cell -> cell.equals(new Cell(x, y, null, 0, 0)))
                            .allMatch(cell -> cell.f > f);

                    // If the neighbor is better, add it to the open set with the current cell as
                    // its parent
                    if (better) {
                        openSet.add(new Cell(x, y, current, g, h));
                    }
                }
            }
        }

        // Return null if no path is found
        return null;
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 3 }
        };

        // Define the start cell and the goal cell
        Cell start = new Cell(0, 0, null, 0, heuristic(new Cell(0, 0, null, 0, 0), new Cell(4, 4, null, 0, 0)));
        Cell goal = new Cell(4, 4, null, 0, 0);
        // Find the shortest path using A* algorithm
        List<Cell> path = findPath(grid, start, goal);

        // Print the grid with the path marked as *
        // printPath(grid ,path);

        // Print the coordinates of each cell in the path
        printCoordinates(path);
    }

    public static void printCoordinates(List<Cell> path) {
        for (Cell cell : path) {
            System.out.println("(" + cell.x + ", " + cell.y + ")");
        }
    }
}
