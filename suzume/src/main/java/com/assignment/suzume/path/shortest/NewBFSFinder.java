package com.assignment.suzume.path.shortest;

import java.util.*;
import com.assignment.suzume.map.PixelMap;
import static com.assignment.suzume.utils.PathUtils.*;
import static com.assignment.suzume.utils.MapGetter.*;

public class NewBFSFinder extends ShortestPathFinder {

    public NewBFSFinder(int[][] map) {
        super(map);
    }

    public NewBFSFinder(PixelMap pixelMap) {
        super(pixelMap);
    }

    @Override
    public List<List<String>> findAllShortestPaths() {
        List<List<int[]>> shortestPaths = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        BitSet visited = new BitSet();
        visited.set(getKey(0, 0));
        queue.offer(new Node(0, 0, 0, visited, null));
        
        int shortestDistance = Integer.MAX_VALUE;

        
        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.distance > shortestDistance) {
                continue;
            }

            if (isDestination(current.row, current.col)) {
                if (current.distance < shortestDistance) {
                    shortestPaths = null; // Helps GC and thereby reduce memory usage
                    shortestPaths = new ArrayList<>();
                    shortestDistance = current.distance;
                }

                List<int[]> path = new ArrayList<>();

                for(Node node = current; node != null; node = node.parent) {
                    path.add(new int[] {node.row, node.col});
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
                    queue.offer(new Node(newRow, newCol, current.distance + 1, newVisited, current));
                }
            }
        }

        return posToDirections(shortestPaths);
    }

    public static void main(String[] args) {
        NewBFSFinder finder = new NewBFSFinder(getCombinedMap());
        
        List<List<String>> paths = finder.findAllShortestPaths();
        for(List<String> ls : paths) {
            System.out.println(ls.size());
        }
    }


    static class Node {
        int row;
        int col;
        int distance;
        BitSet visited;
        Node parent;

        Node(int row, int col, int distance, BitSet visited, Node parent) {
            this.row = row;
            this.col = col;
            this.distance = distance;
            this.visited = visited;
            this.parent = parent;
        }
    }
}
