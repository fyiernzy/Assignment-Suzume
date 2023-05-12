package Examples;

import PixelMap.*;
import java.util.*;
import static Examples.ExampleUtils.MapGetter.*;

public class PixelMapExample {
    public static void main(String[] args) {
        List<PixelMap> maps = getMapList();

        for(int i = 0; i < maps.size(); i++) {
            System.out.println("Map " + (i + 1) + ":");
            maps.get(i).showPixelMap();
            System.out.println();
        }
        
        PixelMap combinedMap = getCombinedMap();
        System.out.println("Combined Map:");
        combinedMap.showPixelMap();
    }
}
