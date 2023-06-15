package com.assignment.suzume.connecting.account;

import java.util.Scanner;

import com.assignment.suzume.connecting.account.data.GameDataInitializer;
import com.assignment.suzume.connecting.game.ConsolePrinter;
import com.assignment.suzume.connecting.game.GameConstant;

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

    public void login() throws InterruptedException {
        ConsolePrinter.printBanner();
        ConsolePrinter.printWelcomeMessage();
        System.out.println("HERE WE GO!");

        while(true) {
            System.out.println(" --> [1] Sign in");
            System.out.println(" --> [2] Sign up");
            System.out.println(" --> [3] Exit");
            
            ConsolePrinter.printDecorator();
            String choice = scanner.nextLine();

            switch(choice) {
                case "1" -> signInManager.run();
                case "2" -> signUpManager.run();
                case "3" -> {
                    Dashboard.showDashboard();
                    System.out.println("\u001B[1;34mExiting... ");
                    for(int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.print(".");
                    }
                    Thread.sleep(500);
                    System.exit(0);
                    System.out.println("\u001B[1;35m");
                }
                default -> System.out.println("Invalid choice.\n");
            }
        }
    }
}
