package Objects;
<<<<<<< HEAD
=======

>>>>>>> d190abaef0a1e0d85e9819a610a52046b3b4d07b
import State.*;

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
