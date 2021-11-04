import java.util.ArrayList;

public class Token {
	final public int id;
	private Gameboard board; private ArrayList<Player> players;
	
	public Token(int id, int numPlayers) {
		this.id = id;
		this.numPlayers = numPlayers;
	}
	private final int ID;
	private Gameboard gameboard;
	private int numPlayers;
	//private ArrayList<Gamerule> rules;
	//private ArrayList<Player> players;
	//private ArrayList<RNG> rng;
	
	public void setRules(Rule rule) {
		//ToDo 
	}
	public void setGameboard(Gameboard gameboard) {
		this.gameboard = gameboard;
	}
	public void addPlayers(Player player) {
		//this.players.add(player)
	}
	public void addRNG(RNG rng) {
		//this.rng.add(rng);
	} 
	private void to_json(Token token) {
		//persistence 
	}

}
