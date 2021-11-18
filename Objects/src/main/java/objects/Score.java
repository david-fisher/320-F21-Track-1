package objects;

public class Score extends Saveable {
    int cur_score;
    
    public Score(){
        super();
        this.cur_score = 0;
    }
    
    public Score(int score){
    	super();
        this.cur_score = score;
    }
    
    public Score(String id, int score){
    	super(id);
        this.cur_score = score;
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