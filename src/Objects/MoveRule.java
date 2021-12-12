package objects;

import state.*;
import java.util.*;

public class MoveRule extends Rule {
  private int stepCount; // Dice, Spinner
  private ArrayList<Tile> destinations = new ArrayList<Tile>();

  public MoveRule(Tile destination) {
    super();
    this.stepCount = 0;
    this.destinations.add(destination);
  }

  public MoveRule(int stepCount) {
    super();
    this.stepCount = stepCount;
  }

  public void execute(GameState state) {
    Player player = state.getCurPlayer();
    if (stepCount > 0) {
      destinations.addAll(state.getNextTiles(player.getTile(), stepCount));
    }
    destinations.forEach(tile -> state.addChoice(new MoveChoice(tile)));
    if (destinations.size() == 0) {
      state.addChoice(new PassChoice());
    }
  }
}
