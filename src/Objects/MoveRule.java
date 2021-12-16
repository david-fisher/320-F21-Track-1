package Objects;

import State.GameState;
import State.MoveChoice;
import State.PassChoice;

import java.util.ArrayList;

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

  public String prettyPrint() {
    int[] range = stepCount.getRange();
    if (range[0] == range[1]) {
      return "Move " + stepCount.getRange()[0] + " spaces.";
    } else {
      return "Move " + range[0] + " - " + range[1] + " spaces";
    }
  }
}
