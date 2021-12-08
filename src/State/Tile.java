import java.util.*;

/*
  A Tile is a vertex in a directed graph (the Board). It has Player occupants and neighbors,
  which are Tiles that are connected to it by an outgoing edge. It extends RuleList, so it
  has a list of rules that can be executed as one.
*/

public class Tile extends RuleList {
  private ArrayList<Tile> neighbors;

  public Tile() {
    this(0);
  }
   
  public Tile(ArrayList<Rule> rules) {
    this(rules, 0);
  }

  public Tile(int constant) {
    this(new ArrayList<Rule>(), constant);
  }

  public Tile(int[] range) {
    this(new ArrayList<Rule>(), range);
  }

  public Tile(ArrayList<Rule> rules, int constant) {
    super(rules, constant);
    this.neighbors = new ArrayList<Tile>();
  }

  public Tile(ArrayList<Rule> rules, int[] range) {
    super(rules, range);
    this.neighbors = new ArrayList<Tile>();
  }

  public void setNeighbors(ArrayList<Tile> neighbors) {
    this.neighbors = neighbors;
  }

  public ArrayList<Tile> getNeighbors() {
    return neighbors;
  }
}
