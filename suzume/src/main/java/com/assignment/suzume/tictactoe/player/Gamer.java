package com.assignment.suzume.tictactoe.player;

import java.util.*;
import com.assignment.suzume.tictactoe.board.GamingBoard;

public class Gamer extends Player {
     public Gamer(String name, char mark) {
          super(name, mark);
     }

     @Override
     public int[] makeMove(GamingBoard board) {
          Scanner scanner = new Scanner(System.in);
          int[] move = new int[2];

          while (true) {
               System.out.println("Please select an option:");
               System.out.println(" --> [1] Take back previous move");
               System.out.println(" --> [2] Make a move");

               int choice = scanner.nextInt();
               scanner.nextLine();

               if (choice == 1) {
                    if(!board.takeBackMove()) {
                         System.out.println("No move to take back!!!");
                    }
                    continue;
               } else if (choice == 2) {
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
                    break;
               } else {
                    System.out.println("Invalid choice. Please try again.");
               }
          }

          return move;
     }

     public String getName() {
          return name;
     }

     @Override
     public String toString() {
          return getName();
     }
}
