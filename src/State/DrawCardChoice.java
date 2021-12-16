package State;

import Objects.*;

public class DrawCardChoice extends Choice {
  private Deck deck;
  private int amount;

  public DrawCardChoice(Deck deck, int amount) {
    super();
    this.deck = deck;
    this.amount = amount;
  }

  public String getPrettyData(GameState state) {
    return "Card draw options: ";
  }

  public Deck getDeck() {
    return deck;
  }

  public void execute(GameState state) {
    for (int i = 0; i < amount; i++) {
      state.getCurPlayer().addCard(state.popDeck());
    }
  }

  public String toString() {
    return "Draw " + amount + " from the deck.";
  }

  public int[] getPoints() {
    return new int[] { 0, 0 };
  }
}
