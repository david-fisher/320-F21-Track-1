package Objects;

import State.GameState;

public abstract class Rule extends Saveable {

    public Rule() {
        super();
    }

    public abstract void execute(GameState state);
}