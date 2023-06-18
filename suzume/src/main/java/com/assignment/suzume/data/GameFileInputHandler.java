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

    /**
     * Handles the saving of the game data.
     *
     * @param runner   The BoardGameRunner instance representing the current game
     *                 state.
     * @param gameMode The game mode.
     */
    public void handleSaveGame(BoardGameRunner runner, int gameMode) {
        handleSaveProcess(runner, gameMode, "Save Game");
    }

    /**
     * Handles the saving of the game replay data.
     *
     * @param board The GamingBoard instance representing the game board.
     */
    public void handleSaveReplay(GamingBoard board) {
        handleSaveProcess(board, null, "Save Game Replay");
    }

    /**
     * Handles the loading of the game data.
     *
     * @param gameMode The game mode.
     * @return An Optional containing the loaded BoardGameRunner instance if
     *         successful, or an empty Optional otherwise.
     */
    public Optional<BoardGameRunner> handleLoadGame(int gameMode) {
        Optional<Object> obj = handleLoadProcess(gameMode, "Load Game");
        return obj.isPresent() ? Optional.of((BoardGameRunner) obj.get()) : Optional.empty();
    }

    /**
     * Handles the loading of the game replay data.
     *
     * @return An Optional containing the loaded GamingBoard instance if successful,
     *         or an empty Optional otherwise.
     */
    public Optional<GamingBoard> handleLoadReplay() {
        Optional<Object> obj = handleLoadProcess(null, "Load Game Replay");
        return obj.isPresent() ? Optional.of((GamingBoard) obj.get()) : Optional.empty();
    }

    /**
     * Handles the process of loading a game.
     *
     * @param gameMode    The game mode.
     * @param processName The name of the loading process.
     * @return An Optional containing the loaded object if successful, or an empty
     *         Optional otherwise.
     */
    private Optional<Object> handleLoadProcess(Integer gameMode, String processName) {
        String parentFolderPath = getParentFolderPath(gameMode);
        manager.createFolderIfNotExists(parentFolderPath);

        if (!computeIfFileExists(parentFolderPath)) {
            return Optional.empty();
        }

        while (true) {
            Optional<String> filename = getLoadFileName(parentFolderPath);

            if (filename.isPresent()) {
                Optional<Object> obj = performLoad(gameMode, parentFolderPath, filename.get());
                return obj;
            }

            if (confirmCancellation(processName)) {
                System.out.println(processName + " is cancelled.");
                return Optional.empty();
            }
        }
    }

    /**
     * Handles the saving process for game data.
     *
     * @param object      The object to be saved (either BoardGameRunner or
     *                    GamingBoard).
     * @param gameMode    The game mode.
     * @param processName The name of the save process.
     * @return An Optional containing the saved object if successful, or an empty
     *         Optional otherwise.
     */
    private Optional<Object> handleSaveProcess(Object object, Integer gameMode, String processName) {
        String parentFolderPath = getParentFolderPath(gameMode);
        manager.createFolderIfNotExists(parentFolderPath);

        while (true) {
            Optional<String> filename = getSaveFileName(parentFolderPath);

            if (filename.isPresent()) {
                Optional<Object> obj = performSave(object, gameMode, parentFolderPath, filename.get());
                return obj;
            }

            if (confirmCancellation(processName)) {
                System.out.println(processName + " is cancelled.");
                return Optional.empty();
            }
        }
    }

    /**
     * Checks if files exist in the specified parent folder.
     *
     * @param parentFolder The parent folder path.
     * @return True if files exist, false otherwise.
     */
    private boolean computeIfFileExists(String parentFolder) {
        return computeIfFileExists(parentFolder, 5);
    }

    /**
     * Checks if files exist in the specified parent folder, up to the specified
     * limit.
     *
     * @param parentFolder The parent folder path.
     * @param limit        The maximum number of files to display.
     * @return True if files exist, false otherwise.
     */
    private boolean computeIfFileExists(String parentFolder, int limit) {
        File[] files = manager.getFileList(parentFolder);
        if (files == null || files.length == 0) {
            System.out.println("No files found.\n");
            return false;
        } else {
            for (int i = 0; i < Math.min(files.length, limit); i++) {
                System.out.println(" --> [" + (i + 1) + "] " + files[i].getName());
            }
            return true;
        }
    }

    /**
     * Retrieves the parent folder path based on the game mode.
     *
     * @param gameMode The game mode.
     * @return The parent folder path.
     */
    private String getParentFolderPath(Integer gameMode) {
        return (gameMode != null)
                ? manager.getSaveGameParentFolderPath(gameMode)
                : manager.getSaveReplayParentFolderPath();
    }

    /**
     * Performs the load process for game data.
     *
     * @param gameMode         The game mode.
     * @param parentFolderPath The parent folder path.
     * @param filename         The name of the file to load.
     * @return An Optional containing the loaded object if successful, or an empty
     *         Optional otherwise.
     */
    private Optional<Object> performLoad(Integer gameMode, String parentFolderPath, String filename) {
        if (gameMode != null) {
            Object obj = manager.loadGame(parentFolderPath, filename);
            System.out.println("Game loaded successfully.");
            return Optional.ofNullable(obj);
        } else {
            Object obj = manager.loadGameReplay(parentFolderPath, filename);
            System.out.println("Game Replay loaded successfully.");
            return Optional.ofNullable(obj);
        }
    }

    /**
     * Performs the save process for game data.
     *
     * @param object           The object to be saved (either BoardGameRunner or
     *                         GamingBoard).
     * @param gameMode         The game mode.
     * @param parentFolderPath The parent folder path.
     * @param filename         The name of the file to save.
     * @return An Optional containing the saved object if successful, or an empty
     *         Optional otherwise.
     */
    private Optional<Object> performSave(Object object, Integer gameMode, String parentFolderPath, String filename) {
        if (gameMode != null) {
            manager.saveGame(parentFolderPath, filename, (BoardGameRunner) object);
            System.out.println("Game saved successfully.");
        } else {
            manager.saveGameReplay(parentFolderPath, filename, (GamingBoard) object);
            System.out.println("Game Replay saved successfully.");
        }
        return Optional.empty();
    }

    /**
     * Asks the user to confirm the cancellation of a process.
     *
     * @param processName The name of the process to cancel.
     * @return True if the user confirms the cancellation, false otherwise.
     */
    private boolean confirmCancellation(String processName) {
        System.out.println("Are you sure you want to quit the " + processName.toLowerCase() + " process? (y/n)");
        return InputHandler.getYesNoInput();
    }

    /**
     * Gets the filename for saving a game.
     *
     * @param parentFolderPath The parent folder path.
     * @return An Optional containing the filename if provided, or an empty Optional
     *         if the user cancels the process.
     */
    private Optional<String> getSaveFileName(String parentFolderPath) {
        String fileName = null;

        while (true) {
            System.out.print("Enter the filename (enter ~q to quit): ");
            fileName = InputHandler.getStringInput();

            if (fileName.equals("~q")) {
                return Optional.empty();
            }

            if (manager.isFileExist(parentFolderPath, fileName)) {
                System.out.println("File already exists. Do you want to overwrite it? (y/n)");
                if (!InputHandler.getYesNoInput()) {
                    continue;
                }
            }

            if (fileName.isBlank() || !manager.isFileNameValid(fileName)) {
                System.out.println("Invalid filename.\n");
                continue;
            }

            break;
        }

        return Optional.of(fileName);
    }

    /**
     * Gets the filename for loading a game.
     *
     * @param parentFolderPath The parent folder path.
     * @return An Optional containing the filename if provided, or an empty Optional
     *         if the user cancels the process.
     */
    private Optional<String> getLoadFileName(String parentFolderPath) {
        String fileName = null;
        while (true) {
            System.out.print("Enter the filename (enter ~q to quit): ");
            fileName = InputHandler.getStringInput();

            if (fileName.equals("~q")) {
                return Optional.empty();
            }

            if (fileName.isBlank() && !manager.isFileNameValid(fileName)) {
                System.out.println("Invalid filename.\n");
                continue;
            }

            if (!manager.isFileExist(parentFolderPath, fileName)) {
                System.out.println("File does not exist. Enter another file name.\n");
                continue;
            }

            break;
        }

        return Optional.of(fileName);
    }
}
