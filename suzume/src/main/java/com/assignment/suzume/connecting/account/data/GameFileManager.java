package com.assignment.suzume.connecting.account.data;

import java.io.File;
import java.util.Scanner;
import com.assignment.suzume.connecting.account.User;
import com.assignment.suzume.connecting.game.BoardGameRunner;
import com.assignment.suzume.connecting.configuration.Configuration;

public class GameFileManager {
    private static GameFileManager instance; // Singleton
    private Scanner scanner;
    private String parentFolderFormat = "%s" + File.separator + "%s" + File.separator
            + "save_game" + File.separator + "%s";
    private GameDataManager gameDataManager;

    private GameFileManager() {
        this.scanner = new Scanner(System.in);
        this.gameDataManager = GameDataManager.getInstance();
    }

    public static GameFileManager getInstance() {
        if (instance == null) {
            instance = new GameFileManager();
        }
        return instance;
    }

    public void saveGame(BoardGameRunner runner, int gameMode) {
        String[] folders = generateFolderPaths(gameMode);
        createFolderIfNotExists(folders[0]);
        gameDataManager.saveGame(folders[0], folders[1], runner);
    }

    public BoardGameRunner loadGame(int gameMode) {
        String[] folders = generateFolderPaths(gameMode);
        return gameDataManager.loadGame(folders[0], folders[1]);
    }

    private String[] generateFolderPaths(int gameMode) {
        String username = User.getInstance().getName();
        String subfolder = getSubfolder(gameMode);
        String parentFolder = String.format(parentFolderFormat, Configuration.getGameFolderURL(), username, subfolder);
        String fileName = getValidFileName(parentFolder);
        System.out.println(parentFolder);
        return new String[] { parentFolder, fileName };
    }

    private String getValidFileName(String parentFolder) {
        String fileName = null;
        while (true) {
            System.out.print("Enter the filename: ");
            fileName = scanner.nextLine();
            if (!fileName.isBlank() && isFileNameValid(fileName)) {
                break;
            }
            System.out.println("Invalid filename.");
        }
        return fileName;
    }

    boolean isFileExist(String parentFolder, String fileName) {
        File file = new File(parentFolder + File.separator + fileName);
        return file.exists();
    }

    private static boolean isFileNameValid(String fileName) {
        return fileName.matches("[\\w-]+");
    }

    private String getSubfolder(int gameMode) {
        switch (gameMode) {
            case 1:
                return "pvp";
            case 2:
                return "pve";
            case 3:
                return "eve";
            default:
                throw new IllegalArgumentException("Invalid mod choice.");
        }
    }

    private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static void main(String[] args) {
        GameDataInitializer.getInstance().checkGameFolder();
        System.out.println(GameFileManager.getInstance().generateFolderPaths(1)[0]);
    }
}
