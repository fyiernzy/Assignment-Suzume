package Examples;

import PixelMap.*;
import java.util.*;

public class PixelMapExample {
    public static void main(String[] args) {
        PixelImageReader reader = new PixelImageReader();
        List<PixelMap> list = reader.readImages("Examples/Images", ".png");
        list.stream().forEach(map -> map.transform(64));
        
        PixelMapProcessor processor = new PixelMapProcessor();
        PixelMap combinedMap = processor.combinePixelMaps(new int[][] {{16, 41}, {38, 27}}, list);
        
        combinedMap.obstacles(map -> {
            final int numRow = map.size()[0] / 2;
            final int numCol = map.size()[1] / 2;
            int[][] grid = map.getPixelMap();

            for(int i = 0; i < 3; i++) {
                int targetRow = (i / 2 + 1) * numRow - 1;
                int targetCol = (i % 2 + 1) * numCol - 1;
                grid[targetRow][targetCol] = 1;
            }
            return map;
        });

        combinedMap.showPixelMap();
    }
}
