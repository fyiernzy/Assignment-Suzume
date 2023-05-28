package com.assignment.suzume.tictactoe.system;

import com.assignment.suzume.tictactoe.player.Player;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class GameRunner2 {
    Player one;
    Player two;
    GamingBoard board;

    public GameRunner2(Player one, Player two, GamingBoard board) {
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

        while(!board.isBoardFull()) {
            System.out.println((isOneTurn ? one : two).getName() + "'s turn");
            board.printBoard();
            if(isOneTurn) {
                move = one.makeMove(board);
            } else {
                move = two.makeMove(board);
            }

            int row = move[0], col = move[1];
            if(board.checkForWin(row, col)) {
                board.printBoard();
                break;
            }

            isOneTurn = !isOneTurn;
            board.changePlayer();
        }

        if(board.isBoardFull()) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Congratulations, player " + board.getCurrentPlayerMark() + " has won!");
        }
    }
    public boolean suzumeWin(){
        
    }

    public void setSuzume(){
        
    }
    
}
