package com.assignment.suzume.connecting.account.data;

import java.util.Properties;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import com.assignment.suzume.connecting.configuration.Configuration;

public class GameDataManager {

    private static GameDataManager instance; // Singleton
    private Properties properties;
    private String gameFolderURL;

    GameDataManager() {
        this.properties = Configuration.properties;
    }

    public static GameDataManager getInstance() {
        if (instance == null) {
            instance = new GameDataManager();
        }
        return instance;
    }

    public void checkGameFolder() {
        if (!isGameFolderURLSet()) {
            configureGameFolderURL();
            saveConfiguration();
            Configuration.setGameFolderURL(gameFolderURL);
        } 

        if(gameFolderURL == null) {
            this.gameFolderURL = properties.getProperty("game_folder_url");
        }
        
        createGameFolder();
    }

    private void configureGameFolderURL() {
        // Create a JFrame to act as the parent for the file chooser dialog
        JFrame parentFrame = new JFrame();

        // Create an instance of JFileChooser
        JFileChooser fileChooser = new JFileChooser();

        // Set the file chooser to select directories only
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(parentFrame);

        // Check if the user clicked the "Open" button
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFolder = fileChooser.getSelectedFile();

            // Get the absolute path of the selected folder
            this.gameFolderURL = selectedFolder.getAbsolutePath() + "\\suzume\\";
        }
    }

    public String getGameFolderURL() {
        if(this.gameFolderURL == null) {
            properties.getProperty("game_folder_url");
        }
        return gameFolderURL;
    }

    private boolean saveConfiguration() {
        // Edit the game_folder_url property
        properties.setProperty("game_folder_url", gameFolderURL);

        // Save the modified properties back to a file if needed
        try (FileOutputStream fileOutputStream = new FileOutputStream(Configuration.CONFIG_FILE)) {
            properties.store(fileOutputStream, "Modified properties");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isGameFolderURLSet() {
        return this.properties.getProperty("game_folder_url") != null;
    }

    private void createGameFolder() {
        new File(gameFolderURL).mkdirs();
    }

    public void createUserFolder(String username) {
        final File userFolder = new File(gameFolderURL, username);
        final File saveGameFolder = new File(userFolder, "save_game");
        final File replayFolder = new File(userFolder, "replay");

        saveGameFolder.mkdirs();
        replayFolder.mkdirs();
    }
}
