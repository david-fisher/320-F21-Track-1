import java.util.*;

public interface IBoard{
    public ArrayList<Tile> get_tiles ();
    public ArrayList<Tile> update_tile (Tile new_tile);
    public ArrayList<Tile> remove_tile(Tile tile);
    public ArrayList<Tile> update_tiles(ArrayList<Tile> tiles);
    public ArrayList<Tile> remove_tiles();
    public ArrayList<Rule> get_rules();
    public ArrayList<Rule> add_rule(Rule new_rule);
    public Rule remove_rule(Rule target_rule);
    public Rule rule_findByID(String ID);
    public Tile tile_findByID(String ID);
}