package src.main.java.objects;

public class Rule extends Saveable implements IRule {
	private String type; 
    private int score;     
    private RNG rng;
    private Card card;
    private Tile tile;

    public Rule(){
        super();
        this.score = 0;
        this.type = "";
        this.rng = new RNG();
    }

    public Rule(int score){
    	super();
        this.score = score;
        this.type = "";
        this.rng = new RNG();
    }

    public Rule(int score, String type){
    	super();
        this.score = score;
        this.type = type;
        this.rng = new RNG();
    }

    public Rule(int score, String type, RNG rng){
    	super();
        this.score = score;
        this.type = type;
        this.rng = rng;
    }

    public Rule(String id, int score, String type, RNG rng){
    	super(id);
        this.score = score;
        this.type = type;
        this.rng = rng;
    }

    public RNG get_rng() { return this.rng; }

    public int get_rule_score() { return this.score; }

    public String get_type() { return this.type; }

    public RNG update_rng(RNG new_rng) {
        this.rng = new_rng;
        return this.rng;
    }

    public int replace_rule_score(int new_score) {
        this.score = new_score;
        return this.score;
    }

    public void update_rule_type(String new_type) {
        this.type = new_type;
    }

}