import java.util.*;

// Anything extending this can and will be displayed in the UI.
public abstract class Displayable extends Saveable {
  private Map<String, String> UIAttributes;

  public Displayable() {
    super();
    this.UIAttributes = new HashMap<String, String>();
  }
}
