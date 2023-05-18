package com.assignment.suzume.map;

import java.util.*;
import com.assignment.suzume.path.searching.DFSFinder;

public class PixelMapProcessor {
    public PixelMap combinePixelMaps(List<PixelMap> pixelMaps, int rowSize, int colSize) {
        if(pixelMaps.size() != rowSize * colSize) {
            throw new IllegalArgumentException("The number of pixel maps must be equal to the product of numRows and numCols");
        } 

        if(!hasSameDimension(pixelMaps)) {
            throw new IllegalArgumentException("All pixel maps must have the same dimension");
        }

        int numMaps = pixelMaps.size();
        int numRows = pixelMaps.get(0).size()[0];
        int numCols = pixelMaps.get(0).size()[1];

        int[][] combinedPixelMap = new int[numRows * rowSize][numCols * colSize];

        for (int mapIndex = 0; mapIndex < numMaps; mapIndex++) {
            int rowOffset = mapIndex / colSize * numRows;
            int colOffset = mapIndex % colSize * numCols;
            int[][] pixelMap = pixelMaps.get(mapIndex).getPixelMap();
            for (int row = 0; row < numRows; row++) {
                System.arraycopy(pixelMap[row], 0, combinedPixelMap[row + rowOffset], colOffset, numCols);
            }
        }
    
        return new PixelMap(combinedPixelMap);
    }

    public PixelMap combinePixelMaps(int[][] sequences, List<PixelMap> pixelMaps) {
        int numRows = sequences.length;
        int numCols = sequences[0].length;

        Map<Integer, PixelMap> map = new HashMap<>();
        for(PixelMap pixels : pixelMaps) {
            DFSFinder finder = new DFSFinder(pixels.getPixelMap());
            map.put(finder.countPaths(), pixels);
        }

        List<PixelMap> list = new ArrayList<>();
        for(int row = 0; row < numRows; row++) {
            for(int col = 0; col < numCols; col++) {
                list.add(map.get(sequences[row][col]));
            }
        }

        return combinePixelMaps(list, numRows, numCols);
    }

    private Boolean hasSameDimension(List<PixelMap> pixelMaps) {
        for(int i = 0; i < pixelMaps.size() - 1; i++) {
            int[] prevMapSize = pixelMaps.get(i).size();
            int[] nextMapSize = pixelMaps.get(i + 1).size();
            if(prevMapSize[0] != nextMapSize[0] || prevMapSize[1] != nextMapSize[1]) {
                return false;
            }
        }
        return true;
    }
}
