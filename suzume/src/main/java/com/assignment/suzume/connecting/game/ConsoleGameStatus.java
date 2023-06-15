package com.assignment.suzume.connecting.game;

import java.util.List;
import java.util.Stack;
import com.assignment.suzume.map.utils.MapUtils;

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
    private int startingRow = 0; // Added startingRow variable
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

        // Check if the new position is within the valid range of the map
        if (isValidPosition(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
            currentMap[currentRow][currentCol] = '*';

            // Save the updated map
            mapHistory.push(MapUtils.getClonedMap(currentMap));
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < currentMap.length && col >= 0 && col < currentMap[0].length;
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

    // simyi editted
    public int getCurrentRow() {
        return currentRow;
    }

    // simyi editted
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

    public void resetPosition(int[][] map) {
        currentRow = startingRow;
        currentCol = startingCol;
        currentStep = 0;
        currentMap = MapUtils.transformMap(map);
        currentMap[currentRow][currentCol] = '*';
    }

}