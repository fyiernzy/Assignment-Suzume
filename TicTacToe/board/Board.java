package TicTacToe.board;

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

    public int getSize() {
        return this.size;
    }

    public char[][] getBoard() {
        return this.board;
    }
}
