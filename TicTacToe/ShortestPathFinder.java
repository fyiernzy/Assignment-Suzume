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
    private List<Cell> paths;

    ShortestPathFinder(int[][] map) {
        setMap(map);
    }

    ShortestPathFinder(PixelMap pixelMap) {
        this(pixelMap.getPixelMap());
    }

    public void setMap(int[][] map) {
        this.map = map;
        this.numRows = map.length;
        this.numRows = map[0].length;
    }

    private int heuristic(Cell n, Cell goal) {
        return Math.abs(n.x - goal.x) + Math.abs(n.y - goal.y);
    }

    private boolean isValid(int row, int col) {
        return (row >= 0 && row < numRows)
                && (col >= 0 && col < numCols)
                && map[row][col] != 1;
    }

    private Cell[] initializeCells() {
        Cell goal = new Cell(numRows - 1, numCols - 1, null, 0, 0);
        Cell start = new Cell(0, 0, null, 0, heuristic(new Cell(0, 0, null, 0, 0), goal));
        return new Cell[] { start, goal };
    }

    private List<Cell> getNeighbors(Cell cell) {
        List<Cell> neighbors = new ArrayList<>();
        int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // directions
        for (int[] dir : dirs) {
            int x = cell.x + dir[0]; // neighbor's x coordinate
            int y = cell.y + dir[1]; // neighbor's y coordinate

            // Check if the neighbor is valid and not in the closed set
            if (isValid(x, y)) {
                neighbors.add(new Cell(x, y, null, 0, 0));
            }
        }
        return neighbors;
    }

    private List<Cell> reconstructPath(Cell current) {
        List<Cell> path = new ArrayList<>();
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    public List<Cell> findPath() {
        Cell[] cells = initializeCells();
        Cell start = cells[0], goal = cells[1];
        PriorityQueue<Cell> openSet = new PriorityQueue<>((a, b) -> a.f - b.f);
        Set<Cell> closedSet = new HashSet<>();

        openSet.add(start);

        while (!openSet.isEmpty()) {
            Cell current = openSet.poll();

            closedSet.add(current);

            if (current.equals(goal)) {
                List<Cell> paths = reconstructPath(current);
                this.paths = paths;
                return paths;
            }

            for (Cell neighbor : getNeighbors(current)) {
                if (!closedSet.contains(neighbor)) {
                    // Calculate the g(n), h(n) and f(n) values for the neighbor
                    int g = current.g + 1; // assume unit cost for each step
                    int h = heuristic(neighbor, goal);
                    int f = g + h;

                    // Check if the neighbor is in the open set and has a lower f(n) value
                    boolean better = openSet.stream()
                            .filter(cell -> cell.equals(neighbor))
                            .allMatch(cell -> cell.f > f);

                    // If the neighbor is better, add it to the open set with the current cell as
                    // its parent
                    if (better) {
                        openSet.add(new Cell(neighbor.x, neighbor.y, current, g, h));
                    }
                }
            }
        }

        // Return null if no path is found
        return null;
    }
    
    public void printMap() {
        printMap(this.map);
    }

    void printMap(int[][] map) {
        for (int[] row : map) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public void printCoordinates() {
        printCoordinates(this.paths);
    }

    void printCoordinates(List<Cell> path) {
        for (Cell cell : path) {
            System.out.println("(" + cell.x + ", " + cell.y + ")");
        }
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

        // Find the shortest path using A* algorithm
        ShortestPathFinder finder = new ShortestPathFinder(grid);
        List<Cell> path = finder.findPath();
        finder.printMap();
        // Print the grid with the path marked as *
        // printPath(grid ,path);

        // Print the coordinates of each cell in the path
        // finder.printCoordinates(path);
    }

    
}
