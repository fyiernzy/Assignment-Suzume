package com.assignment.suzume;

import com.assignment.suzume.authentication.LoginManager;

public class Main {
    public static void main(String[] args) {
        LoginManager loginHelper = LoginManager.getInstance();
        loginHelper.login();
    }
}