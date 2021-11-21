public class DrawCardChoice extends Choice {
  public DrawCardChoice(Object data) {
    super(data);
  }

  public String getPrettyData(GameState state) {
    return "Card draw options: ";
  }

  public void execute(GameState state) {
    state.getCurPlayer().addCard(state.popDeck());
  }
}
