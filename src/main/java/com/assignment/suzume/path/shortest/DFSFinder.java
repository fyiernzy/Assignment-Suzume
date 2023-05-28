package com.assignment.suzume.path.shortest;

import java.util.*;
import com.assignment.suzume.map.PixelMap;
import static com.assignment.suzume.utils.PathUtils.*;

public class DFSFinder extends ShortestPathFinder {
    private static int[][] DIRS = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
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
        pathFinderHelper(0, 0, new ArrayList<>(), new HashSet<>());
        return posToDirections(shortestPaths);
    }

    private void pathFinderHelper(int row, int col, List<int[]> currentPath, Set<Integer> visited) {
        if (!isValidLocation(row, col) || visited.contains(getKey(row, col))) {
            return;
        }

        if (currentPath.size() > shortestDistance) {
            return;
        }

        currentPath.add(new int[] { row, col });

        if (isDestination(row, col)) {
            if (currentPath.size() < shortestDistance) {
                shortestDistance = currentPath.size();
                shortestPaths.clear();
            }

            if (currentPath.size() == shortestDistance) {
                shortestPaths.add(new ArrayList<>(currentPath));
            }

            return;
        }

        visited.add(getKey(row, col));
        for (int[] dir : DIRS) {
            List<int[]> tmp = new ArrayList<>(currentPath);
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            pathFinderHelper(newRow, newCol, tmp, visited);
        }
        visited.remove(getKey(row, col));
    }
}
