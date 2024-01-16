package docs;

public class Pit {
    private int stoneCount;

    public Pit() {
        this.stoneCount = 4; // Initial number of stones in each pit
    }

    public int getStoneCount() {
        return stoneCount;
    }

    public void addStone() {
        stoneCount++;
    }

    public int removeStones() {
        int stones = stoneCount;
        stoneCount = 0;
        return stones;
    }
}