package com.assignment.suzume.connecting;

import java.util.*;
import com.assignment.suzume.path.shortest.DFSFinder;
import static com.assignment.suzume.utils.MapGetter.*;
import com.assignment.suzume.tictactoe.player.engine.EasyEngine;
import com.assignment.suzume.tictactoe.system.GameRunner;
import com.assignment.suzume.tictactoe.player.*;
import com.assignment.suzume.tictactoe.board.*;

public class ConsoleGameTry {
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
                Player Gamer = new Gamer("ys");  // Create a human player
                Player Engine = new EasyEngine();  // Create an engine player
                GamingBoard board = new VariantBoard();  // Create a gaming board
                GameRunner gameRunner = new GameRunner(Gamer, Engine, board);
                gameRunner.gamePlay();
            }

            if(grid[currentRow][currentCol] == 3) {
                System.out.println("Congratulations, you reached the end!");
                break;
            }

            step++;
        }
    }

    public static void main(String[] args) {
        ConsoleGameTry consoleGame = new ConsoleGameTry();
        consoleGame.play();
    }
}

