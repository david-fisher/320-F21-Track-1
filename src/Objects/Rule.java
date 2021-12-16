package Objects;

import State.GameState;

public abstract class Rule extends Savable {

    public Rule() {
        super();
    }

    public abstract void execute(GameState state);
}