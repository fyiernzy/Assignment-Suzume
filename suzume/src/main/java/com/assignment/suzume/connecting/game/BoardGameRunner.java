package com.assignment.suzume.connecting.game;

import com.assignment.suzume.tictactoe.board.*;
import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.connecting.game.monitor.*;

public class BoardGameRunner {
    private Player one;
    private Player two;
    private GamingBoard board;
    private GameMonitor monitor;

    public BoardGameRunner(Player one, Player two, GamingBoard board, GameMonitor monitor) {
        this.one = one;
        this.two = two;
        this.board = board;
        this.monitor = monitor;
    }

    public int play() {
        Player winner = null;
        Player currentPlayer = null;
        boolean hasWon = false;
        boolean isFirstPlayerTurn = Math.random() < 0.5;
        
        while (!board.isFull()) {
            board.printBoard();
            System.out.println();

            currentPlayer = isFirstPlayerTurn ? one : two;
            System.out.println(currentPlayer + "'s turn (" + currentPlayer.getMark() + ")");
            printWinProbability();

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
            return GameConstant.DRAW;
        } else {
            System.out.println("Congratulations, " + winner + " (" + winner.getMark() + ") has won!\n");
            return winner == one ? GameConstant.WIN : GameConstant.LOSE;
        }
    }

    private void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printWinProbability() {
        double[] winProbability = monitor.getWinProbability();
        System.out.printf("Win probability --> %s: %.2f%%\n", one, winProbability[0] * 100);
        System.out.printf("Win probability --> %s: %.2f%%\n", two, winProbability[1] * 100);
    }

}
