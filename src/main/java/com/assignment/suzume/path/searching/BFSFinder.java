package com.assignment.suzume.path.searching;

import java.util.*;
import com.assignment.suzume.map.PixelMap;

public class BFSFinder extends PathNumberFinder {

    public BFSFinder(int[][] map) {
        super(map);
    }

    public BFSFinder(PixelMap map) {
        super(map);
    }

    @Override
    public int countPaths(int numberOfStation) {
        Queue<int[]> pixelQueue = new LinkedList<>();
        Queue<BitSet> visitedQueue = new LinkedList<>();

        BitSet startVisited = new BitSet(numRows * numCols);
        startVisited.set(getKey(0, 0));

        pixelQueue.offer(new int[] {0, 0, 0});
        visitedQueue.offer(startVisited);

        int numberOfPath = 0;
        while (!pixelQueue.isEmpty()) {
            int[] current = pixelQueue.poll();
            BitSet visited = visitedQueue.poll();

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
                BitSet newVisited = (BitSet) visited.clone();
                newVisited.set(getKey(newRow, newCol));
                visitedQueue.offer(newVisited);
            }

            current = null; // Helps GC
            visited = null; // Helps GC
        }

        return numberOfPath;
    }

    private List<int[]> getNeighbors(int row, int col, int cStation, BitSet visited) {
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : DIRECTIONS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (isValidLocation(nextRow, nextCol) && !isVisited(visited, nextRow, nextCol)) {
                neighbors.add(new int[] {nextRow, nextCol, cStation});
            }
        }
        return neighbors;
    }

    private boolean isVisited(BitSet visited, int nextRow, int nextCol) {
        return visited.get(getKey(nextRow, nextCol));
    }
}
