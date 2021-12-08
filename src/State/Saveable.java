import java.util.*;

public abstract class Saveable {
  protected String ID;

  public Saveable() {
    this.ID = UUID.randomUUID().toString();
  }

  public String get_id() {
    return this.ID;
  }
}
