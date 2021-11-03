import java.util.*;

public class Tile extends Saveable{

    final int x,y; Hashtable<String,String> attributes; ArrayList<Rule> rules; Hashtable<String,Tile> neighbors;

    public Tile(int x, int y, Hashtable<String,String> attributes, ArrayList<Rule> rules){
        this.ID = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.rules = rules;
        this.neighbors = new Hashtable<String,Tile>();
    }

    public Tile(int x, int y, Hashtable<String,String> attributes, ArrayList<Rule> rules, Hashtable<String,Tile> neighbors){
        this.ID = UUID.randomUUID().toString();
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.rules = rules;
        this.neighbors = neighbors;
    }
    public Tile(String id, int x, int y, Hashtable<String,String> attributes, ArrayList<Rule> rules, Hashtable<String,Tile> neighbors){
        this.ID = id;
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.rules = rules;
        this.neighbors = neighbors;
    }

    public Hashtable<String,Tile> update_neighbor(String direction, Tile new_neighbor){
        this.neighbors.put(direction, new_neighbor);
        return this.neighbors;
    }

    public Hashtable<String,Tile> remove_neighbor(String direction){
        this.neighbors.remove(direction);
        return this.neighbors;
    }

    public Hashtable<String,Tile> update_neighbors(Hashtable<String,Tile> new_neighbors){
        this.neighbors.putAll(new_neighbors);
        return this.neighbors;
    }

    public Hashtable<String,Tile> remove_neighbors(){
        this.neighbors.clear();
        return this.neighbors;
    }

    public Hashtable<String,String> update_attribute(String new_key, String new_attribute){
        this.attributes.put(new_key, new_attribute);
        return this.attributes;
    }

    public Hashtable<String,String> remove_attribute(String key){
        this.attributes.remove(key);
        return this.attributes;
    }

    public Hashtable<String,String> update_attributes(Hashtable<String,String> new_attributes){
        this.attributes.putAll(new_attributes);
        return this.attributes;
    }

    public Hashtable<String,String> remove_attributes(){
        this.attributes.clear();
        return this.attributes;
    }

    public ArrayList<Rule> add_rule(Rule new_rule){
        this.rules.add(new_rule);
        return this.rules;
    }

    public boolean remove_rule(Rule target_rule){
        return this.rules.remove(findByID(target_rule.ID));
    }

    public Rule findByID(String ID) {
	    return this.rules.stream().filter(rule -> rule.ID == ID).findFirst().orElse(null);
	}


    @Override
    public Hashtable<String, String> to_json(){
        Hashtable<String, String> result = new Hashtable<String, String>();
        // result.put("id", this.ID);
        // result.put("x", new String(Integer.toString(this.x)));
        // result.put("y", new String(Integer.toString(this.y)));
        // String rule_ids = "";
        // for(int i = 0; i < this.rules.size(); i++){
        //     rule_ids += " " + this.rules.get(i).ID;
        // }
        // result.put("rules id", rule_ids);
        // String next_tiles_ids = "";
        // for(int j = 0; j < this.next_tiles.size(); j++){
        //     next_tiles_ids += " " + this.next_tiles.get(j).ID;
        // }
        // result.put("next tile ids", next_tiles_ids);
        return result;
    } 


}
