package Examples;

import PixelMap.*;
import PathUtils.Searching.*;
import java.util.*;

public class BFSFinderExample {
    public static void main(String[] args) {
        PixelImageReader reader = new PixelImageReader();
        List<PixelMap> list = reader.readImages("Examples/Images", ".png");
        list.stream().forEach(map -> map.transform(64));

        for(int i = 0; i < list.size(); i++) {
            BFSFinder finder = new BFSFinder(list.get(i).getPixelMap());
            System.out.println("Map = " + i + ", BFSFinder = " + finder.countPaths(3));
        }
        
    }
}
