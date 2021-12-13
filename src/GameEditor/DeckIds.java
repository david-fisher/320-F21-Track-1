package GameEditor;

import java.util.ArrayList;

public final class DeckIds {
    private ArrayList<String> DeckIds = new ArrayList<String>();
    private final static DeckIds INSTANCE = new DeckIds();

    private DeckIds() {}

    public static DeckIds getInstance() {
        return INSTANCE;
    }

    public void addId(String u) {
        this.DeckIds.add(u);
    }

    public void changeId(String oldId, String newId) {
        this.DeckIds.remove(oldId);
        this.addId(newId);
    }

    public ArrayList<String> Ids() {
        return this.DeckIds;
    }
}
