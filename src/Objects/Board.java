package Objects;

import java.util.*;

public class Board extends Saveable implements IBoard {
	/* 
	parem: tiles - an array list of tiles on the board
           rules - an array list of rules on the board
		   deck  - a deck of game pieces (including cards) for the board
	*/
	private ArrayList<Tile> tiles;
	private ArrayList<Rule> rules;
	private Deck deck;
	private int dimensionX, dimensionY;
	
	public Board(){
		super();
		this.tiles = new ArrayList<Tile>();
		this.rules = new ArrayList<Rule>();
		this.deck = new Deck();
	}
	
	public Board(ArrayList<Tile> tiles) {
		super();
		this.tiles = tiles;
		this.rules = new ArrayList<Rule>();
		this.deck = new Deck();
	}
	
	public Board(ArrayList<Tile> tiles, ArrayList<Rule> rules) {
		super();
		this.tiles = tiles;
		this.rules = rules;
		this.deck = new Deck();
	}
	
	public Board(ArrayList<Tile> tiles, ArrayList<Rule> rules, Deck deck) {
		super();
		this.tiles = tiles;
		this.rules = rules;
		this.deck = deck;
	}

	public Board(String ID, ArrayList<Tile> tiles, ArrayList<Rule> rules, Deck deck) {
		super(ID);
		this.tiles = tiles;
		this.rules = rules;
		this.deck = deck;
	}

	public Deck get_deck() {
		return deck;
	}
	
	@Override
	public ArrayList<Tile> get_tiles() {
		return tiles;
	}

	@Override
	// new tile ID must match ID of tile to be replaced
	public ArrayList<Tile> update_tile(Tile new_tile) {
		int index = tiles.indexOf(tile_findByID(new_tile.get_id()));
		tiles.set(index, new_tile);
		return tiles;	
	}

	@Override
	public ArrayList<Tile> remove_tile(Tile tile) {
		int index = tiles.indexOf(tile_findByID(tile.get_id()));
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
		int index = rules.indexOf(rule_findByID(target_rule.get_id()));
		return rules.remove(index);
	}

	@Override
	public Rule rule_findByID(String ID) {
	    return this.rules.stream().filter(rule -> rule.get_id().equals(ID)).findFirst().orElse(null);
	}

	@Override
	public Tile tile_findByID(String ID) {
	    return this.tiles.stream().filter(tile -> tile.get_id().equals(ID)).findFirst().orElse(null);
	}
	
	public void updateDimensions(int x, int y) {
		this.dimensionX = x;
		this.dimensionY = y;
	}
	
	public int getDimensionX() {
		return this.dimensionX;
	}
	
	public int getDimensionY() {
		return this.dimensionY;
	}
}