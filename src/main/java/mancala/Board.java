package mancala;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pit> pits;
    private ArrayList<Store> stores;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer; // Added currentPlayer field

    public Board() {
        // Initialized a new Mancala board with pits and stores
        pits = new ArrayList<>();
        stores = new ArrayList<>();
        setUpPits();
        setUpStores();
        registerPlayers(null, null);
        initializeBoard();
    }

    public int captureStones(int stoppingPoint) throws PitNotFoundException{
        if(stoppingPoint < 0 || stoppingPoint > 12){
            throw new PitNotFoundException();
        }
        int capturedStones = 0;
        int startIndex = stoppingPoint - 1;
        //getting the stone count in the pit.
        if (startIndex >= 0 && startIndex < 12 && pits.get(startIndex).getStoneCount() == 1) {
            int oppositePitIndex = 11 - startIndex;
            //if their are stones in the opposite pit then capture and add them to the playrs store
            if(pits.get(oppositePitIndex).getStoneCount()!=0) {
            capturedStones = pits.get(oppositePitIndex).removeStones();
            capturedStones += pits.get(startIndex).removeStones();
            }
        }
        return capturedStones;
    }
    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum >= 0 && pitNum < 12) {
            return pits.get(pitNum).getStoneCount();
        } else {
            throw new PitNotFoundException();
        }
    }
    
    public int distributeStones(int startingPoint) throws PitNotFoundException{

        if(startingPoint < 0 || startingPoint > 12){
            throw new PitNotFoundException();
        }
        int stoneInStore;
        int stonesAdded = 0;
        int currentIndex = startingPoint;
        int distributeStoneCount = pits.get(startingPoint).removeStones();
        boolean indexIncremented;
        //To make sure that stones are added to the store rather then next index without skipping
        if(startingPoint ==5 || startingPoint== 11) {
        indexIncremented = false; 
        } else {
        indexIncremented = true;
        }
        //While we have stones to distribute
        while (distributeStoneCount > 0) {
            if (indexIncremented) {
                currentIndex++;
                if (currentIndex > 11) {
                    currentIndex = 0;
                }
                pits.get(currentIndex).addStone();
                distributeStoneCount--;
                stonesAdded++;
            }
    
           
    
            // Set the currentPlayer based on the current player's store
            //If the index is 5 or 11 then add stones to store of the current player
            if (currentPlayer == playerOne) { 
                if (currentIndex == 5) {
                    if (distributeStoneCount > 0 && distributeStoneCount != 1) {
                        stores.get(0).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        distributeStoneCount--;
                        stonesAdded++;
                    } else if (distributeStoneCount == 1) {
                        stores.get(0).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        distributeStoneCount--;
                        //Return -1 to give the current player a bonus chance
                        return -1;
                    }
                }
                if (distributeStoneCount == 0 && currentIndex <= 5 && currentIndex >= 0) {
                    int lastPit = currentIndex;
                    if (pits.get(lastPit).getStoneCount() == 1) {
                        int capturedStones = captureStones(lastPit + 1);
                        stoneInStore = stores.get(0).getTotalStones();
                        stores.get(0).addStones(capturedStones);
                        currentPlayer.getStore().addStones(capturedStones);
                        stonesAdded = stonesAdded + (stores.get(0).getTotalStones() - stoneInStore);
                    }
                }
            } else if (currentPlayer == playerTwo) {
                if (currentIndex == 11 && currentPlayer == playerTwo) {
                    if (distributeStoneCount > 0 && distributeStoneCount != 1) {
                        stores.get(1).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        distributeStoneCount--;
                        stonesAdded++;
                    } else if (distributeStoneCount == 1) {
                        stores.get(1).addStones(1);
                        currentPlayer.getStore().addStones(1);
                        distributeStoneCount--;
                        //Return -1 to give the current player a bonus chance
                        return -1;
                    }
                }
                //Capturing stones when required
                if (distributeStoneCount == 0 && currentIndex <= 11 && currentIndex >= 6) {
                    int lastPit = currentIndex;
                    if (pits.get(lastPit).getStoneCount() == 1) {
                        int capturedStones = captureStones(lastPit + 1);
                        stoneInStore = stores.get(0).getTotalStones();
                        stores.get(1).addStones(capturedStones);
                        currentPlayer.getStore().addStones(capturedStones);
                        stonesAdded = stonesAdded + (stores.get(0).getTotalStones() - stoneInStore);
                    }
                }
            }
    
           
            indexIncremented = true;
        }
    
        return stonesAdded;
    }
    
    public ArrayList<Pit> getPits() {
        // Creating a new ArrayList that includes only the pits and excluding stores
        ArrayList<Pit> boardPits = new ArrayList<>(pits.subList(0, 12));
        return boardPits;
    }

    public ArrayList<Store> getStores() {
        // Create a new ArrayList that includes only the stores
        ArrayList<Store> boardStores = new ArrayList<>(stores);
        return boardStores;
    }

    public boolean isSideEmpty(int pitNum) throws PitNotFoundException {
        if(pitNum < 0 || pitNum > 12){
            throw new PitNotFoundException();
        }
        int endIndex = pitNum;
        int startIndex;
        if(endIndex<=6){
            startIndex = 0;
            endIndex = 6;
        } else{
            startIndex = 6;
            endIndex = 11;
        }// 0-5 for Player One, 7-12 for Player Two

        for (int i = startIndex; i <= endIndex; i++) {
            if (pits.get(i).getStoneCount() > 0) {
                return false;
            }
        }
        return true;
    }  

    public int moveStones(int startPit, Player player) throws InvalidMoveException {
        currentPlayer = player;
        int stones = 0;
        //Throwing different exceptions when required 
    
        if (currentPlayer == playerOne) {
            if (startPit > 5 || startPit < 0) {
                throw new InvalidMoveException();
            }
            if (pits.get(startPit).getStoneCount() == 0) {
                throw new InvalidMoveException();
            }
        } else if (currentPlayer == playerTwo) { // Fix: Change to playerTwo
            if (startPit > 11 || startPit < 6) {
                throw new InvalidMoveException();
            }
            if (pits.get(startPit).getStoneCount() == 0) {
                throw new InvalidMoveException();
            }
        }
    
        // Check if the startPit is within bounds
        if (startPit >= 0 && startPit < pits.size()) {
            if (pits.get(startPit).getStoneCount() > 0) {
                stones = distributeStones(startPit);
                return stones;
            } else {
                // Handle the case if no stones
                System.out.println("No stones in the selected pit.");
            }
            return 1;
        } else {
            // Handle the case if startPit out of bound
            System.out.println("Invalid pit index.");
            return -1; // or another value indicating failure
        }
    }
    
    
    

    public void registerPlayers(Player one, Player two) {
        // Connecting Players to their Stores
        if (one != null && two != null) {
            stores.get(0).setOwner(one);
            stores.get(1).setOwner(two);
            playerOne = one; 
            playerTwo = two;
        }
    }

    public void resetBoard() {
        // Resets the board
        initializeBoard();
    }

    public void setUpPits() {
        // Establishes 12 Pits in the board, with 4 stones each
        for (int i = 0; i < 12; i++) {
            Pit pit = new Pit();
            pits.add(pit);
        }
    }
    
    public void initializeBoard(){
        //initializing the board by removing all stones 
        //and adding 4 stones in each Pit
        for(Pit pit: pits){
            pit.removeStones();
            pit.addStones(4);
        }
         for (Store store : stores) {
            store.emptyStore();
        }
    }
    

    public void setUpStores() {
        // Establishes 2 empty Stores
        stores.add(new Store());
        stores.add(new Store());
    }

    public String toString() {
        StringBuilder boardString = new StringBuilder();
        boardString.append("Mancala Board\n");

        // Add pits and stores information here
        for (int i = 0; i < 12; i++) {
            boardString.append("Pit").append(i + 1).append(":").append(pits.get(i).getStoneCount()).append(" stones\n");
        }
        boardString.append("Store 1: ").append(stores.get(0).getTotalStones()).append(" stones\n");
        boardString.append("Store 2: ").append(stores.get(1).getTotalStones()).append(" stones\n");

        return boardString.toString();
    }
    public void storeSum() {
        // Sum all stones in P1's pits and add to P2
        int playerOneStonesSum = 0;
        for (int i = 0; i < 6; i++) {
            playerOneStonesSum += pits.get(i).removeStones();
        }
        stores.get(0).addStones(playerOneStonesSum);
         playerOne.getStore().addStones(playerOneStonesSum);

        // Sum all stones in P1's pit and add to P1
        int playerTwoStonesSum = 0;
        for (int i = 6; i < 12; i++) {
            playerTwoStonesSum += pits.get(i).removeStones();
        }
        stores.get(1).addStones(playerTwoStonesSum);
        playerTwo.getStore().addStones(playerTwoStonesSum);
    }
}
