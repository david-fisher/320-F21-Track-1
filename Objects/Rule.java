import java.util.*;

public class Rule {
    final String ID;
    final String rule; int score; String type; RNG rng;

    public Rule(){
        this.ID = UUID.randomUUID().toString();
        this.score = 0;
        this.type = "";
        this.rule = "";
        this.rng = new RNG();
    }

    public Rule(rule){
        this.ID = UUID.randomUUID().toString();
        this.score = 0;
        this.type = "";
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(rule, score){
        this.ID = UUID.randomUUID().toString();
        this.score = score;
        this.type = "";
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(rule, score, type){
        this.ID = UUID.randomUUID().toString();
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = new RNG();
    }

    public Rule(rule, score, type, rng){
        this.ID = UUID.randomUUID().toString();
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = rng;
    }

    public Rule(id, rule, score, type, rng){
        this.ID = id;
        this.score = score;
        this.type = type;
        this.rule = rule;
        this.rng = rng;
    }

    public String get_id() { return this.id; }

    public RNG get_rng() { return this.rng; }

}
