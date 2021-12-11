import java.util.*;

public class PlayCardRule extends Rule {
  public PlayCardRule() {
    super();
  }

  public void execute(GameState state) {
    if (state.getCurPlayer().getHand().get().size() == 0) {
      state.addChoice(new PassChoice());
    }
    state.getCurPlayer().getHand().get().forEach(card -> state.addChoice(new PlayCardChoice(card)));
  }
}
