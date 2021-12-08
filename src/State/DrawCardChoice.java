public class DrawCardChoice extends Choice {
  Deck deck;
  
  public DrawCardChoice(Deck deck) {
    super();
    this.deck = deck;
  }

  public String getPrettyData(GameState state) {
    return "Card draw options: ";
  }

  public Deck getDeck() {
    return deck;
  }

  public void execute(GameState state) {
    state.getCurPlayer().addCard(state.popDeck());
  }
}
