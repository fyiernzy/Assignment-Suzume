package com.assignment.suzume.connecting.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    public static final String PARENT_PATH = "src/main/java/com/assignment/suzume/connecting/configuration/";
    public static final String CONFIG_FILE = PARENT_PATH + "config.properties";

    public static final Properties properties = loadProperties();
    public static final String BANNER_URL = properties.getProperty("banner_url");
    public static final String WELCOME_MESSAGE_URL = properties.getProperty("welcome_message_url");
    private static String gameFolderURL = properties.getProperty("game_folder_url");
    private static String decorator = properties.getProperty("input_prefix");

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Handle the exception accordingly
            e.printStackTrace();
        }
        return properties;
    }

    public static String getGameFolderURL() {
        return gameFolderURL;
    }

    public static void setGameFolderURL(String gameFolderURL) {
        Configuration.gameFolderURL = gameFolderURL;
    }

    public static String getDecorator() {
        return decorator;
    }

    public static void setDecorator(String decorator) {
        Configuration.decorator = decorator;
    }
}
