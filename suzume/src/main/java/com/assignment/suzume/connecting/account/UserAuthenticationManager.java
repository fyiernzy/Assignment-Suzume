package com.assignment.suzume.connecting.account;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import com.assignment.suzume.connecting.configuration.Configuration;
public class UserAuthenticationManager {
    
    public static boolean checkIfPasswordMatch(String username, String password) {
        File userFile = new File(Configuration.GAME_FOLDER_URL, username + "/user.json");
        if (!userFile.exists()) {
            return false;
        }

        try (FileReader fileReader = new FileReader(userFile)) {
            JsonFileReader reader = JsonFileReader.createJsonFileReader(fileReader);
            String storedPassword = reader.getStringValue("password");
            return storedPassword.equals(password);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean checkIfUserExist(String username) {
        File userFolder = new File(Configuration.GAME_FOLDER_URL);
        File[] userFiles = userFolder.listFiles();
        if (userFiles == null) {
            return false;
        }

        for (File file : userFiles) {
            if (file.isDirectory() && FilenameUtils.getBaseName(file.getName()).equals(username)) {
                return true;
            }
        }
        return false;
    }
}
