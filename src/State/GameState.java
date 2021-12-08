import java.util.*;

public class GameState {
  private List<Player> players;
  private Player curPlayer;
  private Board board;
  private Deck deck;
  private RNG rng;

  public GameState(List<Player> players, Board board, Deck cards, double[] rngRange) {
    this.players = new ArrayList<Player>(players);
    this.curPlayer = this.players.get(0);
    this.board = board;
    this.deck = cards;
    this.rng = new RNG(rngRange);

    this.players.forEach(player -> player.moveTo(board.getStartTile()));
  }

  public GameState(ArrayList<Player> players, Token gameToLoad){
    // TODO: Write this function for Omnicron to init game with
  }

  /*
   * Takes in the choice the last player has made and returns a new list of
   * choices for the next player. This is the public-facing API of GameState.
   */
  public List<Choice> progressGame(Choice choice) {
    choice.execute(this); // Execute the choice the last player has made.
    this.curPlayer = this.nextPlayer(); // Advance the current player.
    // this.debugPrint(); // Uncomment this line to print debug info.
    return this.getChoices(); // Return the list of choices available to the next player.
  }

  // Initial turn.
  public List<Choice> progressGame() {
    return this.getChoices();
  }

  private List<Choice> getChoices() {
    List<Choice> choices = new ArrayList<Choice>();

    int dieRoll = this.rng.ran_int()[0];
    choices.add(new MoveChoice(this.getNextTiles(this.curPlayer.position, dieRoll)));

    if (!this.deck.isEmpty()) {
      choices.add(new DrawCardChoice(this.deck.peek()));
    }

    if (!this.curPlayer.getHand().isEmpty()) {
      choices.add(new PlayCardChoice(this.curPlayer.getHand()));
    }

    return choices;

  }

  // gets every tile which is steps distance from curTile. We disallow looping
  // back to end on curTile and we disallow doubling back on an undirected edge.
  private ArrayList<Tile> getNextTiles(Tile curTile, int steps) {
    ArrayList<Tile> output = tileSearch(curTile, steps, null);
    output.remove(curTile);
    return output;
  }

  // helper for getNextTiles, recursively finds all the tiles steps distance from
  // curTile without doubling back.

  private ArrayList<Tile> tileSearch(Tile curTile, int steps, Tile lastTile) {
    ArrayList<Tile> output = new ArrayList<Tile>();
    if (steps == 1) {
      output = curTile.getNeighbors();
      if (lastTile != null) {
        output.remove(lastTile);
      }
      return output;
    }
    for (Tile neighbor : curTile.getNeighbors()) {
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

  // Removes and returns the top card from the deck.
  public Piece popDeck() {
    return this.deck.pop();
  }

  public Deck getDeck() {
    return this.deck;
  }

  public void debugPrint() {
    System.out.println("\nPlayer " + (this.players.indexOf(this.curPlayer) + 1) + "'s turn.");
    for (Player player : this.players) {
      System.out.println("Player " + (this.players.indexOf(player) + 1) + " is at Tile "
          + (this.board.tiles.indexOf(player.position) + 1) + ".");
    }
  }

  public Board getBoard() {
    return this.board;
  }
}
