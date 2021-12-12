package State;

import java.util.*;

import Objects.*;

public class Demo {
  public static void main(String[] args) {
    ArrayList<Player> players = new ArrayList<Player>();

    Tile t1 = new Tile(1);
    ArrayList<Rule> t2Rule = new ArrayList<Rule>();
    t2Rule.add(new DrawCardRule(2));
    Tile t2 = new Tile(t2Rule, 2);
    Tile t3 = new Tile(3);
    ArrayList<Rule> t4Rule = new ArrayList<Rule>();
    t4Rule.add(new MoveRule(t2));
    Tile t4 = new Tile(t4Rule, new int[] { 4, 9 });
    t1.setNeighbors(new ArrayList<Tile>(Arrays.asList(t2)));
    t2.setNeighbors(new ArrayList<Tile>(Arrays.asList(t3, t4)));
    t3.setNeighbors(new ArrayList<Tile>(Arrays.asList(t4)));
    ArrayList<Tile> tiles = new ArrayList<Tile>();
    tiles.add(t1);
    tiles.add(t2);
    tiles.add(t3);
    tiles.add(t4);

    players.add(new Player(t1));

    Board board = new Board(Arrays.asList(t1, t2, t3, t4), t1);

    Player p1 = new Player(board.getStartTile());

    Card card1 = new Card(5);
    Card card2 = new Card(new int[] { 2, 11 });

    Deck deck = new Deck(new ArrayList<Card>(Arrays.asList(card1, card2)));

    ArrayList<Rule> rules = new ArrayList<Rule>(
        Arrays.asList(new PlayCardRule(), new MoveRule(2), new DrawCardRule(2), new PlayCardRule()));

    GameState state = new GameState(new ArrayList<Player>(Arrays.asList(p1)), board, deck, rules, 100);

    List<Choice> choices = state.progressGame();
    Scanner input = new Scanner(System.in);
    while (!(choices.get(0) instanceof WinChoice)) {
      System.out.println("Choices: ");
      for (int i = 0; i < choices.size(); i++) {
        System.out.println(i + ": " + choices.get(i).toString());
      }
      printPoints(state.getCurPlayer());
      int option = input.nextInt();
      choices = state.progressGame(option);
    }
    input.close();

    printPoints(state.getCurPlayer());
  }

  private static void printPoints(Player player) {
    System.out.println("Total Points: " + player.getScore());
  }
}
