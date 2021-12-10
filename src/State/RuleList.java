import java.util.*;

/*
  Tiles and cards are pretty similar. They can both:
  - Move the player to a tile
  - Give the player points
  - Give the player a card
  Thus, the "RuleList" abstract class. The difference is that a player must land on a Tile
  but play a Card. Think of a Card like an invisible Tile that the player can teleport to.
*/
public abstract class RuleList extends Displayable {
  private ArrayList<Rule> rules;
  private RNG score;

  public RuleList(ArrayList<Rule> rules, int constant) {
    super();
    this.rules = rules;
    this.score = new RNG(constant);
  }

  public RuleList(ArrayList<Rule> rules, int[] range) {
    super();
    this.rules = rules;
    this.score = new RNG(range);
  }

  public void setRules(ArrayList<Rule> rules) {
    this.rules = rules;
  }

  public void executeRules(GameState state) {
    state.enqueueRules(rules);
    state.getCurPlayer().deltaScore(score.pop());
  }
  
  public int[] getScore() {
    return score.getRange();
  }
}
