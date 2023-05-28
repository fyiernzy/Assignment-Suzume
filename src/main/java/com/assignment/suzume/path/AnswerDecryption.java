package com.assignment.suzume.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerDecryption {
    public static int decryptNumber(int encryptedNumber, int secretKey) {
        // Step 1: Convert the number to binary format
        String binaryString = Integer.toBinaryString(encryptedNumber);

        // Step 2: Divide the binary string into blocks of three digits, starting from the right
        List<String> blocks = divideIntoBlocks(binaryString, 3);

        // Step 3: For each block
        StringBuilder decryptedBinaryString = new StringBuilder();
        for (String block : blocks) {
            // Step 4a: Subtract the value of (secret key modulo 2) from the binary value of the block
            int decryptedValue = Integer.parseInt(block, 2) - (secretKey % 2);

            // Step 4b: Convert the decrypted value back to binary format in blocks of three digits
            String decryptedBlock = padBinaryString(Integer.toBinaryString(decryptedValue), 3);

            // Step 4c: Shift the bits of secret key to the right by a positive integer below 10
            int shiftAmount = decryptedValue % 10;
            String shiftedBlock = shiftBits(decryptedBlock, shiftAmount);

            // Append the modified block to the decrypted binary string
            decryptedBinaryString.append(shiftedBlock);

            // Update the secret key by shifting it to the right
            secretKey >>= 1;
        }

        // Step 5: Convert the decrypted binary string back to an integer
        int decryptedNumber = Integer.parseInt(decryptedBinaryString.toString(), 2);

        return decryptedNumber;
    }

    private static List<String> divideIntoBlocks(String binaryString, int blockSize) {
        List<String> blocks = new ArrayList<>();
        int length = binaryString.length();
        for (int i = length; i > 0; i -= blockSize) {
            int startIndex = Math.max(i - blockSize, 0);
            String block = binaryString.substring(startIndex, i);
            blocks.add(block);
        }
        Collections.reverse(blocks);
        return blocks;
    }

    private static String padBinaryString(String binaryString, int desiredLength) {
        StringBuilder paddedString = new StringBuilder(binaryString);
        while (paddedString.length() < desiredLength) {
            paddedString.insert(0, '0');
        }
        return paddedString.toString();
    }

    private static String shiftBits(String binaryString, int shiftAmount) {
        StringBuilder shiftedString = new StringBuilder();
        int length = binaryString.length();
        for (int i = 0; i < length; i++) {
            int newIndex = (i + shiftAmount) % length;
            shiftedString.append(binaryString.charAt(newIndex));
        }
        return shiftedString.toString();
    }

    public static void main(String[] args) {
        int encryptedNumber = 17355;
        int secretKey = 7;

        int decryptedNumber = decryptNumber(encryptedNumber, secretKey);
        System.out.println("Decrypted Number: " + decryptedNumber);
    }
}
