package Objects;

import State.GameState;

import State.*; // Do not remove this "unused import"; the Rule subclasses need it.

public abstract class Rule extends Saveable {

    public Rule() {
        super();
    }

    public abstract void execute(GameState state);
}