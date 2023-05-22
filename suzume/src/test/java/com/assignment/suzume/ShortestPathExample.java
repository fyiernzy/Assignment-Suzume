package com.assignment.suzume;

import java.util.*;

import com.assignment.suzume.utils.*;
import com.assignment.suzume.map.*;
import com.assignment.suzume.path.shortest.*;

public class ShortestPathExample {
    public static void main(String[] args) {
        MBFSFinder finder = new MBFSFinder(new int[][] {{0, 1, 0}, {0, 1, 0}, {0, 0, 0}});
        finder.findAllShortestPaths();
        for(Map.Entry<Integer, Node> entry : finder.shortestPaths.entrySet()) {
            Node node = entry.getValue();
            System.out.println(node + " : " + node.dist);
        }
        // Map<Integer, Node> list = findAllShortestPaths(getMapList().get(0));
        // System.out.println(list.get(getKey(0, 0, getMapList().get(0).ge
        // grid()[0].length)).right);
        // preOrder(list.get(getKey(0, 0, getMapList().get(0).ge grid()[0].length)), new
        // StringBuilder());
        int[][] grid = { { 0, 0 }, { 0, 0 } };
        List<PixelMap> list = MapGetter.getMapList();
        //
        MBFSFinder finder = new MBFSFinder(MapGetter.getCombinedMap().getPixelMap());
        finder.findAllShortestPaths();
        // System.out.println(finder.shortestPaths.get(finder.getKey(1, 6)).dist);
        // System.out.println(finder.shortestPaths.get(finder.getKey(2, 5)).dist);

        // for(Map.Entry<Integer, Node> entry : finder.shortestPaths.entrySet()) {
        // Node node = entry.getValue();
        // System.out.println("Key: " + entry.getKey() + " Location : " +
        // node.toString() + " Left : " + node.left + " Right : " + node.right);
        // }
        // System.out.println();
        // finder.getShortestPathAt(0, 0);

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
