package com.assignment.suzume.connecting.account;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import com.assignment.suzume.connecting.configuration.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SignUpManager {
    private Scanner scanner;

    SignUpManager() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        String username, password;

        System.out.print("Enter your username: ");
        username = scanner.nextLine();

        if(UserAuthenticationManager.checkIfUserExist(username)) {
            System.out.println("User already exist!");
            return;
        }

        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        String folderPath = String.format("%s/%s", Configuration.GAME_FOLDER_URL, username);
        File folder = new File(folderPath);
        if(!folder.exists()) {
            folder.mkdir();
        }

        JsonObject userObject = Json.createObjectBuilder()
                .add("username", username)
                .add("password", password)
                .add("win", 0)
                .add("lose", 0)
                .add("draw", 0)
                .build();

            
        try (JsonWriter jsonWriter = Json
                .createWriter(new FileWriter(String.format(folderPath + "/user.json", Configuration.GAME_FOLDER_URL, username)))) {
            jsonWriter.writeObject(userObject);
            System.out.println("User information saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
