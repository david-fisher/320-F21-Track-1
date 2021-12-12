package objects;

import java.util.ArrayList;

public class Player extends Saveable {
    private int score;
    private Tile currentTile;
    private Deck hand;
    private String name;

    public Player() {
        super();
        this.isAI = false;
        this.score = 0;
        this.currentTile = null;
        this.hand = new Deck();
    }

    public Player(int score) {
        super();
        this.isAI = false;
        this.score = score;
        this.currentTile = null;
        this.hand = new Deck();
    }

    public Player(Tile currentTile) {
        super();
        this.isAI = false;
        this.score = 0;
        this.currentTile = currentTile;
        this.hand = new Deck();
    }

    public Player(String id, int score, Tile currentTile, Deck hand) {
        super(id);
        this.isAI = false;
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

    // Prerequisite: the tile is on the board.
    public void moveTo(Tile tile) {
        this.currentTile = tile;
        // userInterface.movePlayer(this, tile);
    }

    public int getScore() {
        return this.score.get_score();
    }

    public int updateScore(int change) {
        this.score.update(change);
        return get_score();
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
}