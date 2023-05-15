package Assignment.clone.TicTacToe.board;

public class ReverseBoard extends GamingBoard {
    public ReverseBoard() {
        super(3); // Set the size of the board to 3x3
    }

    @Override
    boolean checkForWin(int row, int col) {
        // Check for a horizontal win
        for (int i = 0; i < size; i++) {
            if (board[row][i] == currentPlayerMark) {
                break;
            }
            if (i == size - 1) {
                return true;
            }
        }

        // Check for a vertical win
        for (int i = 0; i < size; i++) {
            if (board[i][col] == currentPlayerMark) {
                break;
            }
            if (i == size - 1) {
                return true;
            }
        }

        // Check for a diagonal win (top-left to bottom-right)
        if (row == col) {
            for (int i = 0; i < size; i++) {
                if (board[i][i] == currentPlayerMark) {
                    break;
                }
                if (i == size - 1) {
                    return true;
                }
            }
        }

        // Check for a diagonal win (top-right to bottom-left)
        if (row + col == size - 1) {
            for (int i = 0; i < size; i++) {
                if (board[i][size - 1 - i] == currentPlayerMark) {
                    break;
                }
                if (i == size - 1) {
                    return true;
                }
            }
        }

        return false;
    }
}

