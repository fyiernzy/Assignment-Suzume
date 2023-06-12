package com.assignment.suzume.connecting.account.data;

import java.io.*;
import com.assignment.suzume.tictactoe.board.GamingBoard;
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

    public void saveGameReplay(String parentFolder, String filename, GamingBoard board) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(parentFolder + File.separator + filename))) {
            out.writeObject(board);
            System.out.println("Game Replay saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GamingBoard loadGameReplay(String parentFolder, String filename) {
        GamingBoard board = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolder + File.separator + filename))) {
            board = (GamingBoard) in.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return board;
    }
}