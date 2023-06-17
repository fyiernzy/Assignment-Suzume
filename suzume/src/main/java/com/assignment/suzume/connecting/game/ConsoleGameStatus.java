package com.assignment.suzume.connecting.game;

import java.util.List;
import java.util.Stack;

public class ConsoleGameStatus {
    String user;
    int currentRow;
    int currentCol;
    public int currentStep;
    char[][] currentMap;
    public boolean isContinueJourney;
    Stack<int[]> stationHistory; // format: [row, col, step]
    Stack<char[][]> mapHistory;
    private List<String> path;
    private int startingCol = 0;

    public ConsoleGameStatus(char[][] map) {
        this.currentRow = 0;
        this.currentCol = 0;
        this.currentMap = map;
        this.currentMap[0][0] = '*';
        this.isContinueJourney = true;
        this.stationHistory = new Stack<>();
        this.mapHistory = new Stack<>();
    }

    public void updatePosition(String direction) {
        int newRow = currentRow;
        int newCol = currentCol;

        switch (direction) {
            case "Up" -> newRow--;
            case "Down" -> newRow++;
            case "Left" -> newCol--;
            case "Right" -> newCol++;
        }

    }

    public void backToPreviousStation() {
        int[] lastStation = stationHistory.pop();
        currentRow = lastStation[0];
        currentCol = lastStation[1];
        currentStep = lastStation[2];
        currentMap = mapHistory.pop();
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public boolean isContinueJourney() {
        return isContinueJourney;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }

    public List<String> getPath() {
        return path;
    }

    public void setCurrentStep(int i) {
        currentStep = i;
    }

}