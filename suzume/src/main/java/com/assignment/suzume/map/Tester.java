package com.assignment.suzume.map;

import java.util.Scanner;
import java.io.*;

public class Tester
{
    public static void main(String[] args) throws IOException {
        String color = "\033[1;31m";
        String message = "Hello world!";
        System.out.println(message);

        Scanner sc = new Scanner(new File("src/main/java/com/assignment/suzume/connecting/configuration/banner.txt"));
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(line);
        }

    }
}
