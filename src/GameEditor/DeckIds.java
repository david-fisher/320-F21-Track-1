package GameEditor;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

public final class DeckIds {
    private Hashtable<UUID, String> DeckIds = new Hashtable<UUID, String>();
    private final static DeckIds INSTANCE = new DeckIds();

    private DeckIds() {}

    public static DeckIds getInstance() {
        return INSTANCE;
    }

    public UUID addId(String u) {
        if (this.DeckIds.containsValue(u)) {
            return null;
        }
        UUID temp = UUID.randomUUID();
        this.DeckIds.put(temp, u);
        return temp;
    }

    public void changeId(UUID id, String newId) {
        this.DeckIds.remove(id);
        this.DeckIds.put(id, newId);
    }

    public ArrayList<String> Values() {
        return new ArrayList<String>(this.DeckIds.values());
    }
}
