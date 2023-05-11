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
        BFSFinder finder = new BFSFinder(map1.getPixelMap());
        System.out.println("BFSFinder = " + finder.countPaths(3));
    }
}
