package objects;

import java.util.*;

public class Player extends Saveable {
    Score score; Tile current_tile;

    public Player(){
        this.ID = UUID.randomUUID().toString();
        this.score = new Score();
        this.current_tile = null;
    } 

    public Player(Score score){
        this.ID = UUID.randomUUID().toString();
        this.score = score;
        this.current_tile = null;
    } 

    public Player(Tile current_tile){
        this.ID = UUID.randomUUID().toString();
        this.score = new Score();
        this.current_tile = current_tile;
    } 

    public Player(String id, Score score, Tile current_tile){
        this.ID = id;
        this.score = score;
        this.current_tile = current_tile;
    }

    public int get_score(){
        return this.score.get_score();
    }

    public int update_score(int change){
        this.score.update(change);
        return get_score();
    }

    public Tile get_Tile(){
        return this.current_tile;
    }

    public void update_tile(Tile new_tile){
        this.current_tile = new_tile;
    }
    
    @Override
    public Hashtable<String, String> to_json(){
        Hashtable<String, String> result = new Hashtable<String, String>();
        result.put("id", this.ID);
        result.put("score", this.score.get_id());
        result.put("current tile", this.current_tile.get_id());
        return result;
    }
}