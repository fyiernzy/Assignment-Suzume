package com.assignment.suzume.game;

import java.util.Stack;
import com.assignment.suzume.utils.MapUtils;

public class ConsoleGameStatus {
    private int currentTile;
    private int currentRow;
    private int currentCol;
    private int currentStep;
    private char[][] currentMap;
    private boolean isContinueJourney;
    private Stack<int[]> stationHistory; // format: [row, col, step]
    private Stack<char[][]> mapHistory;

    /**
     * Constructs a new ConsoleGameStatus object with the provided map.
     *
     * @param map the initial map representation
     */
    public ConsoleGameStatus(char[][] map) {
        this.currentRow = 0;
        this.currentCol = 0;
        this.currentMap = map;
        this.currentMap[0][0] = '*';
        this.isContinueJourney = true;
        this.stationHistory = new Stack<>();
        this.mapHistory = new Stack<>();
    }

    /**
     * Updates the position of the player based on the given direction.
     *
     * @param direction the direction to move in ("Up", "Down", "Left", "Right")
     */
    public void updatePosition(String direction) {
        switch (direction) {
            case "Up" -> currentRow--;
            case "Down" -> currentRow++;
            case "Left" -> currentCol--;
            case "Right" -> currentCol++;
        }
        currentTile = currentMap[currentRow][currentCol];
        currentMap[currentRow][currentCol] = isCurrentPositionStation() ? '!' : '*';
        currentStep++;
    }

    /**
     * Moves the player back to the previous station.
     *
     * @return true if successfully moved back to the previous station, false otherwise
     */
    public boolean backToPreviousStation() {
        if (stationHistory.isEmpty()) {
            return false;
        }
        int[] lastStation = stationHistory.pop();
        currentRow = lastStation[0];
        currentCol = lastStation[1];
        currentStep = lastStation[2];
        currentMap = mapHistory.pop();
        return true;
    }

    /**
     * Saves the current station in the station history and map history.
     */
    public void saveCurrentStation() {
        stationHistory.push(new int[]{currentRow, currentCol, currentStep});
        mapHistory.push(MapUtils.getClonedMap(currentMap));
    }

    /**
     * Sets the flag indicating whether the journey should continue.
     *
     * @param isContinueJourney true to continue the journey, false otherwise
     */
    public void setContinueJourney(boolean isContinueJourney) {
        this.isContinueJourney = isContinueJourney;
    }

    /**
     * Checks if the journey should continue.
     *
     * @return true if the journey should continue, false otherwise
     */
    public boolean isContinueJourney() {
        return this.isContinueJourney;
    }

    /**
     * Returns the current map representation.
     *
     * @return the current map as a 2D character array
     */
    public char[][] getCurrentMap() {
        return this.currentMap;
    }

    /**
     * Returns the current step in the journey.
     *
     * @return the current step
     */
    public int getCurrentStep() {
        return this.currentStep;
    }

    /**
     * Returns the station history stack.
     *
     * @return the station history stack
     */
    public Stack<int[]> getStationHistory() {
        return this.stationHistory;
    }

    /**
     * Checks if the current position is a station.
     *
     * @return true if the current position is a station, false otherwise
     */
    public boolean isCurrentPositionStation() {
        return currentTile == '@';
    }

    /**
     * Checks if the current position is the destination.
     *
     * @return true if the current position is the destination, false otherwise
     */
    public boolean isCurrentPositionDestination() {
        return currentTile == 'X';
    }

    /**
     * Returns the current row of the player.
     *
     * @return the current row
     */
    public int getCurrentRow() {
        return this.currentRow;
    }

    /**
     * Returns the current column of the player.
     *
     * @return the current column
     */
    public int getCurrentCol() {
        return this.currentCol;
    }
}
