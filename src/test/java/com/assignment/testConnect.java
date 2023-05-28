package com.assignment;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.shortest.DFSFinder;
import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.shortest.draft.Node;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.tictactoe.board.VariantBoard;
import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.player.engine.HardEngine;
import com.assignment.suzume.tictactoe.system.GameRunner;
import com.assignment.suzume.utils.KeyProcessor;
import com.assignment.suzume.utils.MapGetter;
import com.assignment.suzume.utils.MapPrinter;
import com.assignment.suzume.utils.MapSimulator;
import java.util.ArrayList;
import java.util.List;

package com.assignment;

import com.assignment.suzume.map.PixelMap;
import com.assignment.suzume.path.shortest.DFSFinder;
import com.assignment.suzume.utils.MapGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class testConnect {

    public static void main(String[] args) {
        // Connect the dots and obtain the shortest path
        List<List<int[]>> shortestPath = connectDots();

        if (shortestPath.isEmpty()) {
            System.out.println("No shortest path found. Suzume's journey ends.");
            return;
        }

        System.out.println("Shortest Path:");
        for (int[] point : shortestPath) {
            System.out.println("(" + point[0] + ", " + point[1] + ")");
        }

        // Start the simulation system
        boolean journeyEnded = false;
        int currentStation = 0;

        while (!journeyEnded) {
            int[] station = shortestPath.get(currentStation);
            System.out.println("\nSuzume has arrived at station (" + station[0] + ", " + station[1] + ").");
            System.out.println("Playing the station game...");

            // Call the respective station game
            boolean gameResult = playStationGame();

            if (gameResult) {
                System.out.println("Congratulations! Suzume has won the station game.");

                if (currentStation == shortestPath.size() - 1) {
                    System.out.println("Suzume has successfully completed her journey with glory!");
                    journeyEnded = true;
                } else {
                    System.out.println("Suzume proceeds to the next station.");
                    currentStation++;
                }
            } else {
                System.out.println("Suzume has lost the station game.");

                if (currentStation == 0) {
                    System.out.println("Suzume has failed at the first station. Her journey ends.");
                    journeyEnded = true;
                } else {
                    System.out.println("Suzume falls back to the previous station.");
                    currentStation--;
                }
            }
        }
    }

    public static List<List<int[]>> connectDots() {
        List<List<int[]>> shortestPath = new ArrayList<>();
        PixelMap map = MapGetter.getCombinedMap();

        // Create an instance of DFSFinder with the map
        DFSFinder pathFinder = new DFSFinder(map);
    
        // Find all the shortest paths between dots
        List<List<String>> shortestPaths = pathFinder.findAllShortestPaths();
    
        return shortestPath;
    }

    public static boolean playStationGame() {
        Gamer one = new Gamer("Player 1");
        GamingBoard variant = new VariantBoard();
        HardEngine two = new HardEngine();
        GameRunner runner = new GameRunner(one, two, new VariantBoard());
        runner.gamePlay();
    }
}

