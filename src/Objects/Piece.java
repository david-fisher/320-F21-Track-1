package Objects;

import java.util.*;
import javafx.scene.image.ImageView;

public class Piece extends GameObject {
    private transient ImageView image;

    public Piece(ArrayList<Rule> rules, Hashtable<String, String> attributes) {
        super(rules, attributes, 0);
        this.image = new ImageView();
    }

    public Piece(int x, int y, ArrayList<Rule> rules, Hashtable<String, String> attributes) {
        super(x, y, rules, attributes, 0);
        this.image = new ImageView();
    }

    public Piece(int x, int y, ArrayList<Rule> rules, Hashtable<String, String> attributes, ImageView image) {
        super(x, y, rules, attributes, 0);
        this.image = image;
    }

    public Piece(String id, int x, int y, ArrayList<Rule> rules, Hashtable<String, String> attributes,
            ImageView image) {
        super(id, x, y, rules, attributes, 0);
        this.image = image;
    }

<<<<<<< HEAD:Objects/src/main/java/objects/Piece.java
    // card constructor, does not have coordination attributes 
    Piece(ArrayList<Rule> rules, Hashtable<String,String> attributes, ImageView image){
        super(rules, attributes);
        this.image = image;
    }
    
    public ImageView update_image(ImageView image){
=======
    public ImageView update_image(ImageView image) {
>>>>>>> main:src/Objects/Piece.java
        this.image = image;
        return this.image;
    }

    public ImageView remove_image() {
        this.image.setImage(null);
        return this.image;
    }

    public ImageView get_img() {
        return this.image;
    }
}