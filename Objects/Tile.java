import java.util.*;

public class Tile extends PTile{

    Hashtable<String,Tile> neighbors; ArrayList<Piece> pieces;

    public Tile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes){
        super(x, y, rules, attributes);
        this.neighbors = new Hashtable<String,Tile>();
        this.pieces = new ArrayList<Piece>();
    }

    public Tile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, ArrayList<Piece> pieces){
        super(x, y, rules, attributes);
        this.ID = UUID.randomUUID().toString();
        this.neighbors = new Hashtable<String,Tile>();
        this.pieces = pieces;
    }

    public Tile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, Hashtable<String,Tile> neighbors){
        super(x, y, rules, attributes);
        this.ID = UUID.randomUUID().toString();
        this.neighbors = neighbors;
        this.pieces = new ArrayList<Piece>();
    }

    public Tile(int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, Hashtable<String,Tile> neighbors, ArrayList<Piece> pieces){
        super(x, y, rules, attributes);
        this.neighbors = neighbors;
        this.pieces = pieces;
    }

    public Tile(String id, int x, int y, ArrayList<Rule> rules, Hashtable<String,String> attributes, Hashtable<String,Tile> neighbors, ArrayList<Piece> pieces){
        super(id, x, y, rules, attributes);
        this.neighbors = neighbors;
        this.pieces = pieces;
    }

    public Hashtable<String,Tile> get_neighbors(){
        return this.neighbors;
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

    public ArrayList<Piece> get_peices(){
        return this.pieces;
    }
    
    public ArrayList<Piece> update_peice(Piece new_piece){
        try {
            Piece temp = findPieceByID(new_piece.get_id());
            int index = this.pieces.indexOf(temp);
            this.pieces.set(index, new_piece);
        } catch (Exception e) {
            this.pieces.add(new_piece);
        }
        return this.pieces;
    }
    public ArrayList<Piece> remove_piece(Piece piece){
        try {
            int index = this.pieces.indexOf(findPieceByID(piece.ID));
            this.pieces.remove(index);
        } catch (Exception e) {}
        return this.pieces;
    }

    public ArrayList<Piece> update_peices(ArrayList<Piece> new_pieces){
        this.pieces = new_pieces;
        return this.pieces;
    }
    public ArrayList<Piece> remove_peices(){
        this.pieces.clear();
        return this.pieces;
    }    
    
    public Piece findPieceByID(String ID){
        return this.pieces.stream().filter(tile -> tile.ID == ID).findFirst().orElse(null);
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
