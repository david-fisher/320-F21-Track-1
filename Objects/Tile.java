package Objects;
import Objects.*;
import java.util.*;

public class Tile {

    final int ID,x,y; ArrayList<Rule> rules; ArrayList<Tile> next_tiles;

    public Tile(int id, int x, int y, ArrayList<Rule> rules){
        this.ID = id;
        this.x = x;
        this.y = y;
        this.rules = rules;
        this.next_tiles = this.next_state();
    }
    public Tile(int id, int x, int y, ArrayList<Rule> rules, ArrayList<Tile> next_tiles){
        this.ID = id;
        this.x = x;
        this.y = y;
        this.rules = rules;
        this.next_tiles = next_tiles;
    }

    public int get_id(){return this.ID;}

    public ArrayList<Tile> next_state(){
        ArrayList<Tile> next_possible_tiles = new ArrayList<>();        
        for (int i = 0; i < this.rules.size(); i++){
            next_possible_tiles.addAll(this.rules.get(i).update(this));
        }
        this.next_tiles = next_possible_tiles;
        return this.next_tiles;
    }

    public void add_rule(Rule new_rule){
        this.rules.add(new_rule);
    }

    public boolean remove_rule(Rule target_rule){
        return this.rules.remove(findByID(ID));
    }

    public Rule findByID(int ID) {
	    return this.rules.stream().filter(rule -> rule.ID == ID).findFirst().orElse(null);
	}

    public Hashtable<String, int[]> to_json(){
        Hashtable<String, int[]> result = new Hashtable<String, int[]>();
        result.put("id", new int[]{this.ID});
        result.put("x", new int[]{this.x});
        result.put("y", new int[]{this.y});
        int[] rule_ids = new int[this.rules.size()];
        for(int i = 0; i < this.rules.size(); i++){
            rule_ids[i] = this.rules.get(i).ID;
        }
        result.put("rules id", rule_ids);
        int[] next_tiles_ids = new int[this.next_tiles.size()];
        for(int j = 0; j < this.next_tiles.size(); j++){
            next_tiles_ids[j] = this.next_tiles.get(j).ID;
        }
        result.put("next tile ids", next_tiles_ids);
        return result;
    } 


}
