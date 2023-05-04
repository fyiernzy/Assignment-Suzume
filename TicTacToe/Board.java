package TicTacToe;

public class Board {
    int size;
    char[][] board;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
    }

    public char[][] getBoard() {
        return board;
    }
}
