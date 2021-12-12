package objects;

import java.util.*;

/*
  A Card has the following properties:
  - It is a RuleList, meaning it has a list of Rules that can be executed as one
  - It can be drawn from a draw pile by a player.
  - It can be used by a player.
  When a Card is used, its Rules are executed and then it is discarded from the player's hand.
*/
public class Card extends GameObject {
  public Card() {
    this(0);
  }

  public Card(ArrayList<Rule> rules) {
    this(rules, 0);
  }

  public Card(int constant) {
    this(new ArrayList<Rule>(), constant);
  }

  public Card(int[] range) {
    this(new ArrayList<Rule>(), range);
  }

  public Card(ArrayList<Rule> rules, int constant) {
    super(rules, constant);
  }

  public Card(ArrayList<Rule> rules, int[] range) {
    super(rules, range);
  }
}
