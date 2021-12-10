import java.util.*;

public class Deck extends Saveable{
  private ArrayList<Card> cards;
  private ArrayList<Card> cardQueue;

  public Deck(){
    this(new ArrayList<Card>());
  }

  public Deck(ArrayList<Card> cards){
    super();
    this.cards = cards;
    resetDeck();
  }

  // Stack functionality
  public boolean isEmpty(){
    return this.cards.isEmpty();
  }

  public Card peek(){
    return this.cards.get(0);
  }

  public Card pop(){
    if (this.cardQueue.isEmpty()) {
      resetDeck();
    }
    Card output = cardQueue.get(0);
    cardQueue.remove(0);
    return output;
  }

  public void resetDeck() {
    cardQueue = new ArrayList<Card>(cards);
    Collections.shuffle(cardQueue);
  }
    
  public void removeCard(Card card) {
    cards.remove(card);
  }
 
  public void addCard(Card card) {
    cards.add(card);
  }

  public ArrayList<Card> get() {
    return cards;
  }
}
