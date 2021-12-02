public class PlayCardRule extends Rule {
  int repeat; // 1, 2, 3, .... , MAX

  public PlayCardRule(int repeat) {
    super();
    this.repeat = repeat;
  }

  public void execute(GameState state) {
    state.getCurPlayer().getHand().forEach(card -> state.addChoice(new PlayCardChoice(card)));
  }
}
