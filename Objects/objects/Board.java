package objects;

import java.util.*;

public class Board implements IBoard {
	
	private String ID;	
	private ArrayList<Tile> tiles;
	private ArrayList<Rule> rules;
	
	public Board(){
		this(UUID.randomUUID().toString());
	}
	
	public Board(String ID) {
		this(ID, new ArrayList<Tile>());
	}
	
	public Board(String ID, ArrayList<Tile> tiles) {
		this(ID, tiles, new ArrayList<Rule>());
	}
	
	public Board(String ID, ArrayList<Tile> tiles, ArrayList<Rule> rules) {
		this.ID = ID;
		this.tiles = tiles;
		this.rules = rules;
	}

	public String getID() {
		return ID;
	}
	
	@Override
	public ArrayList<Tile> get_tiles() {
		return tiles;
	}

	@Override
	// new tile ID must match ID of tile to be replaced
	public ArrayList<Tile> update_tile(Tile new_tile) {
		int index = tiles.indexOf(tile_findByID(new_tile.ID));
		tiles.set(index, new_tile);
		return tiles;	
	}

	@Override
	public ArrayList<Tile> remove_tile(Tile tile) {
		int index = tiles.indexOf(tile_findByID(tile.ID));
		tiles.remove(index);
		return tiles;
	}

	@Override
	public ArrayList<Tile> update_tiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
		return tiles;
	}

	@Override
	public ArrayList<Tile> remove_tiles() {
		this.tiles.clear();
		return tiles;
	}

	@Override
	public ArrayList<Rule> get_rules() {
		return rules;
	}

	@Override
	public ArrayList<Rule> add_rule(Rule new_rule) {
		rules.add(new_rule);
		return rules;
	}

	@Override
	public Rule remove_rule(Rule target_rule) {
		int index = rules.indexOf(rule_findByID(target_rule.ID));
		return rules.remove(index);
	}

	@Override
	public Rule rule_findByID(String ID) {
	    return this.rules.stream().filter(rule -> rule.ID.equals(ID)).findFirst().orElse(null);
	}

	@Override
	public Tile tile_findByID(String ID) {
	    return this.tiles.stream().filter(tile -> tile.ID.equals(ID)).findFirst().orElse(null);
	}
}