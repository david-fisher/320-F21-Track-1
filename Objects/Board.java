
import java.util.ArrayList;

public class Board implements IBoard{
	public Board(int ID,int height,int width) {
		this.ID = ID;
	//	this.height = height;
	//	this.width = width;
		
	}
	private final int ID;	
	//private final int height;     -- Do we need this?
	//private final int width;      -- And this?
	// Probably want to have a map from tile to next state
	private ArrayList<Tile> tiles;
	
	
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public void addTile(Tile tile) {
		this.tiles.add(tile);
		// Called from Tile object
	}
	public void removeTile(String ID) {
		tiles.remove(findByID(ID));
	}
	public int getID() {
		return ID;
	}
	/*
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	public Tile getNext(Tile tile) {
		//
	}
	
	
	*/
	
	private void to_json(Gameboard current) {
		//persistence method
	}
	
	public Tile findByID(String ID) {
	    return this.tiles.stream().filter(tile -> tile.ID == ID).findFirst().orElse(null);
	}
	
	
	
}
