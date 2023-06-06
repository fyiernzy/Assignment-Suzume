package com.assignment.suzume.example;

import java.util.*;

public class KeyProcessor {

    public int encrypt(int val, int secretKey) {
        return process(val, secretKey, true);
    }

    public int decrypt(int val, int secretKey) {
        return process(val, secretKey, false);
    }

    private String pad(int val) {
        return String.format("%3s", Integer.toBinaryString(val)).replace(' ', '0');
    }

    private int process(int val, int secretKey, boolean isEncryption) {
        String binary = Integer.toBinaryString(val);

        Queue<String> blocks = new LinkedList<>();
        for (int i = binary.length(); i > 0; i -= 3) {
            blocks.add(binary.substring(Math.max(0, i - 3), i));
        }

        Stack<String> processedBlocks = new Stack<>();

        while(!blocks.isEmpty()) {
            String block = blocks.poll();
            int flag = secretKey % 2;
            int blockValue = Integer.parseInt(block, 2) + ((isEncryption) ? 1 : -1) * flag;
            processedBlocks.push(pad(blockValue));
            secretKey >>= 1; 
        }

        StringBuilder processedBinary = new StringBuilder();
        while(!processedBlocks.isEmpty()) {
            processedBinary.append(processedBlocks.pop());
        }

        return Integer.parseInt(processedBinary.toString(), 2);
    }
}
