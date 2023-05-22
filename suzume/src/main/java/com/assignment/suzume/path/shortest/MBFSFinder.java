package com.assignment.suzume.path.shortest;

import java.util.*;

public class MBFSFinder extends ShortestPathFinder {
    Map<Integer, Node> shortestPaths;

    public MBFSFinder(int[][] map) {
        super(map);
        this.shortestPaths = new HashMap<>();
    }

    public Map<Integer, Node> findAllShortestPaths() {
        Queue<Node> queue = new LinkedList<>();

        int numRow = map.length;
        int numCol = map[0].length;
        int[][] recDirection = { { -1, 0 }, { 0, -1 } }; // recommended direction
        int[][] altDirection = { { 1, 0 }, { 0, 1 } }; // alternative direction

        queue.offer(new Node(numRow - 1, numCol - 1, 0, -1));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            boolean hasShortest = false;

            if (node.row == 0 && node.col == 0) {
                continue;
            }

            for (int[] dir : recDirection) {
                int newRow = node.row + dir[0];
                int newCol = node.col + dir[1];

                if (isValidLocation(newRow, newCol) && getKey(newRow, newCol) != node.parent) {
                    hasShortest = true;
                    addNewNode(newRow, newCol, node, shortestPaths, queue);
                }
            }

            if (!hasShortest) {
                for (int[] dir : altDirection) {
                    int newRow = node.row + dir[0];
                    int newCol = node.col + dir[1];

                    if (isValidLocation(newRow, newCol)
                            && !(shortestPaths.containsKey(getKey(newRow, newCol)))) {
                        addNewNode(newRow, newCol, node, shortestPaths, queue);
                    }
                }
            }
        }

        return shortestPaths;
    }

    public void addNewNode(int newRow, int newCol, Node oldNode, Map<Integer, Node> shortestPaths, Queue<Node> queue) {
        int key = getKey(newRow, newCol);
        boolean flag = shortestPaths.containsKey(key);
        Node newNode = shortestPaths.computeIfAbsent(key,
                k -> new Node(newRow, newCol, oldNode.dist + 1, getKey(oldNode.row, oldNode.col)));
        newNode.addChildNode(oldNode);
        if (!flag)
            queue.offer(newNode);
    }
}
