package com.assignment.suzume.path.shortest;

// A* algorithm in Java
import java.util.*;
import static com.assignment.suzume.utils.ExampleUtils.MapGetter.*;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.utils.ExampleUtils.*;

public class NewFinder extends ShortestPathFinder {
    Map<Integer, Node> shortestPaths;

    public NewFinder(int[][] grid) {
        super(grid);
        this.shortestPaths = new HashMap<>();
    }

    public Map<Integer, Node> findAllShortestPaths() {
        Queue<Node> queue = new LinkedList<>();

        int[][] recDirection = { { -1, 0 }, { 0, -1 } }; // recommended direction
        int[][] altDirection = { { 1, 0 }, { 0, 1 } }; // alternative direction

        queue.offer(new Node(numRows - 1, numCols - 1, 1, -1));

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
                    addNewNode(newRow, newCol, node, queue);
                }
            }

            if (!hasShortest) {
                for (int[] dir : altDirection) {
                    int newRow = node.row + dir[0];
                    int newCol = node.col + dir[1];

                    if (isValidLocation(newRow, newCol)
                            && !(shortestPaths.containsKey(getKey(newRow, newCol)))) {
                        addNewNode(newRow, newCol, node, queue);
                    }
                }
            }
        }

        return shortestPaths;
    }

    // public void getShortestPathAt(int row, int col) {
    //     preOrder(shortestPaths.get(getKey(row, col)), new StringBuilder());
    // }

    public void addNewNode(int newRow, int newCol, Node oldNode, Queue<Node> queue) {
        int key = getKey(newRow, newCol);
        boolean flag = shortestPaths.containsKey(key);
        Node newNode = shortestPaths.computeIfAbsent(key,
                k -> new Node(newRow, newCol, oldNode.dist + 1, getKey(oldNode.row, oldNode.col)));
        newNode.addChildNode(oldNode);
        if (!flag)
            queue.offer(newNode);
    }

    public int getKey(int row, int col) {
        return row * numCols + col;
    }

    public ArrayList<List<Node>> preOrder(Node node) {
        ArrayList<List<Node>> list = new ArrayList<>();
        LinkedList<Node> tmp = new LinkedList<>();
        preOrder(node, list, tmp);
        return list;
    }

    public void preOrder(Node node, ArrayList<List<Node>> list, LinkedList<Node> tmp) {
        if (node != null) {
            tmp.add(node);
            if (node.row == numRows - 1 && node.col == numCols - 1) {
                list.add(tmp);
            } else {
                LinkedList<Node> tmp2 = new LinkedList<>(tmp);
                preOrder(node.left, list, tmp);
                tmp = tmp2;
                preOrder(node.right, list, tmp);
            }
        }
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
        NewFinder finder = new NewFinder(getCombinedMap().getPixelMap());
        finder.findAllShortestPaths();
        // System.out.println(finder.shortestPaths.get(finder.getKey(1, 6)).dist);
        // System.out.println(finder.shortestPaths.get(finder.getKey(2, 5)).dist);

        // for(Map.Entry<Integer, Node> entry : finder.shortestPaths.entrySet()) {
        //     Node node = entry.getValue();
        //     System.out.println("Key: " + entry.getKey() + " Location : " + node.toString() + " Left : " + node.left + " Right : " + node.right);
        // }
        // System.out.println();
        // finder.getShortestPathAt(0, 0);

        // BFSFinder finder = new BFSFinder(getCombinedMap().getPixelMap());
        ArrayList<List<Node>> ls = finder.preOrder(finder.shortestPaths.get(finder.getKey(0, 0)));
        // System.out.println(ls.size());
        // for (List<Node> tmp : ls) {
        //     System.out.println(tmp.size());
        // }

        MapSimulator simulator = new MapSimulator();
        simulator.simulateUsingNode(getCombinedMap().getPixelMap(), ls.get(0));


    }
}
