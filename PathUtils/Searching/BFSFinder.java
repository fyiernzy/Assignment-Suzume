package PathUtils.Searching;

import java.util.*;
import PixelMap.PixelMap;

public class BFSFinder extends PathNumberFinder {

    public BFSFinder(int[][] map) {
        super(map);
    }

    public BFSFinder(PixelMap map) {
        super(map);
    }

    static class Node {
        int row;
        int col;
        int cStation; // Cumulative number of station
        Node parent;
        Set<Integer> visited;

        Node(int row, int col, int cStation, Set<Integer> visited) {
            this.row = row;
            this.col = col;
            this.cStation = cStation;
            this.visited = visited;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            Node other = (Node) obj;
            return row == other.row && col == other.col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    @Override
    public int countPaths(int numberOfStation) {
        Queue<int[]> pixelQueue = new LinkedList<>();
        Queue<boolean[]> visitedQueue = new LinkedList<>();

        boolean[] startVisited = new boolean[rows * cols];
        startVisited[getKey(0, 0)] = true;

        pixelQueue.offer(new int[] {0, 0, 0});
        visitedQueue.offer(startVisited);

        int numberOfPath = 0;
        while (!pixelQueue.isEmpty()) {
            int[] current = pixelQueue.poll();
            boolean[] visited = visitedQueue.poll();

            int row = current[0];
            int col = current[1];
            int cStation = current[2];

            for (int[] neighbor : getNeighbors(row, col, cStation, visited)) {
                int newRow = neighbor[0];
                int newCol = neighbor[1];

                if (isStation(newRow, newCol)) {
                    if (cStation < numberOfStation) {
                        neighbor[2]++;
                    } else {
                        continue;
                    }
                }

                if (isDestination(newRow, newCol)) {
                    if(cStation == numberOfStation) {
                        numberOfPath++;
                    }
                    continue;
                }
                    
                pixelQueue.offer(neighbor);
                boolean[] newVisited = Arrays.copyOf(visited, visited.length);
                newVisited[getKey(newRow, newCol)] = true;
                visitedQueue.offer(newVisited);
            }

            current = null; // Helps GC
            visited = null; // Helps GC
        }

        return numberOfPath;
    }

    private List<int[]> getNeighbors(int row, int col, int cStation, boolean[] visited) {
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (isValid(nextRow, nextCol) && !isVisited(visited, nextRow, nextCol)) {
                neighbors.add(new int[] {nextRow, nextCol, cStation});
            }
        }
        return neighbors;
    }

    private boolean isVisited(boolean[] visited, int nextRow, int nextCol) {
        return visited[getKey(nextRow, nextCol)] == true;
    }

    private int getKey(int row, int col) {
        return row * cols + col;
    }
}
