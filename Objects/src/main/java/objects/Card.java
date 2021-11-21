import java.util.*;

/*
  A Card has the following properties:
  - It is a RuleList, meaning it has a list of Rules that can be executed as one
  - It can be drawn from a draw pile by a player.
  - It can be used by a player.
  When a Card is used, its Rules are executed and then it is discarded from the player's hand.
*/
public class Card extends RuleList {
  public Card() {
    super();
    this.rules = new ArrayList<Rule>();
  }
}
