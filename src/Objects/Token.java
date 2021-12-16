<<<<<<< HEAD:src/Objects/Token.java
package src.main.java.objects;
=======
package Objects;
>>>>>>> main:Objects/Token.java

import java.util.*;

public class Token extends Saveable implements IToken {
<<<<<<< HEAD:src/Objects/Token.java
	
	private Board board;
	private ArrayList<Player> players;
	
=======

	private Board board;
	private ArrayList<Player> players;

>>>>>>> main:Objects/Token.java
	public Token() {
		super();
		this.board = new Board();
		this.players = new ArrayList<Player>();
	}
<<<<<<< HEAD:src/Objects/Token.java
	
=======

>>>>>>> main:Objects/Token.java
	public Token(String ID) {
		super(ID);
		this.board = new Board();
		this.players = new ArrayList<Player>();
	}
<<<<<<< HEAD:src/Objects/Token.java
	
=======

>>>>>>> main:Objects/Token.java
	public Token(String ID, ArrayList<Player> players) {
		super(ID);
		this.board = new Board();
		this.players = players;
	}
<<<<<<< HEAD:src/Objects/Token.java
	
=======

>>>>>>> main:Objects/Token.java
	public Token(String ID, ArrayList<Player> players, Board board) {
		super(ID);
		this.board = board;
		this.players = players;
	}
<<<<<<< HEAD:src/Objects/Token.java
	

	@Override
	public ArrayList<Player> get_players() {		
		return players;
	}
	
=======

	@Override
	public ArrayList<Player> get_players() {
		return players;
	}

>>>>>>> main:Objects/Token.java
	@Override
	// new player ID must match ID of player to be replaced
	public ArrayList<Player> update_player(Player new_player) {
		try {
			int index = players.indexOf(player_findByID(new_player.get_id()));
			players.set(index, new_player);
		} catch (Exception e) {
			players.add(new_player);
		}
<<<<<<< HEAD:src/Objects/Token.java
		
		return players;
	}
	
	@Override
	public Player player_findByID(String ID) {
		 return this.players.stream().filter(player -> player.get_id() == ID).findFirst().orElse(null);
	}
	
=======

		return players;
	}

	@Override
	public Player player_findByID(String ID) {
		return this.players.stream().filter(player -> player.get_id() == ID).findFirst().orElse(null);
	}

>>>>>>> main:Objects/Token.java
	@Override
	public Board get_gameboard() {
		return board;
	}
<<<<<<< HEAD:src/Objects/Token.java
	
=======

>>>>>>> main:Objects/Token.java
	@Override
	public Board update_gameboard(Board new_board) {
		this.board = new_board;
		return board;
	}
}