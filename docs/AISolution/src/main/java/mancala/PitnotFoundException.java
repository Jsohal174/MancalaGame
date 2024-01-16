package docs;
public class PitNotFoundException extends RuntimeException {
    public PitNotFoundException() {
        super("Pit not found.");
    }
}
