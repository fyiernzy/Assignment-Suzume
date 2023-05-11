package Examples;

import PixelMap.*;
import PathUtils.Searching.*;
import java.util.*;

public class BFSFinderExample {
    public static void main(String[] args) {
        PixelImageReader reader = new PixelImageReader();
        List<PixelMap> list = reader.readImages("Examples/Images", ".png");
        list.stream().forEach(map -> map.transform(64));

        PixelMap map1 = list.get(0);
        int[][] grid = map1.getPixelMap();
        // map1.showPixelMap();
        // int[][] grid = {{0, 0, 0}, {0, 1, 0}, {0, 2, 3}};
        System.out.print(" ".repeat(4));
        for(int i = 0; i < grid[0].length; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();

        for(int i = 0; i < grid.length; i++) {
            System.out.printf(" %-3d", i);
            for(int j = 0; j < grid[0].length;j++) {
                System.out.printf("%4d", grid[i][j]);
            }
            System.out.println();
        }
        BFSFinder finder = new BFSFinder(map1.getPixelMap());
        System.out.println(finder.countPaths(1));
    }
}
