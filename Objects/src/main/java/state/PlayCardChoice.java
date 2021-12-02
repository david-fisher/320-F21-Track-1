public class PlayCardChoice extends Choice {
  private Card card;

  public PlayCardChoice(Card card) {
    super();
    this.card = card;
  }

  public String getPrettyData(GameState state) {
    return "";
  }

  public Card getCard() {
    return card;
  }

  public void execute(GameState state) {
    this.card.executeRules(state);
  }
}
