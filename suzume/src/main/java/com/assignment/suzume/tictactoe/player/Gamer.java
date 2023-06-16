package com.assignment.suzume.tictactoe.player;

import com.assignment.suzume.utils.InputHandler;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Gamer extends Player {

     public Gamer(String name, char mark) {
          super(name, mark);
     }

     @Override
     public int[] makeMove(GamingBoard board) {
          int[] move = new int[2];
          String message = "Enter your move (cell number OR row column): ";
          int row = -1, col = -1;
          while (true) {
               move = InputHandler.readOneOrTwoIntegers(message);

               if(move[1] == -1) { // If the user entered the cell number
                    row = (move[0] - 1) / board.getSize();
                    col = (move[0] - 1) % board.getSize();
               } else {
                    row = move[0];
                    col = move[1];
               }
               System.out.println("Your move is: " + row + " " + col);

               if (!board.isValidMove(row, col)) {
                    System.out.println("Invalid move. Please try again.");
                    continue;
               }
               break;
          }
          board.placeMark(row, col, this.mark);
          return new int[] {row, col};
     }

     public String getName() {
          return name;
     }

     @Override
     public String toString() {
          return getName();
     }
}
