package com.assignment.suzume.connecting;

import java.util.Stack;

public class GameStatus {
    String user;
    int currentRow;
    int currentCol;
    int currentStep;
    char[][] currentMap;
    Stack<int[]> stationHistory; // format: [row, col, step]
    Stack<char[][]> mapHistory;

    GameStatus(char[][] map) {
        this.currentRow = 0;
        this.currentCol = 0;
        this.currentMap = map;
        this.currentMap[0][0] = '*';
        this.stationHistory = new Stack<>();
        this.mapHistory = new Stack<>();
    }

    public void updatePosition(String direction) {
        switch (direction) {
            case "Up" -> currentRow--;
            case "Down" -> currentRow++;
            case "Left" -> currentCol--;
            case "Right" -> currentCol++;
        }
        currentMap[currentRow][currentCol] = '*';
    }

    public void backToPreviousStation() {
        int[] lastStation = stationHistory.pop();
        currentRow = lastStation[0];
        currentCol = lastStation[1];
        currentStep = lastStation[2];
        currentMap = mapHistory.pop();
    }

    public void saveCurrentStation() {
        stationHistory.push(new int[] { currentRow, currentCol, currentStep });
        mapHistory.push(MapUtils.getClonedMap(currentMap));
    }
}
