package Objects;

import State.GameState;

public abstract class Rule extends Saveable {

    public Rule() {
        super();
    }
    
    public Rule(String id) {
    	super(id);
    }

    public abstract void execute(GameState state);

    public abstract String prettyPrint();
}