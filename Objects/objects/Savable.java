package objects;

import java.util.*;

public class Savable {
    private final String ID;
    public Savable(){
        this.ID = UUID.randomUUID().toString();
    }
    public Savable(String id){
        this.ID = id;
    }
    
    public String get_id(){return this.ID;}
}