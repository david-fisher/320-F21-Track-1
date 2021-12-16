package Objects;

import State.*;

public class PlayCardRule extends Rule {
  public PlayCardRule() {
    super();
  }
  
  public PlayCardRule(String id) {
	  super(id);
  }

  public void execute(GameState state) {
    if (state.getCurPlayer().getHand().get().size() == 0) {
      state.addChoice(new PassChoice());
    }
    state.getCurPlayer().getHand().get().forEach(card -> state.addChoice(new PlayCardChoice(card)));
  }

  public String prettyPrint() {
    return "Play a card from your hand.";
  }
}
