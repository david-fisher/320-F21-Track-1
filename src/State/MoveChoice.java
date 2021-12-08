import java.util.*;

public class MoveChoice extends Choice {
  private Tile tile;

  public MoveChoice(Tile tile) {
    super();
    this.tile = tile;
  }

  // This is for debug printing.
  public String getPrettyData(GameState state) {
    @SuppressWarnings("unchecked")
    List<Tile> tiles = (List<Tile>) this.data;
    // System.out.println(tiles);
    ArrayList<String> tileStrings = new ArrayList<String>();
    for (Tile tile : tiles) {
      tileStrings.add(Integer.toString(state.getBoard().tiles.indexOf(tile) + 1));
    }
    return "Movement options: " + tileStrings.toString();
  }

  public Tile getTile() {
    return tile;
  }

  public void execute(GameState state) {
    state.getCurPlayer().moveTo(this.tile);
  }
}
