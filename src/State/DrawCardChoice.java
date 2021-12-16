package State;

import Objects.*;

public class DrawCardChoice extends Choice {
  private Deck deck;
  private int amount;
  private Player player;

  public DrawCardChoice(Deck deck, int amount, Player player) {
    super();
    this.player = player;
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
      player.addCard(state.popDeck());
    }
  }

  public String toString() {
    return "Draw " + amount + " from the deck.";
  }

  public int[] getPoints() {
    return new int[] { 0, 0 };
  }
}
