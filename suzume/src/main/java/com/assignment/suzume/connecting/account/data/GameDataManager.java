package com.assignment.suzume.connecting.account.data;

import java.io.*;
import com.assignment.suzume.tictactoe.player.Gamer;
import com.assignment.suzume.tictactoe.board.VariantBoard;
import com.assignment.suzume.tictactoe.board.rules.Rule;
import com.assignment.suzume.connecting.game.analyzer.VariantGameAnalyzer;
import com.assignment.suzume.connecting.game.BoardGameRunner;

public class GameDataManager {
    private static GameDataManager instance; // Singleton

    public static GameDataManager getInstance() {
        if (instance == null) {
            instance = new GameDataManager();
        }
        return instance;
    }

    public void saveGame(String parentFolder, String filename, BoardGameRunner runner) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(parentFolder + File.separator + filename))) {
            out.writeObject(runner);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BoardGameRunner loadGame(String parentFolder, String filename) {
        BoardGameRunner runner = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolder + File.separator + filename))) {
            runner = (BoardGameRunner) in.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return runner;
    }

    public static void main(String[] args) {
        GameDataManager gameDataManager = GameDataManager.getInstance();
        VariantBoard board = new VariantBoard();
        BoardGameRunner runner = new BoardGameRunner(1, new Gamer("Ng", 'X'), new Gamer("test", 'O'),
                Rule.VARIANT, board, new VariantGameAnalyzer('X', 'O', board));
        gameDataManager.saveGame("C:\\Users\\User\\Desktop\\suzume\\Ng\\save_game\\pvp", "game_1", runner);
        BoardGameRunner runner2 = gameDataManager.loadGame("C:\\Users\\User\\Desktop\\suzume\\Ng\\save_game\\pvp", "game_1");
        System.out.println(runner2);
    }
}
