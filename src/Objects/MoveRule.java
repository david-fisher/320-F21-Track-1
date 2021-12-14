package Objects;

import java.util.*;

import State.*;

public class MoveRule extends Rule {
  private RNG stepCount; // Dice, Spinner
  private ArrayList<Tile> destinations = new ArrayList<Tile>();

  public MoveRule(Tile destination) {
    super();
    this.stepCount = null;
    this.destinations.add(destination);
  }
  
  public MoveRule(RNG stepCount) {
    super();
    this.stepCount = stepCount;
  }

  public MoveRule(int stepCount) {
    super();
    this.stepCount = new RNG(stepCount);
  }

  public void execute(GameState state) {
    Player player = state.getCurPlayer();
    if (stepCount != null) {
      destinations.addAll(state.getNextTiles(player.getTile(), stepCount.randInt()));
    }
    destinations.forEach(tile -> state.addChoice(new MoveChoice(tile)));
    if (destinations.size() == 0) {
      state.addChoice(new PassChoice());
    }
  }
}
