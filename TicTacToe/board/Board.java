package Assignment.clone.TicTacToe.board;

public abstract class Board {
    protected int size;
    protected char[][] board;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        initializeBoard();
    }
    
    // Set/Reset the board back to all empty values.
    public void initializeBoard() {
        // Loop through rows
        for (int i = 0; i < size; i++) {
            // Loop through columns
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Print the current board (may be replaced by GUI implementation later)
    public void printBoard() {
        System.out.println("----".repeat(size));

        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("----".repeat(size));
        }
    }
}
