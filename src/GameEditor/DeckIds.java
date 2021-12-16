package GameEditor;

import Objects.Deck;

import java.util.ArrayList;
import java.util.Hashtable;

public final class DeckIds {
    private Hashtable<String, Deck> DeckIds = new Hashtable<String, Deck>();
    private final static DeckIds INSTANCE = new DeckIds();

    private DeckIds() {}

    public static DeckIds getInstance() {
        return INSTANCE;
    }

    public void addId(String u, Deck obj) {
        if (this.DeckIds.containsKey(u)) {
            this.DeckIds.remove(u);
        }
        this.DeckIds.put(u, obj);
        return;
    }

    public void remove(String u) {
        this.DeckIds.remove(u);
    }

    public ArrayList<String> Values() {
        return new ArrayList<String>(this.DeckIds.keySet());
    }

    public Deck getVal(String id) {
        return this.DeckIds.get(id);
    }
}
