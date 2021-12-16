package State;

import Objects.*;

import java.util.*;

// This class coordinates launching a new game
public class GameManager {
    private static Board board;

    public GameManager(Board b) {
        board = b;
    }

    // For Omnicron to give us the players, and we'll give them a GameState to
    // work with.
    public static GameState startGame(List<Player> players) {
        if (board == null) {
            return null;
        }

        for (Player player : players) {
            player.updateTile(board.getStartTile());
        }

        return new GameState(players, board);
    }
}
