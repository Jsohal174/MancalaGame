package mancala;

import java.util.ArrayList;

public class MancalaGame {
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;
    private Board board;

    public MancalaGame() {
        board = new Board();
        currentPlayer = playerOne;
        playerOne = new Player("Player One");
        playerTwo = new Player("Player Two");
        board.registerPlayers(playerOne, playerTwo);
        startNewGame();
    }

    //gets the board
    public Board getBoard() {
        return board;
    }
    //gets the current player
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    //return the number of stones in a pit.
    public int getNumStones(int pitNum) throws PitNotFoundException {

       if (pitNum >= 0 && pitNum < 12) {
            return board.getNumStones(pitNum);
        } else {
            throw new PitNotFoundException(); // Throw PitNotFoundException for invalid pitNum
        } 
    }
    //adds players to the arraylist
    public ArrayList<Player> getPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(playerOne);
        players.add(playerTwo);
        return players;
    }
    //return the stones in a players store
    public int getStoreCount(Player player) throws NoSuchPlayerException {
        if (currentPlayer != playerOne && currentPlayer != playerTwo){
            throw new NoSuchPlayerException();
        }
        return player.getStore().getTotalStones();
    }

    public Player getWinner() throws GameNotOverException {
        if (!isGameOver()) {
            throw new GameNotOverException();
        }
    
        // Determine the winner
        if (getStoreCount(playerOne) > getStoreCount(playerTwo)) {
            return playerOne;
        } else if (getStoreCount(playerOne) < getStoreCount(playerTwo)) {
            return playerTwo;
        }
        return null;
    }
    

    public boolean isGameOver() {
        boolean pOneEmptyPits = true;
        boolean pTwoEmptyPits = true;

        // Checks pits for player one 
        for (int i = 0; i < 6; i++) {
            if (board.getNumStones(i) != 0) {
                pOneEmptyPits = false;
                break;  
            }
        }

        // Check pits for player two 
        for (int i = 6; i < 12; i++) {
            if (board.getNumStones(i) != 0) {
                pTwoEmptyPits = false;
                break;
            }
        }

        // If pits for any player are empty, the game is over
        if (pOneEmptyPits || pTwoEmptyPits) {
            board.storeSum();
            return true;
        }

        return false;
    }

    public int move(int startPit) throws InvalidMoveException {

        int stones = 0;
        int pitSum = 0;
        //if invalid player then throw exception
        if (currentPlayer != playerOne && currentPlayer != playerTwo) {
            throw new IllegalStateException("Invalid player");
        } else if (startPit < 0 || startPit > 11) {
            throw new InvalidMoveException();
        } else {
            stones = board.moveStones(startPit, currentPlayer);
            if (stones > 0) {
                switchPlayer();
            }
        }
      
        //Sums the total number of stones in current players pits
        if (currentPlayer == playerOne && startPit > 0 && startPit < 6) {
            for (int i = 0; i < 6; i++) {
                pitSum += board.getNumStones(i);
            }
        } else if (currentPlayer == playerTwo && startPit > 6 && startPit < 12) {
            for (int i = 6; i < 12; i++) {
                pitSum += board.getNumStones(i);
            }
        }
    
        return pitSum;
    }
    
    public void setBoard(Board theBoard) {
        board = theBoard;
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer) {
        playerOne = onePlayer;
        playerTwo = twoPlayer;
        board.registerPlayers(playerOne, playerTwo);
        currentPlayer = playerOne;
    }
    //Starts a new game
    public void startNewGame() {
        board.resetBoard();
        currentPlayer = playerOne;
    }

    public String toString() {
        return "MancalaGame: " + playerOne.getName() + " vs. " + playerTwo.getName();
    }

    public void switchPlayer() {
        if (currentPlayer == playerOne) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }
    
}
