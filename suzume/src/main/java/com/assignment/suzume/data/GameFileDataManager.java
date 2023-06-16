package com.assignment.suzume.data;

import java.io.*;
import com.assignment.suzume.profile.User;
import com.assignment.suzume.game.BoardGameRunner;
import com.assignment.suzume.configuration.Configuration;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class GameFileDataManager {
    private static final String SAVE_GAME = "save_game";
    private static final String REPLAY = "replay";

    private static GameFileDataManager instance; // Singleton
    private static String parentFolderPathFormat = "%s" + File.separator + "%s" + File.separator
            + "%s" + File.separator + "%s";

    private GameFileDataManager() {
    }

    public static GameFileDataManager getInstance() {
        if (instance == null) {
            instance = new GameFileDataManager();
        }
        return instance;
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

    public void saveGame(String parentFolderPath, String filename, BoardGameRunner runner) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(parentFolderPath + File.separator + filename))) {
            out.writeObject(runner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BoardGameRunner loadGame(String parentFolderPath, String filename) {
        BoardGameRunner runner = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolderPath + File.separator + filename))) {
            runner = (BoardGameRunner) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return runner;
    }

    public void saveGameReplay(String parentFolderPath, String filename, GamingBoard board) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(parentFolderPath + File.separator + filename))) {
            out.writeObject(board);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GamingBoard loadGameReplay(String parentFolderPath, String filename) {
        GamingBoard board = null;
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(parentFolderPath + File.separator + filename))) {
            board = (GamingBoard) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return board;
    }
    
    public File[] getFileList(String parentFolder) {
        return new File(parentFolder).listFiles();
    }

    public String getSaveReplayParentFolderPath() {
        return getParentFolderPath(REPLAY, "");
    }

    public String getSaveGameParentFolderPath(int gameMode) {
        return getParentFolderPath(SAVE_GAME, getGameModeSubfolder(gameMode));
    }

    private String getParentFolderPath(String folder, String subfolder) {
        String username = User.getInstance().getName();
        String parentFolderPath = String.format(parentFolderPathFormat, Configuration.getGameFolderURL(), username, folder, subfolder);
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

    public void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
