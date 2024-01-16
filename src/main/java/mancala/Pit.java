package mancala;

public class Pit {

    private int stoneCount;

    public Pit() {
        stoneCount = 0;
    }
    
    public int getStoneCount() {
        return stoneCount;
    }

    public void addStones(int count) {
        stoneCount += count;
    }

    public void addStone() {
        stoneCount++;
    }

    public int removeStones() {
        int stones = stoneCount;
        stoneCount = 0;
        return stones;
    }

    public String toString() {
        return "Pit with " + stoneCount + " stone(s)";
    }
}




