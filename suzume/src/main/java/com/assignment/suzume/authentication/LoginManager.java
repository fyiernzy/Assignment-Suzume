package com.assignment.suzume.authentication;

import com.assignment.suzume.utils.InputHandler;
import com.assignment.suzume.utils.ConsolePrinter;
import com.assignment.suzume.data.GameFolderInitializer;

public class LoginManager {
    private static LoginManager instance; // Singleton
    private SignInManager signInManager;
    private SignUpManager signUpManager;

    private LoginManager() {
        GameFolderInitializer.getInstance().checkGameFolder();
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
            int choice = InputHandler.getIntInput();

            switch(choice) {
                case 1 -> signInManager.run();
                case 2 -> signUpManager.run();
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}