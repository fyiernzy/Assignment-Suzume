package com.assignment.suzume.connecting.account.data;

import java.io.*;
import javax.swing.*;
import java.util.Properties;
import mdlaf.MaterialLookAndFeel;
import mdlaf.utils.MaterialColors;
import javax.swing.UnsupportedLookAndFeelException;

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

        if (gameFolderURL == null) {
            this.gameFolderURL = properties.getProperty("game_folder_url");
        }

        createGameFolder();
    }

    // ...

    private void configureGameFolderURL() {
        // Set the Material Design look and feel
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    
        JFrame parentFrame = new JFrame();
        File defaultDirectory = getDefaultDirectory();
    
        JFileChooser fileChooser = new JFileChooser(defaultDirectory);
        fileChooser.updateUI();
        fileChooser.setDialogTitle("Choose a folder to save your game data");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    
        // Customize the appearance of the file chooser
        UIManager.put("FileChooser.background", MaterialColors.CYAN_50);
        UIManager.put("FileChooser.foreground", MaterialColors.BLACK);
        UIManager.put("FileChooser.selectionBackground", MaterialColors.CYAN_100);
        UIManager.put("FileChooser.selectionForeground", MaterialColors.WHITE);
        UIManager.put("FileChooser.acceptButtonToolTipText", "Select");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancel");
    
        int result = fileChooser.showOpenDialog(parentFrame);
    
        File selectedFolder = (result == JFileChooser.APPROVE_OPTION)
                ? fileChooser.getSelectedFile()
                : defaultDirectory;
    
        this.gameFolderURL = selectedFolder.getAbsolutePath() + File.separator + "suzume" + File.separator;
    }

    private File getDefaultDirectory() {
        String userHome = System.getProperty("user.home");
        File programFileDir = new File(userHome);
        return programFileDir;
    }

    public String getGameFolderURL() {
        if (this.gameFolderURL == null) {
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
