package Objects;

import java.util.*;

public class Deck extends Saveable {
    // cards: list of cards in the deck
    // cardQueue: list of cards in order to be drawn
    private ArrayList<Card> cards;
    private ArrayList<Card> cardQueue;

    public Deck() {
        super();
        this.cards = new ArrayList<Card>();
    }

    public Deck(ArrayList<Card> cards) {
        super();
        this.cards = cards;
    }

    // Unused constructors

    // public Deck(String id, ArrayList<Piece> pieces){
    // super(id);
    // this.pieces = pieces;
    // }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    public ArrayList<Card> get() {
        return cards;
    }

    public Card peek() {
        return this.cards.get(0);
    }

    public Card pop() {
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

    // Add a new card into the deck
    public ArrayList<Card> addCard(Card updatedCard) {
        try {
            this.cards.indexOf(findCardByID(updatedCard.get_id()));
            System.out.println("Card already exists");
        } catch (Exception e) {
            // If there is an error finding the card then we know that it can be added
            this.cards.add(updatedCard);
        }
        return this.cards;
    }

    // Update a card in the deck
    public ArrayList<Card> updateCard(Card newCard) {
        try {
            int index = this.cards.indexOf(findCardByID(newCard.get_id()));
            this.cards.set(index, newCard);
        } catch (Exception e) {
            System.out.println("Error: Card not found in deck.");
        }
        return this.cards;
    }

    // Add a list of cards into the deck, takes an array list of cards to add
    public ArrayList<Card> addCards(ArrayList<Card> newCards) {
        for (Card newCard : newCards) {
            addCard(newCard);
        }
        return this.cards;
    }

    // remove a card from the deck, takes the card that needs removal of as parem
    public ArrayList<Card> removeCard(Card input) {
        try {
            this.cards.remove(this.cards.indexOf(findCardByID(input.get_id())));
        } catch (Exception e) {
            System.out.println("Error: Unable to remove target card:");
            System.out.println(e);
        }
        return this.cards;
    }

    // Clear all cards from deck
    public ArrayList<Card> removeCards() {
        this.cards.clear();
        return this.cards;
    }

    // Find a card in the deck by ID, return null if not found
    public Card findCardByID(String ID) {
        return this.cards.stream().filter(card -> card.get_id().equals(ID)).findFirst().orElse(null);
    }
}