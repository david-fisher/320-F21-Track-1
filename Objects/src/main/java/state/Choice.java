public abstract class Choice {

  public Choice() {
  }

  // For debug printing.
  public abstract String getPrettyData(GameState state);

  public abstract void execute(GameState state);
}
