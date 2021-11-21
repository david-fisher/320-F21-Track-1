public class PlayCardChoice extends Choice {
  private Card card;

  public PlayCardChoice(Object data) {
    super(data);
  }

  public String getPrettyData(GameState state) {
    return "";
  }

  public void chooseCard(Card card) {
    this.card = card;
  }

  public void execute(GameState state) {
    this.card.executeRules(state);
  }
}
