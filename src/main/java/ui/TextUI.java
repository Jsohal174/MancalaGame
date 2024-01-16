package ui;

import mancala.MancalaGame;
import mancala.Board;
import mancala.Store;
import mancala.Pit;
import mancala.Player;
import mancala.PitNotFoundException;
import mancala.InvalidMoveException;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {
    private MancalaGame game;
    private Scanner scanner;

    public TextUI() {
        game = new MancalaGame();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        Player playerOne = setupPlayer("Player One");
        Player playerTwo = setupPlayer("Player Two");
        game.setPlayers(playerOne, playerTwo);
        game.startNewGame();
        while (!game.isGameOver()) {
            System.out.println("Current board:\n");
            printBoard();
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");

            if (currentPlayer == playerOne) {
                System.out.print("Enter the pit number to move from (1-6): ");
            }
            if (currentPlayer == playerTwo) {
                System.out.print("Enter the pit number to move from (7-12): ");
            }
            int startPit;
            try {
                startPit = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            try {
                int stonesMoved = game.getNumStones(startPit - 1);
                game.move(startPit - 1);
                System.out.println(currentPlayer.getName() + " moved " + stonesMoved + " stones.");
            } catch (PitNotFoundException e) {
                System.out.println("Pit not found.");
            } catch (InvalidMoveException e) {
                System.out.println("Invalid move.");
            }

            System.out.println();
        }

        System.out.println("Game Over!\n");
        printBoard();
        Player winner = game.getWinner();
        if (winner != null) {
            System.out.println(winner.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private Player setupPlayer(String prompt) {
        System.out.print("Enter " + prompt + "'s name: ");
        String playerName = scanner.nextLine();
        return new Player(playerName);
    }

    public void printBoard() {
        Board board = game.getBoard();
        ArrayList<Pit> pits = board.getPits();
        ArrayList<Store> stores = board.getStores();

        System.out.println("P2S        Player Two        P1S");
        System.out.print("   ");

        for (int i = 12; i > 6; i--) {
            System.out.print("  " + pits.get(i - 1).getStoneCount() + " ");
        }

        System.out.println();
        System.out.println("[" + stores.get(1).getTotalStones() + "] --- --- --- --- --- --- ["
                + stores.get(0).getTotalStones() + "]");
        System.out.print("   ");

        for (int i = 1; i <= 6; i++) {
            System.out.print("  " + pits.get(i - 1).getStoneCount() + " ");
        }

        System.out.println();
        System.out.println("          Player One");
        System.out.println();
    }

    public static void main(String[] args) {
        TextUI textUI = new TextUI();
        textUI.startGame();
    }
}
