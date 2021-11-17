package objects;

import java.util.*;

public class PTile extends Savable {
	private final int x,y; private Hashtable<String,String> attributes; private ArrayList<Rule> rules;

    public PTile(ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super();
        this.x = -1;
        this.y = -1;
        this.attributes = attributes;
        this.rules = rules;
    }

    public PTile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super();
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.rules = rules;
    }

    public PTile(String id, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super(id);
        this.x = -1;
        this.y = -1;
        this.attributes = attributes;
        this.rules = rules;
    }

    public PTile(String id, int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super(id);
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.rules = rules;
    }

    public int get_x(){return this.x;}

    public int get_y(){return this.y;}

    public ArrayList<Integer> get_coordinate(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(this.x);
        result.add(this.y);
        return result;
    }

    public ArrayList<Rule> get_rules(){
        return this.rules;
    }

    public Hashtable<String,String> get_attributes(){
        return this.attributes;
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
        return this.rules.remove(findByID(target_rule.get_id()));
    }

    public Rule findByID(String ID) {
	    return this.rules.stream().filter(rule -> rule.get_id() == ID).findFirst().orElse(null);
	}
}