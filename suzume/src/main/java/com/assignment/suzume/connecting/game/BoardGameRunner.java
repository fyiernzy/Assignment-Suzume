package com.assignment.suzume.connecting.game;

import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.tictactoe.board.ReverseBoard;

public class BoardGameRunner {
    private Player one;
    private Player two;
    private GamingBoard board;

    public BoardGameRunner(Player one, Player two, GamingBoard board) {
        this.one = one;
        this.two = two;
        this.board = board;
    }

    public boolean gamePlay() {
        board.printRule();

        boolean isFirstPlayerTurn = Math.random() < 0.5;
        boolean hasWon = false;
        Player currentPlayer = null;
        Player winner = null;

        while (!board.isFull()) {
            board.printBoard();
            System.out.println();

            currentPlayer = isFirstPlayerTurn ? one : two;
            System.out.println(currentPlayer + "'s turn (" + currentPlayer.getMark() + ")");
            int[] move = currentPlayer.makeMove(board);
            board.recordMove(move);

            sleepForOneSecond();

            int row = move[0];
            int col = move[1];
            hasWon = board.checkForWin(row, col, currentPlayer.getMark());

            if (hasWon) {
                winner = board instanceof ReverseBoard ? (isFirstPlayerTurn ? two : one) : currentPlayer;
                break;
            }

            isFirstPlayerTurn = !isFirstPlayerTurn;
        }

        board.printBoard();

        if (board.isFull() && !hasWon) {
            System.out.println("It's a tie!");
            return false;
        } else {
            System.out.println("Congratulations, " + winner + " (" + winner.getMark() + ") has won!\n");
            return true;
        }
    }

    private void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

