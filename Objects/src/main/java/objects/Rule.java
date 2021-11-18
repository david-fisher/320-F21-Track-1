package objects;

public class Rule extends Saveable implements IRule {
    private String rule; private int score; private String type; private RNG rng;

    public Rule(){
        super();
        this.score = 0;
        this.type = "";
        this.rule = "";
        this.rng = new RNG();
    }

    public Rule(String rule){
    	super();
        this.score = 0;
        this.type = "";
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(String rule, int score){
    	super();
        this.score = score;
        this.type = "";
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(String rule, int score, String type){
    	super();
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(String rule, int score, String type, RNG rng){
    	super();
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = rng;
    }

    public Rule(String id, String rule, int score, String type, RNG rng){
    	super(id);
        this.score = score;
        this.type = type;
        this.rule = rule;
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

    public void update_rule(String new_rule, String new_type) {
        this.rule = new_rule;
        this.type = new_type;
    }

    public String to_string() {
        // to-do
        String str = "This rule is about " + this.rule;
        return str;
    }
}