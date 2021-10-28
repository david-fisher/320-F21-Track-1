import java.io.*;
import java.util.Arrays;

class Token {
    String type, name;
    int quantity, value;
    Pair<String, int>[] movement;
    Object visuals;
    Token[] cards;

    GameToken() {
        this.type = null;
        this.name = "undefined";
        this.quantity = 0;
        this.value = 0;
        this.movement = null;
        this.visuals = null;
        this.cards = new Token[0];
    }

    GameToken(String newType, String newName, int newQuantity, int newValue, Pair<String, int>[] newMovement, Object newVisuals) {
        this.type = newType;
        this.name = newName;
        this.quantity = newQuantity;
        this.movement = newMovement;
        this.visuals = newVisuals;
        this.cards = new Token[0];
    }

    public void addCard(Token newCard) {
        this.cards = Arrays.copyOf(this.cards, this.cards.length+1);
        this.cards[this.cards.length-1] = newCard;
    }

}
