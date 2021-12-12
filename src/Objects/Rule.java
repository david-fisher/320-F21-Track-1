package objects;

public abstract class Rule extends Saveable {
    private int score;
    private RNG rng;
    private Card card;
    private Tile tile;

    public Rule() {
        super();
    }

    public abstract void execute(GameState state);

}