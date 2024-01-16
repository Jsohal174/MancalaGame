package mancala;

public class Store {

    private Player owner;
    private int stoneCount;

    public Store() {
        stoneCount = 0;
        owner = null;
    }

    public void setOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void addStones(int amount) {
        stoneCount += amount;
    }

    public int getTotalStones() {
        return stoneCount;
    }

    public int emptyStore() {
        int stones = stoneCount;
        stoneCount = 0;
        return stones;
    }

    public String toString() {
        return "Store with " + stoneCount + " stone's";
    }

}
   
