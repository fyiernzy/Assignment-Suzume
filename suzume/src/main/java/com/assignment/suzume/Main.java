package com.assignment.suzume;

import com.assignment.suzume.connecting.account.LoginManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LoginManager loginHelper = LoginManager.getInstance();
        loginHelper.login();
    }
}
