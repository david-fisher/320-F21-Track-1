package src.main.java.objects;

import java.util.*;

public class Tile extends GameObject {

    private ArrayList<Tile> neighbors; private Deck deck;

    public Tile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super(x, y, rules, attributes);
        this.neighbors = new ArrayList<Tile>();
        this.deck = new Deck();
    }

    public Tile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, ArrayList<Tile> neighbors){
        super(x, y, rules, attributes);
        this.neighbors = neighbors;
        this.deck = new Deck();
    }

    public Tile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, ArrayList<Tile> neighbors, Deck deck){
        super(x, y, rules, attributes);
        this.neighbors = neighbors;
        this.deck = deck;
    }

    public Tile(String id, int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, ArrayList<Tile> neighbors, Deck deck){
        super(id, x, y, rules, attributes);
        this.neighbors = neighbors;
        this.deck = deck;
    }
    
    public Deck get_deck() {
    	return this.deck;
    }

    public ArrayList<Tile> get_neighbors(){
        return this.neighbors;
    }

    public ArrayList<Tile> update_neighbor(Tile new_neighbor){
        try {
            int index = this.neighbors.indexOf(tile_findByID(new_neighbor.get_id()));
		    this.neighbors.set(index, new_neighbor);
        } catch (Exception e) {
            this.neighbors.add(new_neighbor);
        }
        return this.neighbors;
    }

    public ArrayList<Tile> remove_neighbor(Tile input){
        try {
		    this.neighbors.remove(this.neighbors.indexOf(tile_findByID(input.get_id())));
        } catch (Exception e) {
        	System.out.println(String.format("Error: Unable to remove target neighbor tile: %s", input.get_id()));
            System.out.println(e);
        }	
        return this.neighbors;
    }

    public ArrayList<Tile> update_neighbors(ArrayList<Tile> new_neighbors){
        for (Tile new_neighbor: new_neighbors){
            update_neighbor(new_neighbor);
        }
        return this.neighbors;
    }

    public ArrayList<Tile> remove_neighbors(){
        this.neighbors.clear();
        return this.neighbors;
    }

    public Tile tile_findByID(String ID) {
	    return this.neighbors.stream().filter(tile -> tile.get_id().equals(ID)).findFirst().orElse(null);
	}

}