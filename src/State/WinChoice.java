import java.util.*;

public class WinChoice extends Choice {
  private Player player;

  public WinChoice(Player player) {
    super();
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  public void execute(GameState state) {}

  public String toString() {
    return player.toString() + " wins";
  }

  public int[] getPoints() {
    return new int[] {0,0};
  }
}
