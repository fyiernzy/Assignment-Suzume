package Examples;

import PixelMap.*;
import PathUtils.Searching.*;
import java.util.*;
import static Examples.ExampleUtils.MapGetter.*;

public class SearchingExample {
    public static void main(String[] args) {
        List<PixelMap> maps = getMapList();
        testFinder(DFSFinder.class, maps);
        testFinder(BFSFinder.class, maps);

        PixelMap map = getCombinedMap();
        System.out.println("DFSFinder: " + new DFSFinder(map).countPaths(4));
    }
    
    private static void testFinder(Class<? extends PathNumberFinder> cls, List<PixelMap> maps) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < maps.size(); i++) {
            PixelMap map = maps.get(i);
            try {
                PathNumberFinder finder = cls.getDeclaredConstructor(PixelMap.class).newInstance(map);
                int count = finder.countPaths(3);
                System.out.printf("Map %d: %s = %d\n", i, cls.getSimpleName(), count);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time used: " + (endTime - startTime) + "ms\n");
    }
    
}
