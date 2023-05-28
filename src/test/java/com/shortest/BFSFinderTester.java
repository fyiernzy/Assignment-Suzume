package com.shortest;

import java.util.*;
import com.assignment.suzume.path.shortest.*;
import static com.assignment.suzume.utils.MapGetter.*;

public class BFSFinderTester {
    public static void main(String[] args) {
        BFSFinder finder = new BFSFinder(getCombinedMap().getPixelMap());
        long beforeUsedMem =Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        List<List<String>> directions = finder.findAllShortestPaths();
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        for (List<String> ls : directions) {
            System.out.println(ls + "\n");
        }
        System.out.println("Memory usage: " + actualMemUsed + " bytes");
        System.out.println(directions.size());
    }
}
