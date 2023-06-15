package com.assignment.suzume.map.searching;

import com.assignment.suzume.map.PixelMap;

public class DFSFinder extends PathNumberFinder {
    public DFSFinder(int[][] map) {
        super(map);
    }

    public DFSFinder(PixelMap map) {
        super(map);
    }

    @Override
    public int countPaths(int numOfStation) {
        int[][] visited = new int[numRows][numCols];
        return countPathsHelper(visited, numOfStation, 0, 0, 0);
    }

    private int countPathsHelper(int[][] visited, int numOfStation, int row, int col, int stations) {
        if (!isValidLocation(row, col) || isVisited(visited, row, col)) {
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

    private boolean isVisited(int[][] visited, int row, int col) {
        return visited[row][col] == 1;
    }
}
