package com.assignment.suzume.path.shortest.draft;

import java.util.*;
import java.util.stream.Collectors;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.shortest.ShortestPathFinder;

import static com.assignment.suzume.utils.PathUtils.*;
import static com.assignment.suzume.utils.MapGetter.*;

public class DFS2Finder extends ShortestPathFinder {
    private static int[][] DIRS = { { 1, 0 }, { 0, 1 }, { 0, -1 }, { -1, 0 } };
    private int shortestDistance = Integer.MAX_VALUE;
    private List<List<int[]>> shortestPaths = new ArrayList<List<int[]>>();

    public static void main(String[] args) {
        DFS2Finder dfs2Finder = new DFS2Finder(getCombinedMap());
        List<List<String>> paths = dfs2Finder.findAllShortestPaths();
        System.out.println(paths.size());
        for (List<String> path : paths) {
            System.out.println(path);
        }
    }

    public DFS2Finder(int[][] map) {
        super(map);
    }

    public DFS2Finder(PixelMap pixelMap) {
        super(pixelMap);
    }

    @Override
    public List<List<String>> findAllShortestPaths() {
        pathFinderHelper(0, 0, new Node(0, 0, null), new HashSet<>());
        return posToDirections(shortestPaths);
    }

    private void pathFinderHelper(int row, int col, Node currentNode, Set<Integer> visited) {
        if (!isValidLocation(row, col) || visited.contains(getKey(row, col))) {
            return;
        }

        if (currentNode.dist > shortestDistance) {
            return;
        }

        currentNode.next = new Node(getKey(row, col), currentNode.dist + 1, currentNode);
        currentNode = currentNode.next;

        if (isDestination(row, col)) {
            if (currentNode.dist < shortestDistance) {
                shortestDistance = currentNode.dist;
                shortestPaths.clear();
            }

            if (currentNode.dist == shortestDistance) {
                shortestPaths.add(getPath(currentNode));
            }

            return;
        }

        visited.add(getKey(row, col));
        for (int[] dir : DIRS) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            pathFinderHelper(newRow, newCol, currentNode, visited);
        }
        visited.remove(getKey(row, col));
        currentNode = currentNode.prev;
    }

    private List<int[]> getPath(Node node) {
        Stack<int[]> path = new Stack<>();
        for(; node != null; node = node.prev) {
            path.push(new int[] { node.val / numCols, node.val % numCols}); 
        }
        return path.stream().collect(Collectors.toList());
    }

    static class Node {
        int val;
        int dist;
        Node next;
        Node prev;
        
        Node(int val, int dist, Node prev) {
            this.val = val;
            this.dist = dist;
            this.prev = prev;
        }
    }
}
