package com.assignment.suzume.map.shortest;

import static com.assignment.suzume.map.utils.MapUtils.getCombinedMap;
import static com.assignment.suzume.map.utils.PathUtils.convertListVectorToName;

import java.util.*;
import com.assignment.suzume.map.PixelMap;

public class BFSFinder extends ShortestPathFinder {

    public BFSFinder(int[][] map) {
        super(map);
    }

    public BFSFinder(PixelMap pixelMap) {
        super(pixelMap);
    }

    @Override
    public List<List<String>> findAllShortestPaths() {
        return findAllShortestPaths(4);
    }

    public List<List<String>> findAllShortestPaths(int numOfStation) {
        List<List<int[]>> shortestPaths = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        BitSet visited = new BitSet();
        visited.set(getKey(0, 0));
        queue.offer(new Node(0, 0, 0, 0, visited, null));

        int shortestDistance = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.distance > shortestDistance) {
                continue;
            }

            if (current.numOfStation > numOfStation) {
                continue;
            }

            if (isDestination(current.row, current.col)) {
                if(current.numOfStation != numOfStation)
                    continue;

                if (current.distance < shortestDistance) {
                    shortestPaths = null; // Helps GC and thereby reduce memory usage
                    shortestPaths = new ArrayList<>();
                    shortestDistance = current.distance;
                }

                List<int[]> path = new ArrayList<>();

                for (Node node = current; node != null; node = node.parent) {
                    path.add(new int[] { node.row, node.col });
                }

                Collections.reverse(path);
                shortestPaths.add(path);
                continue;
            } 

            for (int i = 0; i < DIRECTIONS.length; i++) {
                int newRow = current.row + DIRECTIONS[i][0];
                int newCol = current.col + DIRECTIONS[i][1];
                int key = getKey(newRow, newCol);

                if (isValidLocation(newRow, newCol) && !current.visited.get(key)) {
                    BitSet newVisited = (BitSet) current.visited.clone();
                    newVisited.set(key);
                    queue.offer(new Node(newRow, newCol, current.distance + 1,
                            current.numOfStation + (isStation(newRow, newCol) ? 1 : 0), newVisited, current));
                }
            }
        }

        return convertListVectorToName(shortestPaths);
    }

    static class Node {
        int row;
        int col;
        int distance;
        int numOfStation;
        BitSet visited;
        Node parent;

        Node(int row, int col, int distance, int numOfStation, BitSet visited, Node parent) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.numOfStation = numOfStation;
            this.visited = visited;
            this.parent = parent;
        }
    }
}
