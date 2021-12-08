public class DrawCardRule extends Rule {
  int repeat; // 1, 2, 3, .... , MAX

  public DrawCardRule(int repeat) {
    this.repeat = repeat;
  }

  public void execute(GameState state) {
    state.getCurPlayer().addCard(state.popDeck());
  }
}
