package src.main.java.objects;

import java.util.*;

// general class for a game object such as a game piece or a tile
public class GameObject extends Saveable {
    /*
    parem: x - the x coordinate of the object
           y - the y coordinate of the object
           attributes - a hashtable of attributes, e.g. {"color", "red"}
           rules - rules that the object holds 
    */
	private final int x,y;
    private Hashtable<String,String> attributes;
    private ArrayList<Rule> rules;

    // This attribute probably shouldn't be a part of this class
    private RNG score;

    public GameObject(ArrayList<Rule> rules) {
        super();
        this.rules = rules;
    }

    public GameObject(ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super();
        this.x = -1;
        this.y = -1;
        this.attributes = attributes;
        this.rules = rules;
    }

    public GameObject(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super();
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.rules = rules;
    }

    public GameObject(String id, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super(id);
        this.x = -1;
        this.y = -1;
        this.attributes = attributes;
        this.rules = rules;
    }

    public GameObject(String id, int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super(id);
        this.x = x;
        this.y = y;
        this.attributes = attributes;
        this.rules = rules;
    }

    public int get_x(){return this.x;}

    public int get_y(){return this.y;}

    public ArrayList<Integer> getCoordinate(){
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(this.x);
        result.add(this.y);
        return result;
    }

    public ArrayList<Rule> getRules(){
        return this.rules;
    }

    public Hashtable<String,String> getAttributes(){
        return this.attributes;
    }

    public Hashtable<String,String> updateAttribute(String new_key, String new_attribute){
        this.attributes.put(new_key, new_attribute);
        return this.attributes;
    }

    public Hashtable<String,String> removeAttribute(String key){
    	try {
    		this.attributes.remove(key);
        } catch (Exception e) {
        	System.out.println(String.format("Error: Unable to remove target attribute with key: %12s", key));
            System.out.println(e);
        }
        return this.attributes;
    }

    public Hashtable<String,String> updateAttributes(Hashtable<String,String> new_attributes){
        this.attributes.putAll(new_attributes);
        return this.attributes;
    }

    public Hashtable<String,String> removeAttributes(){
        this.attributes.clear();
        return this.attributes;
    }

    public ArrayList<Rule> addRule(Rule new_rule){
        this.rules.add(new_rule);
        return this.rules;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public ArrayList<Rule> removeRule(Rule target_rule){
    	try {
    		this.rules.remove(findByID(target_rule.get_id()));
        } catch (Exception e) {
        	System.out.println(String.format("Error: Unable to remove target rule: %s", target_rule.get_id()));
            System.out.println(e);
        }
        return this.rules;
    }

    public void executeRules(GameState state) {
        state.enqueueRules(rules);
        state.getCurPlayer().deltaScore(score.pop());
    }

    public Rule findByID(String ID) {
	    return this.rules.stream().filter(rule -> rule.get_id() == ID).findFirst().orElse(null);
	}
}