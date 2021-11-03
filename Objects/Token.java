import java.util.ArrayList;

public class Token {
	
	public Token(int ID, int numPlayers) {
		this.ID = ID;
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
