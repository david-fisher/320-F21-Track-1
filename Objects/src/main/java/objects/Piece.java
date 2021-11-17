package objects;

import java.util.*;
import javafx.scene.image.ImageView;

public class Piece extends PTile {
    private transient ImageView image;
    
    Piece(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super(x, y, rules, attributes);
    }
    
    Piece(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, ImageView image){
        super(x, y, rules, attributes);
        this.image = image;
    }
    
    Piece(String id, int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, ImageView image){
        super(id, x, y, rules, attributes);
        this.image = image;
    }

    // card constructor
    Piece(ArrayList<Rule> rules, Hashtable<String,String> attributes, ImageView image){
        super(rules, attributes);
        this.image = image;
    }
    
    public ImageView update_image(ImageView image){
        this.image = image;
        return this.image;
    }
    
    public ImageView remove_image(){
        this.image.setImage(null);
        return this.image;
    }
    
    public ImageView get_img(){
        return this.image;
    }
}