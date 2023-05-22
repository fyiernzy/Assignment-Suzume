package com.assignment.suzume.path.shortest;

import java.util.*;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.utils.map.MapSimulator;
import static com.assignment.suzume.utils.map.MapGetter.*;

public class BFSFinder extends ShortestPathFinder {

    public BFSFinder(int[][] map) {
        super(map);
    }

    public BFSFinder(PixelMap pixelMap) {
        super(pixelMap);
    }

    public List<List<String>> findAllShortestPaths() {
        List<List<String>> shortestPaths = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        HashSet<String> initialVisited = new HashSet<>();
        initialVisited.add("0,0");
        queue.offer(new Node(0, 0, 0, initialVisited, null));

        int shortestDistance = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.distance > shortestDistance) {
                continue;
            }

            if (isDestination(current.row, current.col)) {
                if (current.distance < shortestDistance) {
                    shortestPaths.clear();
                    shortestDistance = current.distance;
                }

                List<String> path = new ArrayList<>();
                Node node = current;

                while (node.parent != null) {
                    int dirRow = node.row - node.parent.row;
                    int dirCol = node.col - node.parent.col;
                    path.add(getDirectionName(dirRow, dirCol));
                    node = node.parent;
                }

                Collections.reverse(path);
                shortestPaths.add(path);
                continue;
            }

            for (int i = 0; i < DIRECTIONS.length; i++) {
                int newRow = current.row + DIRECTIONS[i][0];
                int newCol = current.col + DIRECTIONS[i][1];
                String key = newRow + "," + newCol;

                if (isValidLocation(newRow, newCol) && !current.visited.contains(key)) {
                    HashSet<String> newVisited = new HashSet<>(current.visited);
                    newVisited.add(key);
                    queue.offer(new Node(newRow, newCol, current.distance + 1, newVisited, current));
                }
            }
        }
        return shortestPaths;
    }

    static class Node {
        int row;
        int col;
        int distance;
        HashSet<String> visited;
        Node parent;

        Node(int row, int col, int distance, HashSet<String> visited, Node parent) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.visited = visited;
            this.parent = parent;
        }
    }

    public static void main(String[] args) {
        BFSFinder finder = new BFSFinder(getCombinedMap().getPixelMap());
        List<List<String>> list = finder.findAllShortestPaths();
        // System.out.println(list.size());
        // for (List<String> ls : list) {
        //     System.out.println(ls + "\n");
        // }
        MapSimulator simulator = new MapSimulator();
        simulator.simulate(getCombinedMap().getPixelMap(), list.get(0));
    }
}
