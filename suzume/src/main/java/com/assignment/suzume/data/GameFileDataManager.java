package com.assignment.suzume.data;

import java.io.*;
import com.assignment.suzume.profile.User;
import com.assignment.suzume.utils.InputHandler;
import com.assignment.suzume.game.BoardGameRunner;
import com.assignment.suzume.configuration.Configuration;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class GameFileDataManager {
    private static final String SAVE_GAME = "save_game";
    private static final String REPLAY = "replay";

    private static GameFileDataManager instance; // Singleton
    private String parentFolderPathFormat = "%s" + File.separator + "%s" + File.separator
            + "save_game" + File.separator + "%s";

    private GameFileDataManager() {
    }

    public static GameFileDataManager getInstance() {
        if (instance == null) {
            instance = new GameFileDataManager();
        }
        return instance;
    }

    public void saveGame(BoardGameRunner runner, int gameMode) {
        String parentFolderPath = getParentFolderPath(getGameModeSubfolder(gameMode));
        createFolderIfNotExists(parentFolderPath);
        String filename = getSaveFileName(parentFolderPath);
        saveGame(parentFolderPath, filename, runner);
    }

    public BoardGameRunner loadGame(int gameMode) {
        String parentFolderPath = getParentFolderPath(getGameModeSubfolder(gameMode));
        String filename = getSaveFileName(parentFolderPath);
        return loadGame(parentFolderPath, filename);
    }

    public void saveGameReplay(GamingBoard board) {
        String parentFolderPath = getParentFolderPath(REPLAY);
        createFolderIfNotExists(parentFolderPath);
        String filename = getSaveFileName(parentFolderPath);
        saveGameReplay(parentFolderPath, filename, board);
    }

    public GamingBoard loadGameReplay() {
        String parentFolderPath = getParentFolderPath(REPLAY);
        String filename = getLoadFileName(parentFolderPath);
        return loadGameReplay(parentFolderPath, filename);
    }

    public boolean isFolderEmpty(String parentFolderPath, String fileName) {
        File folder = new File(parentFolderPath + File.separator + fileName);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            return files != null && files.length == 0;
        }
        return false;
    }

    public boolean isFileExist(String parentFolderPath, String fileName) {
        File file = new File(parentFolderPath + File.separator + fileName);
        return file.exists();
    }

    public boolean isFileNameValid(String fileName) {
        return fileName.matches("[\\w-]+");
    }

    private void saveGame(String parentFolderPath, String filename, BoardGameRunner runner) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(parentFolderPath + File.separator + filename))) {
            out.writeObject(runner);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BoardGameRunner loadGame(String parentFolderPath, String filename) {
        BoardGameRunner runner = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolderPath + File.separator + filename))) {
            runner = (BoardGameRunner) in.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return runner;
    }

    private void saveGameReplay(String parentFolderPath, String filename, GamingBoard board) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(parentFolderPath + File.separator + filename))) {
            out.writeObject(board);
            System.out.println("Game Replay saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private GamingBoard loadGameReplay(String parentFolderPath, String filename) {
        GamingBoard board = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolderPath + File.separator + filename))) {
            board = (GamingBoard) in.readObject();
            System.out.println("Game loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return board;
    }

    public String getSaveFileName(String parentFolderPath) {
        String fileName = null;

        while (true) {
            System.out.print("Enter the filename: ");
            fileName = InputHandler.getStringInput();

            if (isFileExist(parentFolderPath, fileName)) {
                System.out.println("File already exists. Do you want to overwrite it? (y/n)");
                if (!InputHandler.getStringInput().equalsIgnoreCase("y")) {
                    continue;
                }
            }

            if (fileName.isBlank() || !isFileNameValid(fileName)) {
                System.out.println("Invalid filename.");
                continue;
            }

            break;
        }

        return fileName;
    }

    public String getLoadFileName(String parentFolderPath) {
        String fileName = null;
        while (true) {
            System.out.print("Enter the filename: ");
            fileName = InputHandler.getStringInput();

            if (!isFileExist(parentFolderPath, fileName)) {
                System.out.println("File does not exist. Enter another file name. ");
                continue;
            }

            if (fileName.isBlank() && !isFileNameValid(fileName)) {
                System.out.println("Invalid filename.");
                continue;
            }

            break;
        }
        return fileName;
    }

    private String getParentFolderPath(String subfolder) {
        String username = User.getInstance().getName();
        String parentFolderPath = String.format(parentFolderPathFormat, Configuration.getGameFolderURL(), username, subfolder);
        return parentFolderPath;
    }

    private String getGameModeSubfolder(int gameMode) {
        String subfolder = null;
        switch (gameMode) {
            case 1 -> subfolder = "pvp";
            case 2 -> subfolder = "pve";
            case 3 -> subfolder = "eve";
            default -> throw new IllegalArgumentException("Invalid mod choice.");
        }
        return SAVE_GAME + File.separator + subfolder;
    }

    private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
