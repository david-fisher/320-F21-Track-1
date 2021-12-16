package Objects;

import State.*;

public class DrawCardRule extends Rule {
  private int repeat; // 1, 2, 3, .... , MAX

  public DrawCardRule() {
    this(1);
  }

  public DrawCardRule(int repeat) {
    this.repeat = repeat;
  }

  public void execute(GameState state) {
    state.addChoice(new DrawCardChoice(state.getDeck(), repeat, state.getCurPlayer()));
  }

  public String prettyPrint() {
    return "Draw " + repeat + " card" + (repeat > 1 ? "s" : "") + " from the deck.";
  }
}
