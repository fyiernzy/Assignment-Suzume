package com.assignment.suzume.game;

import java.util.List;
import com.assignment.suzume.utils.*;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.profile.User;
import com.assignment.suzume.constants.GameConstant;
import com.assignment.suzume.data.GameFileInputHandler;
import com.assignment.suzume.database.DatabaseManager;

public class ConsoleGame {
    private final User user;
    private final ConsoleGameStatus status;
    private final ConsoleGameSetup setup;
    private final DatabaseManager dbManager;

    public ConsoleGame(PixelMap map) {
        this.user = User.getInstance();
        this.status = new ConsoleGameStatus(MapUtils.transformMap(map.getPixelMap()));
        this.setup = new ConsoleGameSetup();
        this.dbManager = DatabaseManager.getInstance();
    }

    public void play() {
        List<String> steps = PathUtils.getOneShortestPath();
        int totalStep = steps.size();

        printWelcomeMessage();
        ConsolePrinter.printMascot();
        printStartingPoint();
        MapUtils.printGrid(status.getCurrentMap());

        System.out.println("Suzume is starting her journey.");
        Timer.waitInSecond(2);

        while (status.getCurrentStep() < totalStep && status.isContinueJourney()) {
            handleNextStep(steps);
            if (status.isCurrentPositionDestination()) {
                System.out.println("Congratulations, you reached the end!");
                break;
            }
        }
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Suzume's journey!");
    }

    private void printStartingPoint() {
        System.out.printf("Suzume is at the starting point (%d, %d).\n", status.getCurrentRow(),
                status.getCurrentCol());
    }

    private void handleNextStep(List<String> steps) {
        status.updatePosition(steps.get(status.getCurrentStep()));
        MapUtils.printGrid(status.getCurrentMap());
        Timer.waitInMilliseconds(500);

        if (status.isCurrentPositionStation()) {
            handleStation();
        }
    }

    private void handleStation() {
        System.out.printf("Suzume has arrived at station (%d, %d).\n", status.getCurrentRow(), status.getCurrentCol());
        System.out.println("You reached a game tile!");

        int result = getBoardGameRunner().play();

        if (result == GameConstant.EXIT) {
            System.out.println("Suzume has chosen to exit the game.");
            status.setContinueJourney(false);
            return;
        }

        if (setup.getModeChoice() != GameConstant.EVE) {
            user.updateResult(result);
            dbManager.updateUserGameStatus(user);
        }

        if (result == GameConstant.LOSE) {
            handleBoardGameLoss();
            return ;
        } else if (result == GameConstant.WIN) {
            handleBoardGameWin();
        } else if (result == GameConstant.DRAW) {
            System.out.println("It's a tie!");
        }

        boolean isContinueJourney = promptForGameContinuationChoice();
        status.setContinueJourney(isContinueJourney);
    }

    private void handleBoardGameLoss() {
        System.out.println("Alas! Suzume has been defeated in the station game.");        

        boolean isBackToPreviousStation = status.backToPreviousStation();
        if (!isBackToPreviousStation) {
            System.out.println(
                    "Oh no! Suzume has failed at the very first station. Her journey comes to an unfortunate end.");
            status.setContinueJourney(false);
        } else {
            System.out.println("But fear not! Suzume bravely falls back to the previous station.");
        }
    }

    private void handleBoardGameWin() {
        System.out.println("Hooray! Suzume emerges victorious in the station game.");
        status.saveCurrentStation();
    }

    private BoardGameRunner getBoardGameRunner() {
        while (true) {
            System.out.println("Do you want to load the previous game or start a new one?");
            System.out.println(" --> [1] Load previous game");
            System.out.println(" --> [2] Start a new game");

            int choice = InputHandler.getIntInput();
            switch (choice) {
                case 1:
                    BoardGameRunner runner = GameFileInputHandler.getInstance().handleLoadGame(setup.getModeChoice());
                    return runner;
                case 2:
                    return setup.getBoardGameRunner();
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private boolean promptForGameContinuationChoice() {
        while (true) {
            System.out.println("Would you like to continue the game?");
            System.out.println("  [1] Yes, let's continue!");
            System.out.println("  [2] No, I want to exit the game.");

            int choice = InputHandler.getIntInput();
            switch (choice) {
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("Oops! Invalid input. Please try again...");
            }
        }
    }
}
