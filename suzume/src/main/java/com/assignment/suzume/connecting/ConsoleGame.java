package com.assignment.suzume.connecting;

import java.util.*;
import com.assignment.suzume.map.PixelMap;
import static com.assignment.suzume.utils.MapGetter.*;
import static com.assignment.suzume.utils.PathUtils.*;

public class ConsoleGame {
    private GameStatus status;
    private int[][] grid;

    ConsoleGame(int[][] grid) {
        this.grid = grid;
        this.status = new GameStatus(MapUtils.transformMap(grid));
        
    }

    ConsoleGame(PixelMap map) {
        this(map.getPixelMap());
    }

    public void play() {
        List<String> steps = getOneShortestPath();

        int totalStep = steps.size();

        while (status.currentStep < totalStep) {
            int currentRow = status.currentRow;
            int currentCol = status.currentCol;
            int currentStep = status.currentStep;

            status.updatePosition(steps.get(currentStep));
            MapUtils.visualizeMap(status.currentMap, currentStep);

            if (isStation(grid, currentRow, currentCol)) {
                System.out.println("Suzume has arrived at station (%d, %d).".formatted(currentRow, currentCol));
                System.out.println("You reached a game tile!");

                StationGame stationGame = new StationGame();
                stationGame.play();

                boolean isSuzumeWin = stationGame.isSuzumeWin();

                if (!isSuzumeWin) {
                    System.out.println("Suzume has lost the station game.");
                    if (status.stationHistory.isEmpty()) {
                        System.out.println("Suzume has failed at the first station. Her journey ends.");
                        return;
                    }

                    System.out.println("Suzume falls back to the previous station.");
                    status.backToPreviousStation();

                } else {
                    System.out.println("Suzume has won the station game.");
                    status.saveCurrentStation();
                }
            }

            if (isDestination(grid, currentRow, currentCol)) {
                System.out.println("Congratulations, you reached the end!");
                break;
            }

            status.currentStep++;
        }
    }


    boolean isDestination(int[][] grid, int row, int col) {
        return grid[row][col] == 3;
    }


    boolean isStation(int[][] grid, int row, int col) {
        return grid[row][col] == 2;
    }

    public static void main(String[] args) {
        ConsoleGame consoleGame = new ConsoleGame(getCombinedMap());
        consoleGame.play();
    }

}
