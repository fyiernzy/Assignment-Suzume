package com.assignment.suzume.tictactoe.player;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.assignment.suzume.connecting.account.InputHandler;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Gamer extends Player {

     public Gamer(String name, char mark) {
          super(name, mark);
     }

     @Override
     public int[] makeMove(GamingBoard board) {
          int[] move;
          while (true) {
               System.out.println("Enter your move (row column): ");
               move = InputHandler.readTwoIntegers();
               System.out.println("Your move is: " + move[0] + " " + move[1]);

               if (!board.isValidMove(move[0], move[1])) {
                    System.out.println("Invalid move. Please try again.");
                    continue;
               }
               break;
          }
          board.placeMark(move[0], move[1], this.mark);
          return move;
     }

     public String getName() {
          return name;
     }

     @Override
     public String toString() {
          return getName();
     }

     private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
