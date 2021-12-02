package GameEditor.Controllers;

import java.util.HashMap;


public class LocalStorage {
    private static LocalStorage single_instance = null;
    public HashMap<String, Object> storage;

    private LocalStorage()
    {
        storage = new HashMap<String, Object>();
    }

    // Static method
    // Static method to create instance of Singleton class
    public static LocalStorage LocalStorage()
    {
        if (single_instance == null)
            single_instance = new LocalStorage();

        return single_instance;
    }
}
