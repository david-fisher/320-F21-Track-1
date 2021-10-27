import java.util.*;

public class Score {
    final int ID; int cur_score;
    
    public Score(int id){
        this.ID = id;
        this.cur_score = 0;
    }
    
    
    public int get_score(){
        return this.cur_score;
    }
    
    public int update(ArrayList<Rule> rules){
        //The score will be updated based on the rules that will be provided by Team 4
        int new_score = 1;//Calculate based on rules and update
        this.cur_score = new_score;
        return this.cur_score;
    }
}
