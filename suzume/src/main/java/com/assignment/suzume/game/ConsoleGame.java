package com.assignment.suzume.game;

import java.util.*;
import java.util.concurrent.TimeUnit;
import com.assignment.suzume.utils.*;
import com.assignment.suzume.constants.*;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.profile.User;
import com.assignment.suzume.data.GameFileDataManager;
import com.assignment.suzume.database.DatabaseManager;
import com.google.common.util.concurrent.Uninterruptibles;

public class ConsoleGame {
    private User user;
    private int[][] grid;
    private GameFileDataManager gameFileDataManager;
    private ConsoleGameStatus status;
    private ConsoleGameSetup setup;
    private DatabaseManager dbManager;

    public ConsoleGame(PixelMap map) {
        this.user = User.getInstance();
        this.grid = map.getPixelMap();
        this.gameFileDataManager = GameFileDataManager.getInstance();
        this.status = new ConsoleGameStatus(MapUtils.transformMap(grid));
        this.setup = new ConsoleGameSetup();
        this.dbManager = DatabaseManager.getInstance();
    }

    public void play() {
        List<String> steps = PathUtils.getOneShortestPath();
        int totalStep = steps.size();

        System.out.println("Welcome to Suzume's journey!");
        System.out.println("Suzume is at the starting point (%d, %d).".formatted(status.currentRow, status.currentCol));
        ConsolePrinter.printRule(setup.getRule());

        MapUtils.printGrid(status.currentMap);

        System.out.println("Suzume is starting her journey.");
        sleepFor(2, TimeUnit.SECONDS);

        while (status.currentStep < totalStep && status.isContinueJourney) {
            status.updatePosition(steps.get(status.currentStep++));
            int currentRow = status.currentRow;
            int currentCol = status.currentCol;
            MapUtils.printGrid(status.currentMap);
            sleepFor(500, TimeUnit.MILLISECONDS);

            if (isStation(grid, currentRow, currentCol)) {
                System.out.println("Suzume has arrived at station (%d, %d).".formatted(currentRow, currentCol));
                System.out.println("You reached a game tile!");

                int result = getBoardGameRunner().play();
                
                if(result == GameConstant.EXIT) {
                    System.out.println("Suzume has chosen to exit the game.");
                    status.isContinueJourney = false;
                    break;
                }

                if (result == GameConstant.LOSE) {
                    System.out.println("Suzume has lost the station game.");
                    if (status.stationHistory.isEmpty()) {
                        System.out.println("Suzume has failed at the first station. Her journey ends.");
                        status.isContinueJourney = false;
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

                status.isContinueJourney = true;
            }

            if (isDestination(grid, currentRow, currentCol)) {
                System.out.println("Congratulations, you reached the end!");
                break;
            }
        }
    }

    BoardGameRunner getBoardGameRunner() {
        while (true) {
            System.out.println("Do you want to load the previous game or start a new one?");
            System.out.println(" --> [1] Load previous game");
            System.out.println(" --> [2] Start a new game");
            int choice = InputHandler.getIntInput();

            switch (choice) {
                case 1:
                    return gameFileDataManager.loadGame(setup.getModeChoice());
                case 2:
                    return setup.getBoardGameRunner();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
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
