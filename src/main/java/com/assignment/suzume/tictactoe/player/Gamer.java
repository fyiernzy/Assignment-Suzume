package com.assignment.suzume.tictactoe.player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Gamer extends Player {
     transient private Scanner scanner;

     public Gamer(String name, char mark) {
          super(name, mark);
          this.scanner = new Scanner(System.in);
     }

     @Override
     public int[] makeMove(GamingBoard board) {
          int[] move = new int[2];
          while (true) {
               System.out.println("Enter your move (row column): ");
               move[0] = scanner.nextInt();
               move[1] = scanner.nextInt();
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
        this.scanner = new Scanner(System.in);
    }
}
