package objects;

import java.util.*;

public class Saveable {
    public String ID;
    public String get_id(){return this.ID;}
    public Hashtable<String, String> to_json(){
        Hashtable<String, String> result = new Hashtable<String, String>();
        result.put("id", this.ID);
        return result;
    }
}