import java.util.*;

public class Board extends Displayable {
  private List<Tile> tiles;
  private Tile startTile;

  // Prerequisite: startTile is a member of tiles.
  public Board(Collection<Tile> tiles, Tile startTile) {
    super();
    this.tiles = new ArrayList<Tile>(tiles);
    if (!this.tiles.contains(startTile)) {
      throw new IllegalArgumentException("The start tile must be a member of the board's tiles.");
    }
    this.startTile = startTile;
  }

  public Tile getStartTile() {
    return this.startTile;
  }

  public boolean containsTile(Tile tile) {
    return this.tiles.contains(tile);
  }
}
