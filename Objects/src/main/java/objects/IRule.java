package src.main.java.objects;

public interface IRule {
	public RNG get_rng();
    public RNG update_rng(RNG new_rng);
    public int get_rule_score();
    public int replace_rule_score(int new_score);
    public void update_rule_type(String new_type);
}