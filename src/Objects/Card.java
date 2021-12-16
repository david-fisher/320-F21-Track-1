package Objects;

import java.util.*;
import javafx.scene.image.ImageView;

/*
  A Card has the following properties:
  - It is a RuleList, meaning it has a list of Rules that can be executed as one
  - It can be drawn from a draw pile by a player.
  - It can be used by a player.
  When a Card is used, its Rules are executed and then it is discarded from the player's hand.
*/
public class Card extends GameObject {
    private transient ImageView image;

    public Card() {
        this(0, new ImageView(), null);
    }

    public Card(ImageView image) {
        this(0, image, null);
    }

    public Card(ArrayList<Rule> rules, ImageView image, Hashtable<String, String> attributes) {
        this(rules, 0, image, attributes);
    }

    public Card(int constant, ImageView image, Hashtable<String,String> attributes) {
        this(new ArrayList<Rule>(), constant, image, attributes);
    }

    public Card(int[] range, ImageView image) {
        this(new ArrayList<Rule>(), range, null, image);
    }

    public Card(ArrayList<Rule> rules, int constant, ImageView image, Hashtable<String, String> attributes) {
        super(rules,  attributes, constant);
        this.image = image;
    }

    public Card(ArrayList<Rule> rules, int[] range, Hashtable<String, String> attributes, ImageView image) {
        super(rules, attributes, range);
        this.image = image;
    }

    public ImageView getImage() {
        return this.image;
    }
}