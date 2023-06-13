package com.assignment.suzume.connecting.account;


import com.assignment.suzume.connecting.account.data.DatabaseManager;

public class SignInManager {
    private DatabaseManager databaseManager;

    SignInManager() {
        this.databaseManager = DatabaseManager.getInstance();
    }

    public void run() {
        String username, password;

        System.out.print("Enter your username: ");
        username = InputHandler.getStringInput();
        System.out.print("Enter your password: ");
        password = InputHandler.getStringInput();

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