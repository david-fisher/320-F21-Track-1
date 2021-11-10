import java.io.*;
import java.util.Arrays;

class RNG {
    String type, name;
    int quantity, min, max;
    Token deck;

    void GameToken() {
        this.type = null;
        this.name = "undefined";
        this.quantity = 0;
        this.value = 0;
        this.movement = null;
        this.visuals = null;
        this.cards = new Token[0];
    }

    void GameToken(String newType, String newName, int newQuantity, int newValue, Pair<String, int>[] newMovement, Object newVisuals) {
        this.type = newType;
        this.name = newName;
        this.quantity = newQuantity;
        this.movement = newMovement;
        this.visuals = newVisuals;
        this.cards = new Token[0];
    }

}
