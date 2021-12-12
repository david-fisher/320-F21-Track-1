package Objects;

import State.GameState;

import State.*; // Do not remove this "unused import"; those that inherit from it need it.

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