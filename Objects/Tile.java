
import java.util.*;

public class Tile {

    final String ID;final int x,y; String color; ArrayList<Rule> rules; ArrayList<Tile> next_tiles;

    public Tile(int x, int y, String color, ArrayList<Rule> rules){
        this.ID = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.color = color;
        this.rules = rules;
        this.next_tiles = this.next_state();
    }

    public Tile(String id, int x, int y, String color, ArrayList<Rule> rules){
        this.ID = id;
        this.x = x;
        this.y = y;
        this.color = color;
        this.rules = rules;
        this.next_tiles = this.next_state();
    }
    public Tile(String id, int x, int y, String color, ArrayList<Rule> rules, ArrayList<Tile> next_tiles){
        this.ID = id;
        this.x = x;
        this.y = y;
        this.color = color;
        this.rules = rules;
        this.next_tiles = next_tiles;
    }

    public String get_id(){return this.ID;}

    public ArrayList<Tile> next_state(){
        ArrayList<Tile> next_possible_tiles = new ArrayList<>();        
        for (int i = 0; i < this.rules.size(); i++){
            next_possible_tiles.addAll(this.rules.get(i).update(this));
        }
        this.next_tiles = next_possible_tiles;
        return this.next_tiles;
    }

    public void change_color(String new_color) {
        this.color = new_color;
    }

    public void add_rule(Rule new_rule){
        this.rules.add(new_rule);
    }

    public boolean remove_rule(Rule target_rule){
        return this.rules.remove(findByID(this.ID));
    }

    public Rule findByID(String ID) {
	    return this.rules.stream().filter(rule -> rule.ID == ID).findFirst().orElse(null);
	}

    public Hashtable<String, String> to_json(){
        Hashtable<String, String> result = new Hashtable<String, String>();
        result.put("id", this.ID);
        result.put("x", new String(Integer.toString(this.x)));
        result.put("y", new String(Integer.toString(this.y)));
        String rule_ids = "";
        for(int i = 0; i < this.rules.size(); i++){
            rule_ids += " " + this.rules.get(i).ID;
        }
        result.put("rules id", rule_ids);
        String next_tiles_ids = "";
        for(int j = 0; j < this.next_tiles.size(); j++){
            next_tiles_ids += " " + this.next_tiles.get(j).ID;
        }
        result.put("next tile ids", next_tiles_ids);
        return result;
    } 


}
