package com.assignment.suzume.data;

import java.io.*;
import java.util.Optional;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.game.BoardGameRunner;
import com.assignment.suzume.utils.InputHandler;

public class GameFileInputHandler {
    private static GameFileInputHandler instance; // Singleton
    GameFileDataManager manager;

    public GameFileInputHandler() {
        this.manager = GameFileDataManager.getInstance();
    }

    public static GameFileInputHandler getInstance() {
        if (instance == null) {
            instance = new GameFileInputHandler();
        }
        return instance;
    }

    public void handleSaveGame(BoardGameRunner runner, int gameMode) {
        handleSaveProcess(runner, gameMode, "Save Game");
    }

    public void handleSaveReplay(GamingBoard board) {
        handleSaveProcess(board, null, "Save Game Replay");
    }

    private void handleSaveProcess(Object object, Integer gameMode, String processName) {
        handleProcess(object, gameMode, processName, false);
    }

    public BoardGameRunner handleLoadGame(int gameMode) {
        return (BoardGameRunner) handleLoadProcess(gameMode, "Load Game");
    }

    public GamingBoard handleLoadReplay() {
        return (GamingBoard) handleLoadProcess(null, "Load Game Replay");
    }

    private Object handleLoadProcess(Integer gameMode, String processName) {
        return handleProcess(null, gameMode, processName, true);
    }

    private Object handleProcess(Object object, Integer gameMode, String processName, boolean isLoadProcess) {
        while (true) {
            String parentFolderPath = getParentFolderPath(gameMode, isLoadProcess);
            manager.createFolderIfNotExists(parentFolderPath);
            if(!showFileLists(parentFolderPath)) {
                return null;
            }
            String filename = getFileName(parentFolderPath, isLoadProcess);

            if (filename != null) {
                Optional<Object> obj = performSaveOrLoad(object, gameMode, isLoadProcess, parentFolderPath, filename);
                if(obj.isPresent()) {
                    return obj.get();
                }
            } else {
                if (confirmCancellation(processName)) {
                    System.out.println(processName + " is cancelled.");
                }
            }
            return null;
        }
    }

    private boolean showFileLists(String parentFolder) {
        return showFileLists(parentFolder, 5);
    }

    private boolean showFileLists(String parentFolder, int limit) {
        File[] files = manager.getFileList(parentFolder);
        if (files == null || files.length == 0) {
            System.out.println("No files found.");
            return false;
        } else {
            for (int i = 0; i < Math.min(files.length, limit); i++) {
                System.out.println(" --> [" + (i + 1) + "] " + files[i].getName());
            }
            return true;
        }
    }

    private String getParentFolderPath(Integer gameMode, boolean isLoadProcess) {
        return isLoadProcess ? getLoadParentFolderPath(gameMode) : manager.getSaveReplayParentFolderPath();
    }

    private String getLoadParentFolderPath(Integer gameMode) {
        return (gameMode != null) ? manager.getSaveGameParentFolderPath(gameMode)
                : manager.getSaveReplayParentFolderPath();
    }

    private String getFileName(String parentFolderPath, boolean isLoadProcess) {
        return isLoadProcess ? getLoadFileName(parentFolderPath) : getSaveFileName(parentFolderPath);
    }

    private Optional<Object> performSaveOrLoad(Object object, Integer gameMode, boolean isLoadProcess, String parentFolderPath,
            String filename) {
        if (isLoadProcess) {
            if (gameMode != null) {
                Object obj = manager.loadGame(parentFolderPath, filename);
                System.out.println("Game loaded successfully.");
                return Optional.of(obj);
            } else {
                Object obj = manager.loadGameReplay(parentFolderPath, filename);
                System.out.println("Game replay loaded successfully.");
                return Optional.of(obj);
            }
        } else {
            if (gameMode != null) {
                manager.saveGame(parentFolderPath, filename, (BoardGameRunner) object);
                System.out.println("Game saved successfully.");
            } else {
                manager.saveGameReplay(parentFolderPath, filename, (GamingBoard) object);
                System.out.println("Game Replay saved successfully.");
            }
            return Optional.empty();
        }
    }

    private boolean confirmCancellation(String processName) {
        System.out.println("Are you sure you want to quit the " + processName.toLowerCase() + " process? (y/n)");
        return InputHandler.getYesNoInput();
    }

    private String getSaveFileName(String parentFolderPath) {
        String fileName = null;

        while (true) {
            System.out.print("Enter the filename (enter ~q to quit): ");
            fileName = InputHandler.getStringInput();

            if (fileName.equals("~q")) {
                return null;
            }

            if (manager.isFileExist(parentFolderPath, fileName)) {
                System.out.println("File already exists. Do you want to overwrite it? (y/n)");
                if (!InputHandler.getYesNoInput()) {
                    continue;
                }
            }

            if (fileName.isBlank() || !manager.isFileNameValid(fileName)) {
                System.out.println("Invalid filename.");
                continue;
            }

            break;
        }

        return fileName;
    }

    private String getLoadFileName(String parentFolderPath) {
        String fileName = null;
        while (true) {
            System.out.print("Enter the filename (enter ~q to quit): ");
            fileName = InputHandler.getStringInput();

            if (fileName.equals("~q")) {
                return null;
            }

            if (fileName.isBlank() && !manager.isFileNameValid(fileName)) {
                System.out.println("Invalid filename.");
                continue;
            }

            if (!manager.isFileExist(parentFolderPath, fileName)) {
                System.out.println("File does not exist. Enter another file name. ");
                continue;
            }

            break;
        }
        return fileName;
    }
}
