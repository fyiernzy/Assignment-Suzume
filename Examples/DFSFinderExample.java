package Examples;

import PixelMap.*;
import PathUtils.Searching.*;
import java.util.*;

public class DFSFinderExample {
    public static void main(String[] args) {
        PixelImageReader reader = new PixelImageReader();
        List<PixelMap> list = reader.readImages("Examples/Images", ".png");
        list.stream().forEach(map -> map.transform(64));
        
        for(int i = 0; i < list.size(); i++) {
            DFSFinder finder = new DFSFinder(list.get(i).getPixelMap());
            System.out.println("Map = " + i + ", DFSFinder = " + finder.countPaths(3));
        }
    }
}
