package GameEditor.Controllers;

import java.util.HashMap;

//TODO initialize localStorage
/*
    - be able to open a previously created game, re-edit it using this.
    -get based on key value pairs
    -find the game based on ID
 */
public class LocalStorage {
    private static LocalStorage single_instance = null;
    public HashMap<String, Object> storage;

    private LocalStorage()
    {
        storage = new HashMap<String, Object>();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static LocalStorage getInstance()
    {
        if (single_instance == null)
            single_instance = new LocalStorage();

        return single_instance;
    }

    public static void reset() {
        if (single_instance != null) single_instance.storage = new HashMap<String, Object>();
    }
}
