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
  List<Rule> rules;

  public RuleList() {
    super();
    rules = new ArrayList<Rule>();
  }

  public void executeRules(GameState state) {
    rules.forEach(rule -> rule.execute(state));
  }
}
