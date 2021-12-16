package Objects;

import java.util.*;

//Class to generate a unique ID
public class Saveable {
    private final String ID;

    // Generating a new unique ID
    public Saveable() {
        this.ID = UUID.randomUUID().toString();
    }

    public Saveable(String id) {
        this.ID = id;
    }

    public String get_id() {
        return this.ID;
    }
}
