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
        this(0, new ImageView());
    }

    public Card(ImageView image) {
        this(0, image);
    }

    public Card(ArrayList<Rule> rules, ImageView image) {
        this(rules, 0, image);
    }

    public Card(int constant, ImageView image) {
        this(new ArrayList<Rule>(), constant, image);
    }

    public Card(int[] range, ImageView image) {
        this(new ArrayList<Rule>(), range, image);
    }

    public Card(ArrayList<Rule> rules, int constant, ImageView image) {
        super(rules, constant);
        this.image = image;
    }

    public Card(ArrayList<Rule> rules, int[] range, ImageView image) {
        super(rules, range);
        this.image = image;
    }

    public ImageView getImage() {
        return this.image;
    }
}