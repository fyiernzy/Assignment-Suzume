package com.assignment.suzume.utils;

import com.assignment.suzume.map.*;
import java.util.*;

public class MapGetter {
    private static final String IMAGE_PATH = "./src/main/java/com/assignment/suzume/utils/Images/";
    private static final String IMAGE_SUFFIX = ".png";
    private static final int[][] NUM_OF_PATH = { { 16, 41 }, { 38, 27 } };
    private static List<PixelMap> list = null;

    public static PixelMap getMap(int index) {
        return getMapList().get(index);
    }

    public static List<PixelMap> getMapList() {
        if(list == null) {
            PixelImageReader reader = new PixelImageReader();
            list = reader.readImages(IMAGE_PATH, IMAGE_SUFFIX);
            list.stream().forEach(map -> map.transform(64));
        }
        return list;
    }

    public static PixelMap getCombinedMap() {
        List<PixelMap> list = getMapList();

        PixelMapProcessor processor = new PixelMapProcessor();
        PixelMap combinedMap = processor.combinePixelMaps(NUM_OF_PATH, list);

        combinedMap.obstacles(map -> {
            final int numRow = map.size()[0] / 2;
            final int numCol = map.size()[1] / 2;
            int[][] grid = map.getPixelMap();

            for (int i = 0; i < 3; i++) {
                int targetRow = (i / 2 + 1) * numRow - 1;
                int targetCol = (i % 2 + 1) * numCol - 1;
                grid[targetRow][targetCol] = 1;
            }
            return map;
        });

        return combinedMap;
    }

    public static void main(String[] args) {
        getCombinedMap();
    }
}
