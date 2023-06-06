package com.assignment.suzume.connecting.game;

import java.util.*;
import java.util.concurrent.TimeUnit;

import com.assignment.suzume.map.MapVisualizer;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.map.utils.*;
import com.google.common.util.concurrent.Uninterruptibles;

public class ConsoleGame {
    private GameStatus status;
    private MapVisualizer visualizer;
    private int[][] grid;

    public ConsoleGame(PixelMap map) {
        this.grid = map.getPixelMap();
        this.status = new GameStatus(MapUtils.transformMap(grid));
        this.visualizer = new MapVisualizer(status.currentMap);
    }

    public void play() {
        List<String> steps = PathUtils.getOneShortestPath();

        int totalStep = steps.size();

        visualizer.visualizeMap();
        System.out.println("Suzume is starting her journey.");
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        while (status.currentStep < totalStep) {
            status.updatePosition(steps.get(status.currentStep++));
            int currentRow = status.currentRow;
            int currentCol = status.currentCol;
            visualizer.visualizeMap();
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);

            if (isStation(grid, currentRow, currentCol)) {
                System.out.println("Suzume has arrived at station (%d, %d).".formatted(currentRow, currentCol));
                System.out.println("You reached a game tile!");

                StationGame stationGame = new StationGame();
                boolean isSuzumeWin = stationGame.play();

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
        }
    }


    boolean isDestination(int[][] grid, int row, int col) {
        return grid[row][col] == 3;
    }


    boolean isStation(int[][] grid, int row, int col) {
        return grid[row][col] == 2;
    }
}
