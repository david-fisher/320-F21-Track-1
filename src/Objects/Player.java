package Objects;

import java.util.Hashtable;

public class Player extends Savable {
    private int score;
    private Tile currentTile;
    private Deck hand;
    private Hashtable<String, String> attributes;

    public Player(Hashtable<String, String> attributes) {
        super();
        this.attributes = attributes;
        this.score = 0;
        this.currentTile = null;
        this.hand = new Deck();
    }

    public Player(int score, Hashtable<String, String> attributes) {
        super();
        this.attributes = attributes;
        this.score = score;
        this.currentTile = null;
        this.hand = new Deck();
    }

    public Player(Tile currentTile, Hashtable<String, String> attributes) {
        super();
        this.attributes = attributes;
        this.score = 0;
        this.currentTile = currentTile;
        this.hand = new Deck();
    }

    public Player(String id, int score, Tile currentTile, Deck hand, Hashtable<String, String> attributes) {
        super(id);
        this.attributes = attributes;
        this.score = score;
        this.currentTile = currentTile;
        this.hand = hand;
    }

    public Deck getHand() {
        return hand;
    }

    public void addCard(Card card) {
        this.hand.addCard(card);
    }

    public void removeCard(Card card) {
        this.hand.removeCard(card);
    }

    public Hashtable<String, String> getAttributes() {
        return attributes;
    }

    // Prerequisite: the tile is on the board.
    public void moveTo(Tile tile) {
        this.currentTile = tile;
        // userInterface.movePlayer(this, tile);
    }

    public int getScore() {
        return this.score;
    }

    public int updateScore(int change) {
        this.score = change;
        return getScore();
    }

    public void deltaScore(int delta) {
        this.score += delta;
    }

    public Tile getTile() {
        return this.currentTile;
    }

    public void updateTile(Tile new_tile) {
        this.currentTile = new_tile;
    }

    public Hashtable<String, String> getAttributes() {
        return this.attributes;
    }
}