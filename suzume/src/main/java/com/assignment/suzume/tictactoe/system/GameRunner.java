package com.assignment.suzume.tictactoe.system;

import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;
import com.assignment.suzume.tictactoe.board.ReverseBoard;

public class GameRunner {
    Player one;
    Player two;
    GamingBoard board;

    public GameRunner(Player one, Player two, GamingBoard board) {
        this.one = one;
        this.two = two;
        this.board = board;
    }

    private void printRules() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println(board.getRule());
    }

    public void gamePlay() {
        printRules();
        boolean isOneTurn = (int) (Math.random() * 2) == 0 ? true : false;
        int[] move = new int[2];
        boolean hasWon = false;

        if(!isOneTurn){
            board.changePlayer();
        }

        while(!board.isBoardFull()) {
            board.printBoard();
            System.out.println();
            if(isOneTurn) {
                board.setCurrentPlayer(one);
                System.out.println(one + "'s turn(" + board.getCurrentPlayerMark() + ")");
                move = one.makeMove(board);
            } else {
                board.setCurrentPlayer(two);
                System.out.println(two + "'s turn(" + board.getCurrentPlayerMark() + ")");
                move = two.makeMove(board);
            }

            int row = move[0], col = move[1];
            hasWon = board.checkForWin(row, col);
            if(hasWon) {
                if (board instanceof ReverseBoard) {
                    board.changePlayer();
                }
                break;
            }

            isOneTurn = !isOneTurn;
            board.changePlayer();
        }

        if(board.isBoardFull() && !hasWon) {
            board.printBoard();
            System.out.println("It's a tie!");
        } else {
            board.printBoard();
            System.out.println("Congratulations, " + board.getCurrentPlayer() + "(" + board.getCurrentPlayerMark() + ")" + " has won!\n");
        }
    }

    public boolean isOneWin(){
        return board.getCurrentPlayer().equals(one);
    }
}
