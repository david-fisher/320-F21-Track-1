
import java.util.*;

public class Score {
    final String ID; int cur_score;
    
    public Score(){
        this.ID = UUID.randomUUID().toString();
        this.cur_score = 0;
    }
    
    public Score(int score){
        this.ID = UUID.randomUUID().toString();
        this.cur_score = score;
    }
    
    public Score(String id, int score){
        this.ID = id;
        this.cur_score = score;
    }
    
    public String get_id(){
        return this.ID;
    }
    
    public int get_score(){
        return this.cur_score;
    }
    
    public int update(int change){
        //The score will be updated based on the rules that will be provided by Team 4
        //Iterate through the rule list and change the score based on each rule
        // int new_score = 1;//Calculate based on rules and update
        this.cur_score = change;
        return this.cur_score;
    }
}
