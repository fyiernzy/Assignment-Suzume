package PixelMap;

import java.util.*;
import java.util.function.*;

public class PixelMap {
    private int[][] pixelMap;

    public PixelMap(int[][] pixelMap) {
        this.pixelMap = pixelMap;
    }

    public PixelMap obstacles(Function<PixelMap, PixelMap> func) {
        func.apply(this);
        return this;
    }
    

    public PixelMap transform(int val) {
        Arrays.stream(this.pixelMap).forEach(row -> {
            for(int i = 0; i < row.length; i++) {
                row[i] /= val;
            }
        });
        return this;
    }

    public int[][] getPixelMap() {
        return this.pixelMap;
    }

    public void showPixelMap() {
        for(int[] row : this.pixelMap) {
            for(int col : row) {
                System.out.printf("%4d", col);
            }
            System.out.println();
        }  
    }

    public int[] size() {
        return new int[] {this.pixelMap.length, this.pixelMap[0].length};
    }
}
