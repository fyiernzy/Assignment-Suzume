package com.assignment.suzume.connecting.game;

import java.util.*;
import java.util.concurrent.TimeUnit;
import com.assignment.suzume.map.utils.*;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.map.MapVisualizer;
import com.assignment.suzume.connecting.account.User;
import com.assignment.suzume.connecting.account.data.*;
import com.google.common.util.concurrent.Uninterruptibles;

public class ConsoleGame {
    private User user;
    private int[][] grid;
    private Scanner scanner;
    private GameFileManager gameFileManager;
    private ConsoleGameStatus status;
    private ConsoleGameSetup setup;
    private MapVisualizer visualizer;
    private DatabaseManager dbManager;

    public ConsoleGame() {}

    public ConsoleGame(PixelMap map) throws InterruptedException {
        this.user = User.getInstance();
        this.grid = map.getPixelMap();
        this.gameFileManager = GameFileManager.getInstance();
        this.scanner = new Scanner(System.in);
        this.status = new ConsoleGameStatus(MapUtils.transformMap(grid));
        this.setup = new ConsoleGameSetup();
        this.visualizer = new MapVisualizer(status.currentMap);
        this.dbManager = DatabaseManager.getInstance();
    }

    public void play() throws InterruptedException {
        List<String> steps = PathUtils.getOneShortestPath();
        int totalStep = steps.size();

        System.out.println("Welcome to Suzume's journey!");
        System.out.println("Suzume is at the starting point (%d, %d).".formatted(status.currentRow, status.currentCol));
        ConsolePrinter.printRule(setup.getRule());

        System.out.println("                                                                                                                                         \u001B[33m    .-=-.        .-==-.");
        System.out.println("                                                                                                                                        \u001B[33m   /  .-. \\      / .-.  \\");
        System.out.println("\u001B[33m      GGGGGGGG         AAAAAAA       MMMM   MMMM   EEEEEEEEE    SSSSSSSSS    TTTTTTTTTT     AAAAAAAA       RRRRRRRR     TTTTTTTTTT  \u001B[33m       \\ /   \\ \\    / /   \\ /");
        System.out.println("\u001B[31m    GGGGGGGGGGGG      AAAAAAAAA      MMMMM MMMMM   EEEEEEEEE   SSSSSSSSSSS   TTTTTTTTTT    AAAAAAAAAA      RRRRRRRRRR   TTTTTTTTTT      \u001B[33m    /   \\_)    (_/   \\");
        System.out.println("\u001B[33m   GGGGG    GGGGG    AAA     AAA     MMMMMMMMMMM   EEE         SSSS             TTTT      AAA      AAA     RRR    RRR      TTTT         \u001B[33m   /   ~         ~    \\");
        System.out.println("\u001B[31m  GGGGG      GGGG   AAAA     AAAA    MMM MMM MMM   EEE         SSSSSSS          TTTT     AAAA      AAAA    RRR    RRR      TTTT         \u001B[33m  /   _____________    \\");
        System.out.println("\u001B[33m GGGGG              AAA       AAA    MMM  M  MMM   EEEEEEEE       SSSSSSSS      TTTT     AAA        AAA    RRRRRRRR        TTTT         \u001B[33m /   /              \\   \\");
        System.out.println("\u001B[31m GGGG               AAA       AAA    MMM     MMM   EEEEEEEE          SSSSSS     TTTT     AAA        AAA    RRR RRRR        TTTT         \u001B[33m/   /                \\   \\");
        System.out.println("\u001B[33m GGGG    GGGGGGGG   AAAAAAAAAAAAA    MMM     MMM   EEE                  SSSS    TTTT     AAAAAAAAAAAAAA    RRR  RRR        TTTT         \u001B[33m\\   \\                /   /");
        System.out.println("\u001B[31m  GGGGGGGGGGGGGG    AAAAAAAAAAAAA    MMM     MMM   EEE                  SSSS    TTTT     AAAAAAAAAAAAAA    RRR   RRR       TTTT         \u001B[33m \\   '.            .'   /");
        System.out.println("\u001B[33m   GGGGGGGGGGGG     AAA       AAA    MMM     MMM   EEEEEEEEE   SSSSSSSSSSSS     TTTT     AAA        AAA    RRR    RRR      TTTT         \u001B[33m  \\    `__________'    /");
        System.out.println("\u001B[31m     GGGGGGGGG      AAA       AAA    MMM     MMM   EEEEEEEEE    SSSSSSSSSS      TTTT     AAA        AAA    RRR     RRR     TTTT         \u001B[33m   '.                .'");
        System.out.println("                                                                                                                                        \u001B[33m     '-._________.-'");
        System.out.println();
        System.out.println("\033[1;31m               GAME START!\033[1;32m");

        visualizer.visualizeMap();
        System.out.println("\033[1;35mSuzume is starting her journey.\033[1;32m");
        sleepFor(2, TimeUnit.SECONDS);

        while (status.currentStep < totalStep && status.isContinueJourney) {
            status.updatePosition(steps.get(status.currentStep++));
            int currentRow = status.currentRow;
            int currentCol = status.currentCol;
            visualizer.visualizeMap();
            sleepFor(500, TimeUnit.MILLISECONDS);

            if (isStation(grid, currentRow, currentCol)) {
                System.out.println("\033[1;35mSuzume has arrived at station (%d, %d).".formatted(currentRow, currentCol));
                System.out.println("\033[1;35mYou reached a game tile!");

                int result = getBoardGameRunner().play();
                
                if(result == GameConstant.EXIT) {
                    System.out.println("\033[1;35mSuzume has chosen to exit the game.");
                    status.isContinueJourney = false;
                    break;
                }

                if (result == GameConstant.LOSE) {
                    System.out.println("\033[1;35mSuzume has lost the station game.");
                    if (status.stationHistory.isEmpty()) {
                        System.out.println("\033[1;35mSuzume has failed at the first station. Her journey ends.");
                        status.isContinueJourney = false;
                    }

                    System.out.println("\033[1;35mSuzume falls back to the previous station.");
                    status.backToPreviousStation();

                } else {
                    System.out.println("\033[1;35mSuzume has won the station game.");
                    status.saveCurrentStation();
                }

                if (setup.getModeChoice() != GameConstant.EVE) {
                    user.updateResult(result);
                    dbManager.updateUserGameStatus(user);
                }

                status.isContinueJourney = true;
            }

            if (isDestination(grid, currentRow, currentCol)) {
                System.out.println("\033[1;35mCongratulations, you reached the end!");
                break;
            }
        }
    }

    public BoardGameRunner getBoardGameRunner() {
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\033[1;35mDo you want to load the previous game or start a new one?");
            System.out.println(" --> [1] Load previous game");
            System.out.println(" --> [2] Start a new game");
            int choice = scanner.nextInt();

            scanner.nextLine();
            switch (choice) {
                case 1:
                    BoardGameRunner loadedGame = gameFileManager.loadGame(setup.getModeChoice());
                    if(loadedGame == null){
                       return getBoardGameRunner();
                    }
                    return loadedGame;
                case 2:
                    System.out.print("\033[1;32m");
                    return setup.getBoardGameRunner();
                default:
                    System.out.println("Invalid choice. Please try again.\n");
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

    public static void main(String[] args) {
        System.out.println("                                                                                                                                           \u001B[33m    .-=-.        .-==-.");
        System.out.println("                                                                                                                                          \u001B[33m   /  .-. \\      / .-.  \\");
        System.out.println("\u001B[33m        GGGGGGGG         AAAAAAA       MMMM   MMMM   EEEEEEEEE    SSSSSSSSS    TTTTTTTTTT     AAAAAAAA       RRRRRRRR     TTTTTTTTTT  \u001B[33m       \\ /   \\ \\    / /   \\ /");
        System.out.println("\u001B[31m      GGGGGGGGGGGG      AAAAAAAAA      MMMMM MMMMM   EEEEEEEEE   SSSSSSSSSSS   TTTTTTTTTT    AAAAAAAAAA      RRRRRRRRRR   TTTTTTTTTT      \u001B[33m    /   \\_)    (_/   \\");
        System.out.println("\u001B[33m     GGGGG    GGGGG    AAA     AAA     MMMMMMMMMMM   EEE         SSSS             TTTT      AAA      AAA     RRR    RRR      TTTT         \u001B[33m   /   ~         ~    \\");
        System.out.println("\u001B[31m    GGGGG      GGGG   AAAA     AAAA    MMM MMM MMM   EEE         SSSSSSS          TTTT     AAAA      AAAA    RRR    RRR      TTTT         \u001B[33m  /   _____________    \\");
        System.out.println("\u001B[33m   GGGGG              AAA       AAA    MMM  M  MMM   EEEEEEEE       SSSSSSSS      TTTT     AAA        AAA    RRRRRRRR        TTTT         \u001B[33m /   /              \\   \\");
        System.out.println("\u001B[31m   GGGG               AAA       AAA    MMM     MMM   EEEEEEEE          SSSSSS     TTTT     AAA        AAA    RRR RRRR        TTTT         \u001B[33m/   /                \\   \\");
        System.out.println("\u001B[33m   GGGG    GGGGGGGG   AAAAAAAAAAAAA    MMM     MMM   EEE                  SSSS    TTTT     AAAAAAAAAAAAAA    RRR  RRR        TTTT         \u001B[33m\\   \\                /   /");
        System.out.println("\u001B[31m    GGGGGGGGGGGGGG    AAAAAAAAAAAAA    MMM     MMM   EEE                  SSSS    TTTT     AAAAAAAAAAAAAA    RRR   RRR       TTTT         \u001B[33m \\   '.            .'   /");
        System.out.println("\u001B[33m     GGGGGGGGGGGG     AAA       AAA    MMM     MMM   EEEEEEEEE   SSSSSSSSSSSS     TTTT     AAA        AAA    RRR    RRR      TTTT         \u001B[33m  \\    `__________'    /");
        System.out.println("\u001B[31m       GGGGGGGGG      AAA       AAA    MMM     MMM   EEEEEEEEE    SSSSSSSSSS      TTTT     AAA        AAA    RRR     RRR     TTTT         \u001B[33m   '.                .'");
        System.out.println("                                                                                                                                          \u001B[33m     '-._________.-'");
        System.out.println();
        System.out.println("\u001B[33m                                       GAME START!\u001B[0m");


    }
}
