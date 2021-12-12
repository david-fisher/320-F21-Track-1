package state;

import objects.*;
import java.util.*;

public class GameState {
  private List<Player> players;
  private Player curPlayer;
  private Board board;
  private Deck deck;
  private ArrayList<Rule> rules;
  private int winCondition;

  private Rule curRule;

  private ArrayList<Choice> choices;
  private ArrayList<Rule> ruleQueue;

  public GameState(List<Player> players, Board board, Deck cards, ArrayList<Rule> rules, int winCondition) {
    this.players = new ArrayList<Player>(players);
    this.curPlayer = this.players.get(0);
    this.board = board;
    this.deck = cards;
    this.rules = rules;
    this.curRule = this.rules.get(0);
    this.winCondition = winCondition;

    choices = new ArrayList<Choice>();
    resetRuleQueue();

    this.players.forEach(player -> player.moveTo(board.getStartTile()));
  }

  /*
   * Takes in the choice the last player has made and returns a new list of
   * choices for the next player. This is the public-facing API of GameState.
   */
  public List<Choice> progressGame(int choice) {
    choices.get(choice).execute(this); // Execute the choice the last player has made.
    choices.clear();
    for (Player p : players) {
      if (p.getScore() > winCondition) {
        choices.add(new WinChoice(p));
        return choices;
      }
    }
    curRule.execute(this);
    curRule = nextRule();
    return choices; // Return the list of choices available to the next player.
  }

  // Initial turn.
  public List<Choice> progressGame() {
    curRule.execute(this);
    curRule = nextRule();
    return choices;
  }

  public void addChoice(Choice choice) {
    choices.add(choice);
  }

  // gets every tile which is steps distance from curTile. We disallow looping
  // back to end on curTile and we disallow doubling back on an undirected edge.
  public ArrayList<Tile> getNextTiles(Tile curTile, int steps) {
    ArrayList<Tile> output = tileSearch(curTile, steps, null);
    output.remove(curTile);
    return output;
  }

  // helper for getNextTiles, recursively finds all the tiles steps distance from
  // curTile without doubling back.

  private ArrayList<Tile> tileSearch(Tile curTile, int steps, Tile lastTile) {
    ArrayList<Tile> output = new ArrayList<Tile>();
    if (steps == 1) {
      output = curTile.get_neighbors();
      if (lastTile != null) {
        output.remove(lastTile);
      }
      return output;
    }
    for (Tile neighbor : curTile.get_neighbors()) {
      if (neighbor != lastTile) {
        merge(output, tileSearch(neighbor, steps - 1, curTile));
      }
    }
    return output;
  }

  // helper for tileSearch, puts all elements from input2 into input1 without
  // duplicates.
  private <T> void merge(ArrayList<T> input1, ArrayList<T> input2) {
    for (T element : input2) {
      if (!input1.contains(element)) {
        input1.add(element);
      }
    }
  }

  public Player getCurPlayer() {
    return this.curPlayer;
  }

  private Player nextPlayer() {
    int curPlayerInd = this.players.indexOf(this.curPlayer);
    if (curPlayerInd == this.players.size() - 1) { // If it's the last player in the list,
      return this.players.get(0); // loop over to the first player.
    }
    return this.players.get(curPlayerInd + 1); // Otherwise, return the next player in the list.
  }

  private void resetRuleQueue() {
    ruleQueue = new ArrayList<Rule>(rules);
  }

  private Rule nextRule() {
    ruleQueue.remove(0);
    if (ruleQueue.isEmpty()) {
      curPlayer = nextPlayer();
      resetRuleQueue();
    }
    return ruleQueue.get(0);
  }

  public void enqueueRules(ArrayList<Rule> input) {
    ArrayList<Rule> output = new ArrayList<Rule>(input);
    output.addAll(ruleQueue);
    ruleQueue = output;
  }

  // Removes and returns the top card from the deck.
  public Card popDeck() {
    return this.deck.pop();
  }

  public Deck getDeck() {
    return this.deck;
  }

  public Board getBoard() {
    return this.board;
  }
}
