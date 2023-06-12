package com.assignment.suzume.connecting.account;

import java.util.Scanner;

import com.assignment.suzume.connecting.account.data.GameDataInitializer;
import com.assignment.suzume.connecting.game.ConsolePrinter;

public class LoginManager {
    private static LoginManager instance; // Singleton
    private Scanner scanner;
    private SignInManager signInManager;
    private SignUpManager signUpManager;

    private LoginManager() {
        GameDataInitializer.getInstance().checkGameFolder();
        this.scanner = new Scanner(System.in);
        this.signInManager = new SignInManager();
        this.signUpManager = new SignUpManager();
    }

    public static LoginManager getInstance() {
        if(instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public void login() {
        ConsolePrinter.printBanner();
        ConsolePrinter.printWelcomeMessage();

        while(true) {
            System.out.println(" --> [1] Sign in");
            System.out.println(" --> [2] Sign up");
            System.out.println(" --> [3] Exit");
            
            ConsolePrinter.printDecorator();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1 -> signInManager.run();
                case 2 -> signUpManager.run();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
