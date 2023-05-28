package com.assignment.suzume.connecting;

import java.util.*;
import com.assignment.suzume.path.shortest.DFSFinder;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.tictactoe.board.VariantBoard;
import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.player.engine.HardEngine;
import com.assignment.suzume.tictactoe.system.GameRunner2;



import static com.assignment.suzume.utils.MapGetter.*;

public class ConsoleGame2 {
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
        while (step < totalStep) {
            switch (steps.get(step)) {
                case "Up" -> currentRow--;
                case "Down" -> currentRow++;
                case "Left" -> currentCol--;
                case "Right" -> currentCol++;
            }

            if (grid[currentRow][currentCol] == 2) {
                //keep the station coordinate
                System.out.println("Suzume has arrived at station (" + currentRow + ", " + currentCol + ").");
                int stationRow=-1;
                int stationColumn=-1;
                // * GameRunner will be called here
                Gamer one = new Gamer("Player 1");
                HardEngine two = new HardEngine();
                GameRunner2 game = new GameRunner2(one, two, new VariantBoard());

                game.gamePlay();
                boolean gameResult = game.OneWin(); // Call the station game

                if (!gameResult) {
                    System.out.println("Suzume has lost the station game.");
                    if (step == 0) {
                        System.out.println("Suzume has failed at the first station. Her journey ends.");
                        return;
                    } else {
                        System.out.println("Suzume falls back to the previous station.");
                        step--;
                        currentRow = stationRow;
                        currentCol = stationColumn;
                    }
                }
                else{
                stationRow=currentRow;
                stationColumn =currentCol;}

            }

            if (grid[currentRow][currentCol] == 3) {
                System.out.println("Congratulation!");
                break;
            }

        }
    }
}
