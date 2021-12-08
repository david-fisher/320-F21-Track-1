package src.main.java.objects;

import java.util.*;

public class Saveable {
    private final String ID;
    
    public Saveable(){
        this.ID = UUID.randomUUID().toString();
    }
    
    public Saveable(String id){
        this.ID = id;
    }
    
    public String get_id(){return this.ID;}
}