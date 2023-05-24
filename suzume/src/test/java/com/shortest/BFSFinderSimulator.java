package com.shortest;

import com.assignment.suzume.utils.MapSimulator;
import com.assignment.suzume.path.shortest.BFSFinder;
import com.assignment.suzume.map.PixelMap;
import static com.assignment.suzume.utils.MapGetter.getCombinedMap;

public class BFSFinderSimulator {
    public static void main(String[] args) {
        MapSimulator simulator = new MapSimulator();
        PixelMap combinedMap = getCombinedMap();
        simulator.simulate(combinedMap.getPixelMap(), new BFSFinder(combinedMap).findAllShortestPaths().get(0));
    }
}
