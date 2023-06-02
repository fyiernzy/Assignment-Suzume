package com.assignment.suzume.connecting;

import java.util.*;
import java.util.concurrent.TimeUnit;
import com.assignment.suzume.map.PixelMap;
import com.google.common.util.concurrent.Uninterruptibles;
import static com.assignment.suzume.utils.MapGetter.*;
import static com.assignment.suzume.utils.PathUtils.*;


public class ConsoleGame {
    private GameStatus status;
    private int[][] grid;

    public ConsoleGame(int[][] grid) {
        this.grid = grid;
        this.status = new GameStatus(MapUtils.transformMap(grid));
    }

    public ConsoleGame(PixelMap map) {
        this(map.getPixelMap());
    }

    public void play() {
        List<String> steps = getOneShortestPath();

        int totalStep = steps.size();

        MapUtils.visualizeMap(status.currentMap);
        System.out.println("Suzume is starting her journey.");
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        while (status.currentStep < totalStep) {
            status.updatePosition(steps.get(status.currentStep++));
            int currentRow = status.currentRow;
            int currentCol = status.currentCol;
            MapUtils.visualizeMap(status.currentMap);
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);

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
