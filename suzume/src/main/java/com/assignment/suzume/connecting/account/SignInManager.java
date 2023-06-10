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

    public void run() {
        String username, password;

        System.out.print("Enter your username: ");
        username = scanner.nextLine();
        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        if (!databaseManager.checkIfUserExists(username)) {
            System.out.println("User does not exist!");
            return;
        }

        if (!databaseManager.checkIfPasswordMatch(username, password)) {
            System.out.println("Password does not match!");
            return;
        }

        databaseManager.loadUser(username);
        Dashboard dashboard = new Dashboard(User.getInstance());
        dashboard.showDashboard();
    }
}