package mancala;

public class Player {
    private String name;
    private Store store;

    public Player() {
        name = "Player";
        store = new Store();
    }

    public Player(String playerName) {
        this.name = playerName;
        store = new Store();
    }

    public String getName() {
        return name;
    }

    public void setName(String playerName) {
        this.name = playerName;
    }

    public Store getStore() {
        return store;
    }

    public int getStoreCount() {
        return store.getTotalStones();
    }

    public void setStore(Store newStore){
        this.store = newStore;
    }

    public String toString(){
        return name;
    }
}
