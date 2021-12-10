public abstract class Choice {

  public Choice() {
  }

  public abstract void execute(GameState state);

  public abstract int[] getPoints();
}
