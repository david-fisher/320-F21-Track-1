import GameEditor.Controllers.LocalStorage;
import Objects.*;
import State.*;

import java.util.*;

// This class is a singleton that coordinates between different parts of the application.
public class Manager {
    private static Manager instance = null;

    private Manager() {

    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    // The entry point of the application.
    public static void main(String[] args) {

    }

    // To initialize a board created in the board editor.
    public void setBoard(LocalStorage storage) {

    }

    // For Omnicron to give us the players, and we'll give them a GameState to
    // work with.
    public GameState startGame(List<Player> players) {
        return null;
    }
}
