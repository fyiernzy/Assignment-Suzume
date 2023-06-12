package com.assignment.suzume;

import com.assignment.suzume.connecting.account.LoginManager;

public class Main {
    public static void main(String[] args) {
        LoginManager loginHelper = LoginManager.getInstance();
        loginHelper.login();
    }
}
