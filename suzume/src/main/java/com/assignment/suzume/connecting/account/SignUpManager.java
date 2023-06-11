package com.assignment.suzume.connecting.account;

import java.io.File;
import java.util.Scanner;
import com.assignment.suzume.connecting.account.data.*;
import com.assignment.suzume.connecting.configuration.Configuration;


public class SignUpManager {
    private Scanner scanner;
    private DatabaseManager databaseManager;
    private GameDataInitializer gameDataManager;

    SignUpManager() {
        this.scanner = new Scanner(System.in);
        this.databaseManager = DatabaseManager.getInstance();
        this.gameDataManager = GameDataInitializer.getInstance();
    }

    public void run() {
        String username, password;

        System.out.print("Enter your username: ");
        username = scanner.nextLine();

        if(databaseManager.checkIfUserExists(username)) {
            System.out.println("User already exist!");
            return;
        }

        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        String folderPath = String.format("%s/%s", Configuration.getGameFolderURL(), username);
        File folder = new File(folderPath);
        if(!folder.exists()) {
            folder.mkdir();
        }

        databaseManager.createNewUser(username, password);
        gameDataManager.createUserFolder(username);
    }
}
