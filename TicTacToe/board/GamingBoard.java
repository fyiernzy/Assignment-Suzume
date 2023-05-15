package TicTacToe.board;

public abstract class GamingBoard extends Board {
    protected char currentPlayerMark;

    GamingBoard(int size) {
        super(size);
        this.currentPlayerMark = 'X';
    }

    abstract boolean checkForWin(int row, int col);

    // Changes player mark each turn.
    public void changePlayer() {
        if (currentPlayerMark == 'X') {
            currentPlayerMark = 'O';
        } else {
            currentPlayerMark = 'X';
        }
    }   

    // Loop through all cells of the board and if one is found to be empty (contains
    // char '-') then return false.
    // Otherwise the board is full.
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Places a mark at the cell specified by row and col with the mark of the
    // current player.
    public boolean placeMark(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayerMark;
            return true;
        }

        return false;
    }

    public boolean isValidMove(int row, int col) {
        return !isBoardFull()
                && (row >= 0 && row < size) && (col >= 0 && col < size)
                && board[row][col] == ' ';
    }

    public char getCurrentPlayerMark() {
        return currentPlayerMark;
    }
}
