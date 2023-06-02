package com.assignment.suzume.connecting.account;

import java.io.*;
import java.util.Scanner;

import com.assignment.suzume.connecting.configuration.Configuration;
import com.google.gson.Gson;

public class SignInManager {
    private Scanner scanner;

    SignInManager() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        String username, password;

        System.out.print("Enter your username: ");
        username = scanner.nextLine();
        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        if(!UserAuthenticationManager.checkIfUserExist(username)) {
            System.out.println("User does not exist!");
            return ;
        }

        if(!UserAuthenticationManager.checkIfPasswordMatch(username, password)) {
            System.out.println("Password does not match!");
            return ;
        }

        Gson gson = new Gson();
        
        try (FileReader reader = new FileReader(new File(Configuration.GAME_FOLDER_URL, username + "/user.json"))) {
            // Deserialize JSON file into MyClass object
            User user  = gson.fromJson(reader, User.class);
            Dashboard dashboard = new Dashboard(user);
            dashboard.showDashboard();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}