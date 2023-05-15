package Assignment.clone.TicTacToe;

public class PlayerUserInterface {
    Player one;
    Player two;

    PlayerUserInterface(Player one, Player two) {
        this.one = one;
        this.two = two;
    }

    private void printRules(String rules) {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println(rules);
    }

    public void gamePlay(String rules) {
        while(true) {
            printRules(rules);

        }
    }
}
