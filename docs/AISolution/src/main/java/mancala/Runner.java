package Runner;

public class Runner {
    public static void main(String[] args) {
        Player playerOne = new Player("Player One");
        Player playerTwo = new Player("Player Two");

        MancalaGame game = new MancalaGame(playerOne, playerTwo);

        // Add more logic for running the game, taking input, displaying the board, etc.
    }
}