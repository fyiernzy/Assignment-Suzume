package com.assignment.suzume.connecting;

import java.util.*;
import com.assignment.suzume.path.shortest.DFSFinder;
import static com.assignment.suzume.utils.MapGetter.*;

public class ConsoleGame {
    // TODO: User can choose the direction that they want to move
    // TODO: User can let AI to walk for him
    // TODO: User can choose the type of game they would like to involve in
    // TODO: 

    public void play() {
        DFSFinder finder = new DFSFinder(getCombinedMap());
        int[][] grid = getCombinedMap().getPixelMap();
        List<String> steps = finder.findAllShortestPaths().get(0);
        int currentRow = 0;
        int currentCol = 0;

        int step = 0, totalStep = steps.size();
        while(step < totalStep) {
            switch(steps.get(step)) {
                case "Up" -> currentRow--;
                case "Down" -> currentRow++;
                case "Left" -> currentCol--;
                case "Right" -> currentCol++;
            }

            if(grid[currentRow][currentCol] == 2) {
                // * GameRunner will be called here
            }

            if(grid[currentRow][currentCol] == 3) {
                System.out.println("Congratulation!");
                break;
            }

            
        }
    }
}
