package com.assignment.suzume.connecting.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final String PARENT_PATH = "src/main/java/com/assignment/suzume/connecting/configuration/";
    private static final String CONFIG_FILE = PARENT_PATH + "config.properties";
    private static final Properties properties = loadProperties();

    public static final String BANNER_URL = properties.getProperty("banner_url");
    public static final String WELCOME_MESSAGE_URL = properties.getProperty("welcome_message_url");
    public static final String GAME_FOLDER_URL = properties.getProperty("game_folder_url");
    public static final String DECORATOR = properties.getProperty("input_prefix");

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Handle the exception accordingly
            e.printStackTrace();
        }
        return properties;
    }
}
