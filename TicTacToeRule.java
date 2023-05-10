package Assignment.clone;

public class TicTacToeRule {
    private char[][] board;
    char currentPlayerMark;

    public TicTacToeRule() {
        board = new char[3][3];
        currentPlayerMark = 'X';
        initializeBoard();
    }

    // Set/Reset the board back to all empty values.
    public void initializeBoard() {
        // Loop through rows
        for (int i = 0; i < 3; i++) {
            // Loop through columns
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("-------------");

        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Loop through all cells of the board and if one is found to be empty (contains char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    // Returns true if the player has won after placing their mark in the given row and column
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

    // Changes player mark each turn.
    public void changePlayer() {
        if (currentPlayerMark == 'X') {
            currentPlayerMark = 'O';
        }
        else {
            currentPlayerMark = 'X';
        }
    }

    // Places a mark at the cell specified by row and col with the mark of the current player.
    public boolean placeMark(int row, int col) {
        // Make sure that row and column are in bounds of the board.
        if (!isBoardFull()) {
            if (row >= 0 && row < 3 && col >= 0 && col < 3) {
                // Make sure the cell is empty
                if (board[row][col] == ' ') {
                    board[row][col] = currentPlayerMark;
                    return true;
                }
            }
        }
        if (isBoardFull())
            System.out.println("It's finish!");
        else
            System.out.println("Invalid move, please try again.");
        printBoard();
        return false;
    }
}
