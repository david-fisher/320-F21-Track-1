package state;

public class PassChoice extends Choice {

  public PassChoice() {
  }

  public void execute(GameState state) {
  }

  public int[] getPoints() {
    return new int[] { 0, 0 };
  }

  public String toString() {
    return "Pass";
  }
}
