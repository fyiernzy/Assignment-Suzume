package com.assignment.suzume.connecting.game;

import java.util.*;
import java.util.concurrent.TimeUnit;
import com.assignment.suzume.connecting.account.data.DatabaseManager;
import com.assignment.suzume.connecting.account.User;
import com.assignment.suzume.map.MapVisualizer;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.map.utils.*;
import com.google.common.util.concurrent.Uninterruptibles;

public class ConsoleGame {
    private User user;
    private int[][] grid;
    private ConsoleGameStatus status;
    private ConsoleGameSetup setup;
    private MapVisualizer visualizer;
    private DatabaseManager dbManager;
    
    public ConsoleGame(PixelMap map) {
        this.user = User.getInstance();
        this.grid = map.getPixelMap();
        this.status = new ConsoleGameStatus(MapUtils.transformMap(grid));
        this.setup = new ConsoleGameSetup();
        this.visualizer = new MapVisualizer(status.currentMap);
        this.dbManager = DatabaseManager.getInstance();
    }

    public void play() {
        List<String> steps = PathUtils.getOneShortestPath();
        int totalStep = steps.size();

        System.out.println("Welcome to Suzume's journey!");
        System.out.println("Suzume is at the starting point (%d, %d).".formatted(status.currentRow, status.currentCol));
        ConsolePrinter.printRule(setup.getRule());

        visualizer.visualizeMap();
        System.out.println("Suzume is starting her journey.");
        sleepFor(2, TimeUnit.SECONDS);

        while (status.currentStep < totalStep && status.isContinueJourney) {
            status.updatePosition(steps.get(status.currentStep++));
            int currentRow = status.currentRow;
            int currentCol = status.currentCol;
            visualizer.visualizeMap();
            sleepFor(500, TimeUnit.MILLISECONDS);

            if (isStation(grid, currentRow, currentCol)) {
                System.out.println("Suzume has arrived at station (%d, %d).".formatted(currentRow, currentCol));
                System.out.println("You reached a game tile!");

                status.isContinueJourney = playStationGame();
            }

            if (isDestination(grid, currentRow, currentCol)) {
                System.out.println("Congratulations, you reached the end!");
                break;
            }
        }
    }

    boolean playStationGame() {
        int result = setup.getBoardGameRunner().play();

        if (result == GameConstant.LOSE) {
            System.out.println("Suzume has lost the station game.");
            if (status.stationHistory.isEmpty()) {
                System.out.println("Suzume has failed at the first station. Her journey ends.");
                return false;
            }

            System.out.println("Suzume falls back to the previous station.");
            status.backToPreviousStation();

        } else {
            System.out.println("Suzume has won the station game.");
            status.saveCurrentStation();
        }

        if (setup.getModeChoice() != GameConstant.EVE) {
            user.updateResult(result);
            dbManager.updateUserGameStatus(user);
        }

        return true;
    }

    void sleepFor(int time, TimeUnit unit) {
        Uninterruptibles.sleepUninterruptibly(time, unit);
    }    

    boolean isDestination(int[][] grid, int row, int col) {
        return grid[row][col] == 3;
    }

    boolean isStation(int[][] grid, int row, int col) {
        return grid[row][col] == 2;
    }
}
