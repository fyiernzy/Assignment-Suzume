package PixelMap;

public class PathFinder {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private final int[][] map;
    private final int rows;
    private final int cols;

    public PathFinder(int[][] map) {
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
    }

    public int countPaths() {
        return countPaths(3);
    }

    public int countPaths(int numOfStation) {
        int[][] visited = new int[rows][cols];
        return countPathsHelper(visited, numOfStation, 0, 0, 0);
    }

    private int countPathsHelper(int[][] visited, int numOfStation, int row, int col, int stations) {
        if (!isValid(row, col) || isVisited(visited, row, col)) {
            return 0;
        }

        if (isStation(row, col)) {
            stations++;
            if (stations > numOfStation) {
                return 0;
            }
        }

        if (stations == numOfStation && isDestination(row, col)) {
            return 1;
        }

        visited[row][col] = 1;
        int count = 0;
        for(int[] step : DIRECTIONS) {
            count += countPathsHelper(visited, numOfStation, row + step[0], col + step[1], stations);
        } 
        visited[row][col] = 0;
        return count;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private boolean isVisited(int[][] visited, int row, int col) {
        return visited[row][col] == 1 || map[row][col] == 1;
    }

    // A helper method to check if a position is a station
    private boolean isStation(int row, int col) {
        return map[row][col] == 2;
    }

    // A helper method to check if a position is the destination
    private boolean isDestination(int row, int col) {
        return row == rows - 1 && col == cols - 1;
    }
}
