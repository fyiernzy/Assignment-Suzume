package com.assignment.suzume.path.shortest;

// A* algorithm in Java
import java.util.*;

import com.assignment.suzume.utils.ExampleUtils.MapSimulator;
import com.assignment.suzume.map.PixelMap;
import static com.assignment.suzume.utils.ExampleUtils.MapGetter.*;

public class MBFSFinder {
    Map<Integer, Node> shortestPaths;
    int[][] grid;
    int numRows;
    int numCols;

    public MBFSFinder(int[][] grid) {
        this.grid = grid;
        this.numRows = grid.length;
        this.numCols = grid[0].length;
        this.shortestPaths = new HashMap<>();
    }

    public Map<Integer, Node> findAllShortestPaths() {
        Queue<Node> queue = new LinkedList<>();

        int numRow = grid.length;
        int numCol = grid[0].length;
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

    public void getShortestPathAt(int row, int col) {
        preOrder(shortestPaths.get(getKey(row, col)), new StringBuilder());
    }

    public void addNewNode(int newRow, int newCol, Node oldNode, Map<Integer, Node> shortestPaths, Queue<Node> queue) {
        int key = getKey(newRow, newCol);
        boolean flag = shortestPaths.containsKey(key);
        Node newNode = shortestPaths.computeIfAbsent(key, k -> new Node(newRow, newCol, oldNode.dist + 1, getKey(oldNode.row, oldNode.col)));
        newNode.addChildNode(oldNode);
        if(!flag)
            queue.offer(newNode);
    }

    public int getKey(int row, int col) {
        return row * numCols + col;
    }

    public boolean isValidLocation(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols && grid[row][col] != 1;
    }

    public static void main(String[] args) {
        // Map<Integer, Node> list = findAllShortestPaths(getMapList().get(0));
        // System.out.println(list.get(getKey(0, 0, getMapList().get(0).ge
        // grid()[0].length)).right);
        // preOrder(list.get(getKey(0, 0, getMapList().get(0).ge grid()[0].length)), new
        // StringBuilder());
        int[][] grid = { { 0, 0 }, { 0, 0 } };
        List<PixelMap> list = getMapList();
        // 
        MBFSFinder finder = new MBFSFinder(getCombinedMap().getPixelMap());
        finder.findAllShortestPaths();
        // System.out.println(finder.shortestPaths.get(finder.getKey(1, 6)).dist);
        // System.out.println(finder.shortestPaths.get(finder.getKey(2, 5)).dist);

        // for(Map.Entry<Integer, Node> entry : finder.shortestPaths.entrySet()) {
        //     Node node = entry.getValue();
        //     System.out.println("Key: " + entry.getKey() + " Location : " + node.toString() + " Left : " + node.left + " Right : " + node.right);
        // }
        // System.out.println();
        // finder.getShortestPathAt(0, 0);

        
    }

    public void preOrder(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.toString());
            if (node.row == numRows - 1 && node.col == numCols - 1) {
                System.out.println(sb.toString() + "\n");
            } else {
                sb.append(" --> ");
                StringBuilder tmp = new StringBuilder(sb);
                preOrder(node.left, sb);
                sb = tmp;
                preOrder(node.right, sb);

            }
        }
    }
}
