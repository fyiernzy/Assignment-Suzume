package com.assignment.suzume.example;

import com.assignment.suzume.utils.KeyProcessor;
public class KeyProcessorTester {
    public static void main(String[] args) {
        KeyProcessor processor = new KeyProcessor();
        int secretKey = 7, weirdNumber = 17355, encrypted, decrypted;
        String title = "| KeyProcessor Tester |";
        System.out.println(title);
        System.out.println("=".repeat(title.length()));
        System.out.printf(" \\----> Decryption of %d = %d\n", weirdNumber, (decrypted = processor.decrypt(weirdNumber, secretKey)));
        System.out.printf(" \\----> Encryption of %d = %s\n", decrypted, (encrypted = processor.encrypt(decrypted, secretKey)));
        System.out.println("%s --- Decryption ---> %s --- Encryption ---> %s".formatted(weirdNumber, decrypted, encrypted));
    }
}
