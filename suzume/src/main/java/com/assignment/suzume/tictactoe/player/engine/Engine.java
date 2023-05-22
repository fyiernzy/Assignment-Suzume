package com.assignment.suzume.tictactoe.player.engine;

import com.assignment.suzume.tictactoe.board.GamingBoard;

public abstract class Engine extends Player{
    public void makeRandomMove(GamingBoard board) {
        // Check for empty cells on the board
        List<int[]> emptyCells = board.getEmptyCells();

        Random random = new Random();
        int randomIndex = random.nextInt(emptyCells.size());
        int[] randomCell = emptyCells.get(randomIndex);
        int randomRow = randomCell[0];
        int randomCol = randomCell[1];
        board.placeMark(randomRow, randomCol);

    }
}
