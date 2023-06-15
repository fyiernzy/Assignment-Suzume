package com.assignment.suzume.connecting.account;

import java.util.Scanner;

import com.assignment.suzume.connecting.account.data.DatabaseManager;

public class SignInManager {
    private Scanner scanner;
    private DatabaseManager databaseManager;

    SignInManager() {
        this.scanner = new Scanner(System.in);
        this.databaseManager = DatabaseManager.getInstance();
    }

    public void run() throws InterruptedException {
        String username, password;

        System.out.print("Enter your username: ");
        username = scanner.nextLine();
        System.out.print("Enter your password: ");
        password = scanner.nextLine();
        System.out.println("\u001B[1;34mLogging in...");
        for(int i = 0; i < 3; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }
        Thread.sleep(500);
        System.out.println("\u001B[1;35m");
        if (!databaseManager.checkIfUserExists(username)) {
            System.out.println("User does not exist!\n");
            return;
        }

        if (!databaseManager.checkIfPasswordMatch(username, password)) {
            System.out.println("Password does not match!\n");
            return;
        }

        databaseManager.loadUser(username);
        Dashboard dashboard = new Dashboard(User.getInstance());
        System.out.println("\nWelcome back, " + username + "!");
        dashboard.showDashboard();
    }
}