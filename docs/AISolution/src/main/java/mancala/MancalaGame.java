package docs;
import java.util.ArrayList;

public class MancalaGame {
    private Board board;
    private Player playerOne;
    private Player playerTwo;

    public MancalaGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = new Board();
    }

    public void move(int startIndex) {
        // Implement move logic here
        // Update pits, stores, and check for capturing
    }

    public boolean isGameOver() {
        // Implement game over logic here
        // Check if all six spaces on one side of the Mancala board are empty
        return false;
    }

    public Player getWinner() {
        // Implement winner logic here
        // Return the player with the most pieces
        return null;
    }
}

