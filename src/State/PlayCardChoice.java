package State;

import Objects.*;

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
    state.getCurPlayer().removeCard(card);
    card.executeRules(state);
  }

  public String toString() {
    return "(" + getPoints()[0] + " to " + getPoints()[1] + " Points) Play " + card.toString();
  }

  public int[] getPoints() {
    return card.getScore();
  }
}
