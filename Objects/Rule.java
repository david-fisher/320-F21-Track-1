import java.util.*;

public class Rule implements IRule{
    final String ID;
    final String rule; int score; String type; RNG rng;

    public Rule(){
        this.ID = UUID.randomUUID().toString();
        this.score = 0;
        this.type = "";
        this.rule = "";
        this.rng = new RNG();
    }

    public Rule(String rule){
        this.ID = UUID.randomUUID().toString();
        this.score = 0;
        this.type = "";
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(String rule, int score){
        this.ID = UUID.randomUUID().toString();
        this.score = score;
        this.type = "";
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(String rule, int score, String type){
        this.ID = UUID.randomUUID().toString();
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(String rule, int score, String type, RNG rng){
        this.ID = UUID.randomUUID().toString();
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = rng;
    }

    public Rule(String id, String rule, int score, String type, RNG rng){
        this.ID = id;
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = rng;
    }

    public String get_id() { return this.id; }

    public RNG get_rng() { return this.rng; }

    public int get_rule_score() { return this.score; }

    public String get_type() { return this.type; }

    public RNG update_rng(RNG new_rng) {
        this.rng = new_rng;
    }

    public int replace_rule_score(int new_score) {
        this.score = new_score;
    }

    public update_rule(String new_rule, String new_type) {
        this.rule = new_rule;
        this.type = new_type;
    }

    public to_string() {
        // to-do
        String str = "This rule is about " + this.rule;
        return str;
    }
}
