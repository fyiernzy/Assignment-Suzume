package Assignment.clone.TicTacToe.board;

public class RegularBoard extends GamingBoard {
    public RegularBoard() {
        super(5); // Set the size of the board to 5x5
    }

    @Override
    public boolean checkForWin(int row, int col) {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(checkForWinInSubpart(i, j))
                    return true;
            }
        }

        return false;
    }

    public boolean checkForWinInSubpart(int row, int col) {
        // Assume (row, col) = top left coordinate
        // Check the row
        for(int i = 0; i < 3; i++) {
            if(board[row + i][col] != ' ') {
                if (board[row + i][col] == board[row + i][col + 1] && board[row + 1][col + 1] == board[row + i][col + 2])
                    return true;
            }
        }

        // Check the column
        for(int i = 0; i < 3; i++) {
            if(board[row + i][col] != ' ') {
                if (board[row][col + i] == board[row + 1][col + i] && board[row + 1][col + i] == board[row + 2][col + i]) {
                    return true;
                }
            }
        }

        // Check the diagonals
        if (board[row][col] != ' ' || board[row + 1][col + 1] != ' ' || board[row + 2][col + 2] != ' ')
            if (board[row][col] ==board[row + 1][col + 1] && board[row + 1][col + 1] == board[row + 2][col + 2])
                return true;

        if (board[row][col + 2] != ' ' || board[row + 1][col + 1] != ' ' || board[row + 2][col] != ' ')
            if (board[row][col + 2] == board[row + 1][col + 1] && board[row + 1][col + 1] == board[col + 2][col])
                return true;

        return false;
    }

//        // Check for a horizontal win
//        int count = 0;
//        for (int i = 0; i < size; i++) {
//            if (board[row][i] != currentPlayerMark) {
//                break;
//            }
//            else
//                count++;
//            if (count == 3)
//                return true;
//        }
//
//        // Check for a vertical win
//        count = 0;
//        for (int i = 0; i < size; i++) {
//            if (board[i][col] != currentPlayerMark) {
//                break;
//            }
//            else
//                count++;
//            if (count == 3)
//                return true;
//        }
//
//        // Check for a diagonal win (top-left to bottom-right)
//        count = 0;
//        if (row == col) {
//            if (row == size / 2 && col == size / 2) {
//                if (board[row][col] == board[row + 1][col - 1] && board[row + 1][col - 1] == board[row + 2][col - 2])
//                    return true;
//                else if (board[row][col] == board[row - 1][col + 1] && board[row - 1][col + 1] == board[row - 2][col + 2])
//                    return true;
//            }
//            for (int i = 0; i < size; i++) {
//                if (board[i][i] != currentPlayerMark) {
//                    break;
//                }
//                else
//                    count++;
//                if (count == 3)
//                    return true;
//            }
//        }
//
//        // Check for a diagonal win (top-right to bottom-left)
//        if (row + col == size - 1) {
//            for (int i = 0; i < size; i++) {
//                if (board[i][size - 1 - i] == currentPlayerMark) {
//                    break;
//                }
//                else
//                    count++;
//                if (count == 3)
//                    return true;
//            }
//        }
//
//        // Check for other diagonals
//        if (row != 0 && col != 0) {
//            // if it is the last column
//            if (col == size - 1) {
//                if (board[row][col] == board[row + 1][col - 1] && board[row + 1][col - 1] == board[row + 2][col - 2])
//                    return true;
//                else if (board[row][col] == board[row - 1][col - 1] && board[row - 1][col - 1] == board[row - 2][col - 2])
//                    return true;
//            } else if (board[row][col] == board[row - 1][col - 1] && board[row - 1][col - 1] == board[row + 1][col + 1])
//                return true;
//        }
//
//        if (row == 0 && row != col && col != size - 1) {
//            if (board[0][col] == board[1][col + 1] && board[1][col + 1] == board[2][col + 2])
//                return true;
//            else if (board[0][col] == board[1][col - 1] && board[1][col - 1] == board[2][col - 2])
//                return true;
//        }
//
//        if (col == 0 && row != col) {
//            if (board[row][col] == board[row + 1][col + 1] && board[row + 1][col + 1] == board[row + 2][col + 2])
//                return true;
//            else if (board[row][col] == board[row - 1][col + 1] && board[row - 1][col + 1] == board[row - 2][col + 2])
//                return true;
//        }
//
//        return false;
}

