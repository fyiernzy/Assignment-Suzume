package Assignment.clone.TicTacToe.board;

public class VariantBoard extends GamingBoard {
    public VariantBoard() {
        super(3);
    }

    // Returns true if the player has won after placing their mark in the given row
    // and column
    public boolean checkForWin(int row, int col) {
        boolean win = false;

        // Check the row
        if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
            win = true;
        }

        // Check the column
        if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
            win = true;
        }

        // Check the diagonals
        if (board[0][0] != ' ' || board[1][1] != ' ' || board[2][2] != ' ')
            if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
                win = true;
        if (board[0][2] != ' ' || board[1][1] != ' ' || board[2][0] != ' ')
            if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
                win = true;
        return win;
    }

     
}
