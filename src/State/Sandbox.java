package State;

import Objects.*;
import java.util.*;

public class Sandbox {
  public static void main(String[] args) {
    // Instantiate list of players. [unused?]
    ArrayList<Player> players = new ArrayList<Player>();
    // Instantiate Tile 1 with constructor argument constant=1, fixing its score to
    // 1.
    Tile t1 = new Tile(1);
    // Instantiate list of rules for Tile 2.
    ArrayList<Rule> t2Rule = new ArrayList<Rule>();
    // Add a DrawCardRule to the list with constructor argument repeat=2, meaning 2
    // cards can be drawn.
    t2Rule.add(new DrawCardRule(2));
    // Instantiate Tile 2 with rules=t2Rule and constant=2.
    Tile t2 = new Tile(t2Rule, 2);
    // Instantiate Tile 3 with constant=3.
    Tile t3 = new Tile(3);
    // Instantiate list of rules for Tile 4.
    ArrayList<Rule> t4Rule = new ArrayList<Rule>();
    // Add a new MoveRule to t4Rule with argument destination=t2.
    t4Rule.add(new MoveRule(t2));
    // Instantiate Tile 4 with rules t4Rule and RNG score range between 4 and 9.
    Tile t4 = new Tile(t4Rule, new int[] { 4, 9 });
    // Set t1's neighbor to t2.
    t1.setNeighbors(new ArrayList<Tile>(Arrays.asList(t2)));
    // Set t2's neighbors to t3 and t4.
    t2.setNeighbors(new ArrayList<Tile>(Arrays.asList(t3, t4)));
    // Set t3's neighbor to t4.
    t3.setNeighbors(new ArrayList<Tile>(Arrays.asList(t4)));
    // Add the tiles to a list of tiles.
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    tiles.add(t1);
    tiles.add(t2);
    tiles.add(t3);
    tiles.add(t4);

    // Add a new player to the player list with starting tile t1. [unused?]
    players.add(new Player(t1));

    // Instantiate a board with tiles t1 through t4 and starting tile t1.
    Board board = new Board(Arrays.asList(t1, t2, t3, t4), t1);

    // Instantiate a new player to start on the board (duplicate?).
    Player p1 = new Player(board.getStartTile());

    // Instantiate Card 1 with score constant 5.
    Card card1 = new Card(5);
    // Instantiate Card 2 with score range 2-11.
    Card card2 = new Card(new int[] { 2, 11 });

    // Instantiate deck holding cards 1 and 2.
    Deck deck = new Deck(new ArrayList<Card>(Arrays.asList(card1, card2)));

    // Instantiate list of rules containing: PlayCardRule, MoveRule with 2 steps,
    // DrawCardRule with repeat=2, and another PlayCardRule.
    ArrayList<Rule> rules = new ArrayList<Rule>(Arrays.asList(
        new PlayCardRule(), new MoveRule(2), new DrawCardRule(2), new PlayCardRule()));

    // Instantiate new GameState with Player 1, board, deck, rules, and 100 points
    // to win.
    GameState state = new GameState(
        new ArrayList<Player>(Arrays.asList(p1)), board, deck, rules, 100);

    // Make the first progression in the game.
    List<Choice> choices = state.progressGame();
    // Get user input from console.
    Scanner input = new Scanner(System.in);
    // While the best choice is not a winning choice,
    while (!(choices.get(0) instanceof WinChoice)) {
      // Print the choices to console.
      System.out.println("Choices: ");
      for (int i = 0; i < choices.size(); i++) {
        System.out.println(i + ": " + choices.get(i).toString());
      }
      // Print the current player's points.
      printPoints(state.getCurPlayer());
      // Let the user choose a choice as input.
      int option = input.nextInt();
      // Progress the game, giving the next set of choices.
      choices = state.progressGame(option);
    }
    // Close the console input.
    input.close();

    // Print the winning player's points.
    printPoints(state.getCurPlayer());
  }

  // Helper method to print the total points for a player.
  private static void printPoints(Player player) {
    System.out.println("Total Points: " + player.getPoints());
  }package State;

public class Sandbox {
  
}
