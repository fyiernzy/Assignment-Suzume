package TicTacToe;

public class PlayerUserInterface {
    Player one;
    Player two;

    PlayerUserInterface(Player one, Player two) {
        this.one = one;
        this.two = two;
    }

    private void printRules() {
        System.out.println("Welcome to Tic Tac Toe!");
    }

    public void gamePlay() {
        while(true) {
            printRules();

        }
    }
}
