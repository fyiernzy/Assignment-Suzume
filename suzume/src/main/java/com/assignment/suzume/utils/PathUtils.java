package com.assignment.suzume.utils;

import java.util.*;
import com.assignment.suzume.path.shortest.Node;

public class PathUtils {
    public static void printUsingNode(Node node) {
        preOrder(node, new StringBuilder());
    }

    public static void printUsingDirections(List<String> directions) {
        directions.forEach(x -> System.out.print(x + " "));
        System.out.println();
    }

    public static void printUsingPositions(List<int[]> positions) {
        positions.forEach(x -> System.out.print(Arrays.toString(x) + " "));
        System.out.println();
    }

    private static void preOrder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.toString());
            if (node.isLeaf()) {
                System.out.println(sb.toString() + "\n");
            } else {
                sb.append(" --> ");
                StringBuilder tmp = new StringBuilder(sb);
                preOrder(node.getLeftChild(), sb);
                sb = tmp;
                preOrder(node.getRightChild(), sb);
            }
        }
    }

    public static List<List<int[]>> getPositions(Node node) {
        List<int[]> current = new ArrayList<>();
        List<List<int[]>> result = new ArrayList<>();
        getPositions(node, result, current);
        return result;
    }

    private static void getPositions(Node node, List<List<int[]>> result, List<int[]> current) {
        if (node != null) {
            current.add(node.getPosition());
            if (node.isLeaf()) {
                result.add(current);
            } else {
                List<int[]> tmp = new ArrayList<>(current);
                getPositions(node.getLeftChild(), result, current);
                getPositions(node.getRightChild(), result, tmp);
            }
        }
    }

    public static List<String> posToDirection(List<int[]> positions) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < positions.size() - 1; i++) {
            int[] current = positions.get(i);
            int[] next = positions.get(i + 1);
            result.add(getDirectionName(next[0] - current[0], next[1] - current[1]));
        }
        return result;
    }

    public static List<List<String>> posToDirections(List<List<int[]>> positions) {
        List<List<String>> result = new ArrayList<>();
        for (List<int[]> pos : positions) {
            result.add(posToDirection(pos));
        }
        return result;
    }

    public static List<int[]> directionToPos(List<String> directions, int[] start) {
        List<int[]> result = new ArrayList<>();
        result.add(start);
        for (int i = 0; i < directions.size(); i++) {
            int[] step = getPosition(directions.get(i));
            int newRow = result.get(i)[0] + step[0];
            int newCol = result.get(i)[1] + step[1];
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

    public static int[] getPosition(String direction) {
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
}
