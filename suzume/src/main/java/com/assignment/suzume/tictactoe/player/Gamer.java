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
          while (true) {
               System.out.println("Enter the cell number for your move: ");
               int cellNumber = scanner.nextInt();
               System.out.println("Your move is: " + cellNumber);

               int row = (cellNumber - 1) / board.getSize();
               int col = (cellNumber - 1) % board.getSize();

               if (!board.isValidMove(row, col)) {
                    System.out.println("Invalid move. Please try again.");
                    continue;
               }

               board.placeMark(cellNumber, this.mark);
               return new int[] { row, col };
          }
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
