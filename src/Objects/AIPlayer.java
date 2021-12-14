package Objects;

import java.util.*;

import State.*;

public class AIPlayer extends Player {

  public AIPlayer(Tile startPos, Hashtable<String, String> attributes) {
    super(startPos, attributes);
  }

  public Choice choose(ArrayList<Choice> choices) {
    Choice output = choices.get(0);
    for (Choice c : choices) {
      if (c.getPoints()[1] > output.getPoints()[1]) {
        output = c;
      }
    }
    return output;
  }

}
