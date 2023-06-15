package com.assignment.suzume.map.utils;

import java.util.*;
import com.assignment.suzume.map.shortest.BFSFinder;

public class PathUtils {

    public static List<String> getOneShortestPath() {
        BFSFinder finder = new BFSFinder(MapUtils.getCombinedMap());
        List<List<String>> path = finder.findAllShortestPaths();
        return path.get((int) Math.random() * path.size());
    }

    public static List<String> convertVectorToName(List<int[]> positions) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < positions.size() - 1; i++) {
            int[] current = positions.get(i);
            int[] next = positions.get(i + 1);
            result.add(getDirectionName(next[0] - current[0], next[1] - current[1]));
        }
        return result;
    }

    public static List<List<String>> convertListVectorToName(List<List<int[]>> positions) {
        List<List<String>> result = new ArrayList<>();
        for (List<int[]> pos : positions) {
            result.add(convertVectorToName(pos));
        }
        return result;
    }

    public static List<int[]> convertNameToVector(List<String> directions, int[] start) {
        List<int[]> result = new ArrayList<>();
        result.add(start);
        for (String direction : directions) {
            int[] step = getDirectionVector(direction);
            int newRow = result.get(result.size() - 1)[0] + step[0];
            int newCol = result.get(result.size() - 1)[1] + step[1];
            result.add(new int[] { newRow, newCol });
        }
        return result;
    }

    public static String getDirectionName(int row, int col) {
        if (row == 1 && col == 0) {
            return "Down";
        } else if (row == -1 && col == 0) {
            return "Up";
        } else if (row == 0 && col == 1) {
            return "Right";
        } else if (row == 0 && col == -1) {
            return "Left";
        } else {
            throw new IllegalArgumentException("Invalid direction");
        }
    }

    public static int[] getDirectionVector(String direction) {
        switch (direction) {
            case "Up":
                return new int[] { -1, 0 };
            case "Down":
                return new int[] { 1, 0 };
            case "Left":
                return new int[] { 0, -1 };
            case "Right":
                return new int[] { 0, 1 };
            default:
                return new int[] { 0, 0 };
        }
    }

    // simyi editted
    public static List<String> getShortestPath(int pathIndex) {
        BFSFinder finder = new BFSFinder(MapUtils.getCombinedMap());
        List<List<String>> allPaths = finder.findAllShortestPaths();
        return allPaths.get(pathIndex);
    }

}
