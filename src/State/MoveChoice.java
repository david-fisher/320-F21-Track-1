package State;

import java.util.*;

import Objects.*;

public class MoveChoice extends Choice {
  private Tile tile;

  public MoveChoice(Tile tile) {
    super();
    this.tile = tile;
  }

  public Tile getTile() {
    return tile;
  }

  public void execute(GameState state) {
    state.getCurPlayer().moveTo(this.tile);
    tile.executeRules(state);
  }

  public String toString() {
    return "(" + getPoints()[0] + "-" + getPoints()[1] + " Points) Move to " + tile.toString();
  }

  public int[] getPoints() {
    return tile.getScore();
  }
}
