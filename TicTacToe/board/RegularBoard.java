package TicTacToe.board;

public class RegularBoard extends GamingBoard {
    public RegularBoard() {
        super(5); // Set the size of the board to 5x5
    }

    @Override
    public boolean checkForWin(int row, int col) {
        int[][] positions = { { 0, 1, 2 }, { -1, 0, 1 }, { -2, -1, 0 } };

        int r1, r2, r3, c1, c2, c3;
        for (int[] pos : positions) {

            r1 = row + pos[0];
            r2 = row + pos[1];
            r3 = row + pos[2];
            c1 = col + pos[0];
            c2 = col + pos[1];
            c3 = col + pos[2];

            // Check for horizontal win
            if (isValid(c1, c2, c3)) {
                if (board[row][c1] != ' ' && board[row][c1] == board[row][c1] && board[row][c1] == board[row][c2]) {
                    return true;
                }
            }

            // Check for vertical win
            if (isValid(r1, r2, r3)) {
                if (board[r1][col] != ' ' && board[r1][col] == board[r2][col] && board[r2][col] == board[r3][col]) {
                    return true;
                }
            }

            // Check for diagonal win
            if (isValid(r1, r2, r3) && isValid(c1, c2, c3)) {
                if (board[r1][c1] != ' ' && board[r1][c1] == board[r2][c2] && board[r2][c2] == board[r3][c3]) {
                    return true;
                }
            }

            r1 = row - pos[0];
            r2 = row - pos[1];
            r3 = row - pos[2];

            if (isValid(r1, r2, r3) && isValid(c1, c2, c3)) {
                if (board[r1][c1] != ' ' && board[r1][c1] == board[r2][c2] && board[r2][c2] == board[r3][c3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValid(int... positions) {
        for (int pos : positions) {
            if (pos < 0 || pos >= size) {
                return false;
            }
        }
        return true;
    }
}
