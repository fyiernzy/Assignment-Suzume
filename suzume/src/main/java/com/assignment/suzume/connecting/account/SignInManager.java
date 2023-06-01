package com.assignment.suzume.connecting.account;

import java.util.Scanner;

public class SignInManager {
    private Scanner scanner;

    SignInManager() {
        this.scanner = new Scanner(System.in);
    }

    public boolean run() {
        String username, password;

        System.out.print("Enter your username: ");
        username = scanner.nextLine();
        System.out.print("Enter your password: ");
        password = scanner.nextLine();

        if(!UserAuthenticationManager.checkIfUserExist(username)) {
            System.out.println("User does not exist!");
            return false;
        }

        if(!UserAuthenticationManager.checkIfPasswordMatch(username, password)) {
            System.out.println("Password does not match!");
            return false;
        }

        return true;
    }
}