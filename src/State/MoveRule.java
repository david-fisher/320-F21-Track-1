public class MoveRule extends Rule {
  int repeat; // 1, 2, 3, .... , MAX
  RNG stepCount; // Dice, Spinner
  ArrayList<Tile> destinations = new ArrayList<Tile>();

  public MoveRule(Tile destination) {
    super();
    this.stepCount = 0;
    this.destinations.add(destination);
  }

  public MoveRule(int repeat, RNG stepCount) {
    super();
    this.repeat = repeat;
    this.stepCount = stepCount;
  }

  public void execute(GameState state) {
    Player player = state.getCurPlayer();
    if (stepCount > 0) {
      destinations.addAll(state.getNextTiles(player.getTile(), stepCount)); 
    }
    destinations.forEach(tile -> state.addChoice(new MoveChoice(destination)));
  }
}
