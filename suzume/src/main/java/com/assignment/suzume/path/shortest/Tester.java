package com.assignment.suzume.path.shortest;

import java.util.Map;

public class Tester {
    public static void main(String[] args) {
        MBFSFinder finder = new MBFSFinder(new int[][] {{0, 1, 0}, {0, 1, 0}, {0, 0, 0}});
        finder.findAllShortestPaths();
        for(Map.Entry<Integer, Node> entry : finder.shortestPaths.entrySet()) {
            Node node = entry.getValue();
            System.out.println(node + " : " + node.dist);
        }
    }
}
