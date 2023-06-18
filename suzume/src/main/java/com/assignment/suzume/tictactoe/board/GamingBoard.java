package com.assignment.suzume.tictactoe.board;

import java.util.Stack;
import com.assignment.suzume.constants.FontStyle;
import org.apache.commons.lang3.StringUtils;

public abstract class GamingBoard extends Board {
    private Stack<int[]> moveHistory;

    public GamingBoard(int size) {
        super(size);
        this.moveHistory = new Stack<>();
    }

    public abstract boolean checkForWin(int row, int col, char mark);

    public Stack<int[]> getMoveHistory() {
        return moveHistory;
    }

    public void recordMove(int[] move) {
        moveHistory.push(move);
    }

    public boolean takeBackMove() {
        if (moveHistory.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                int[] move = moveHistory.pop();
                removeMark(move[0], move[1]);
            }
            return true;
        }
        return false;
    }

    public void placeMark(int row, int col, char mark) {
        board[row][col] = mark;
    }

    public void removeMark(int row, int col) {
        board[row][col] = ' ';
    }

    public boolean isValidMove(int row, int col) {
        return !isFull() && isCellWithinBoard(row, col) && isCellEmpty(row, col);
    }


    public void printBoard() {
        System.out.println("-".repeat(6 * size + 1));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int cellNumber = i * size + j + 1;
                char mark = board[i][j];
                String val;
                if (mark == ' ') {
                    val = String.valueOf(cellNumber);
                } else {
                    String markString = String.valueOf(mark);
                    int markLength = markString.length();
                    int padding = (3 - markLength) / 2;
                    String formattedMark;
                    if (mark == 'O') {
                        formattedMark = FontStyle.RED_BOLD + mark + FontStyle.YELLOW_BOLD_BRIGHT;
                    } else {
                        formattedMark = FontStyle.BLUE_BOLD + mark + FontStyle.YELLOW_BOLD_BRIGHT;
                    }
                    val = " ".repeat(padding) + formattedMark + " ".repeat(3 - padding - markLength);
                }
                System.out.printf("| %s ", StringUtils.center(val, 3));
            }
            System.out.println("|");
            System.out.println("-".repeat(6 * size + 1));
        }
    }




}
