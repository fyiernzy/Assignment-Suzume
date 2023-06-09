package com.assignment.suzume.map.shortest;

import static com.assignment.suzume.map.utils.PathUtils.convertListVectorToName;

import java.util.*;

import com.assignment.suzume.map.PixelMap;

public class DFSFinder extends ShortestPathFinder {
    private int shortestDistance = Integer.MAX_VALUE;
    private List<List<int[]>> shortestPaths = new ArrayList<List<int[]>>();

    public DFSFinder(int[][] map) {
        super(map);
    }

    public DFSFinder(PixelMap pixelMap) {
        super(pixelMap);
    }

    @Override
    public List<List<String>> findAllShortestPaths() {
        pathFinderHelper(0, 0, 0, 4, new ArrayList<>(), new BitSet());
        return convertListVectorToName(shortestPaths);
    }

    public List<List<String>> findAllShortestPaths(int numOfStationRequired) {
        pathFinderHelper(0, 0, 0, numOfStationRequired, new ArrayList<>(), new BitSet());
        return convertListVectorToName(shortestPaths);
    }

    private void pathFinderHelper(int row, int col, int numOfStationVisited, int numOfStationRequired,
            List<int[]> currentPath, BitSet visited) {
        if (!isValidLocation(row, col) || visited.get(getKey(row, col))) {
            return;
        }

        if (isStation(row, col)) {
            numOfStationVisited++;
        }

        if (numOfStationVisited > numOfStationRequired) {
            return;
        }

        if (currentPath.size() > shortestDistance) {
            return;
        }

        currentPath.add(new int[] { row, col });

        if (isDestination(row, col)) {
            if (currentPath.size() < shortestDistance) {
                shortestDistance = currentPath.size();
                shortestPaths = null; // Helps GC
                shortestPaths = new ArrayList<>();
            }

            if (currentPath.size() == shortestDistance) {
                shortestPaths.add(new ArrayList<>(currentPath));
            }

            return;
        }

        visited.set(getKey(row, col));
        for (int[] dir : DIRECTIONS) {
            List<int[]> tmp = new ArrayList<>(currentPath);
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            pathFinderHelper(newRow, newCol, numOfStationVisited,
                    numOfStationRequired, tmp, visited);
        }
        visited.flip(getKey(row, col));
    }
}
