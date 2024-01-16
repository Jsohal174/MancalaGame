package docs;

public class Store {
    private int totalStones;

    public int getTotalStones() {
        return totalStones;
    }

    public void addStones(int stones) {
        totalStones += stones;
    }

    public void emptyStore() {
        totalStones = 0;
    }
}
