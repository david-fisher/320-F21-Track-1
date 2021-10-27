import java.util.*;


public class Player {
    final int ID; Score score; Tile current_tile;

    public Player(int id){
        this.ID = id;
        this.score = new Score();
        this.current_tile = null;
    } 

    public Player(int id, Score score){
        this.ID = id;
        this.score = score;
        this.current_tile = null;
    } 

    public Player(int id, Tile current_tile){
        this.ID = id;
        this.score = new Score();
        this.current_tile = current_tile;
    } 

    public Player(int id, Score score, Tile current_tile){
        this.ID = id;
        this.score = score;
        this.current_tile = current_tile;
    }

    public int get_id(){return this.ID;} 

    public int check_score(){
        return this.score.get_score();
    }

    public void update_score(ArrayList<Rule> rules){
        this.score.update(rules);
    }

    public void update_tile(Tile new_tile){
        this.current_tile = new_tile;
    }

    public Hashtable<String, Integer> to_json(){
        Hashtable<String, Integer> result = new Hashtable<String, Integer>();
        result.put("id", this.ID);
        result.put("score", this.score.get_id());
        result.put("current tile", this.current_tile.get_id());
        return result;
    } 

    
}
