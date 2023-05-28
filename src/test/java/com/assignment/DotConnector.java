package com.assignment;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.shortest.DFSFinder;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.shortest.draft.Node;
import com.assignment.suzume.utils.KeyProcessor;
import com.assignment.suzume.utils.MapGetter;
import com.assignment.suzume.utils.MapSimulator;
import java.util.ArrayList;
import java.util.List;
import static com.assignment.suzume.utils.PathUtils.*;
import java.util.*;


public class DotConnector {

    public static void main(String[] args) {
        // PixelMap map = MapGetter.getCombinedMap();
        PixelMap map = MapGetter.getMap(0);
        int[][] grid = map.getPixelMap();

        // Connect the dots
        for(List<int[]> directions : connectDots()) {
            for(int[] dirs : directions) {
                // if(grid[dirs[0]][dirs[1]]==2){
                System.out.println(Arrays.toString(dirs) + " = " + grid[dirs[0]][dirs[1]]);
            // }
            }
            System.out.println("\n");
        }
        System.out.println(connectDots().size());
    }

    public static List<List<int[]>> connectDots() {
        List<List<int[]>> connectedPaths = new ArrayList<>();
        // PixelMap map = MapGetter.getCombinedMap();
        PixelMap map = MapGetter.getMap(0);
        // Create an instance of DFSFinder with the map
        DFSFinder pathFinder = new DFSFinder(map);
    
        // Find all the shortest paths between dots
        List<List<String>> shortestPaths = pathFinder.findAllShortestPaths();
    
        // Convert the paths to string representations
        for (List<String> shortestPath : shortestPaths) {
            connectedPaths.add(directionToPos(shortestPath, new int[] {0, 0}));
        }
    
        return connectedPaths;
    }
}
    

