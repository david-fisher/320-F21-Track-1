import java.util.*;

public class Player extends Displayable {
  private int score;
  private Tile position;
  private Deck hand;

  public Player(Tile startPos) {
    super();
    this.score = 0;
    this.position = startPos;
    this.hand = new Deck();
  }

  public void deltaScore(int delta) {
    this.score += delta;
  }

  // Prerequisite: the tile is on the board.
  public void moveTo(Tile tile) {
    this.position = tile;
    //userInterface.movePlayer(this, tile);
  }

  public Deck getHand() {
    return this.hand;
  }

  public void removeCard(Card card) {
    this.hand.removeCard(card);
  }

  public void addCard(Card card) {
    this.hand.addCard(card);
  }

  public Tile getTile() {
    return position;
  }

  public int getPoints() {
    return score;
  }
}
