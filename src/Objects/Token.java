package Objects;

import java.util.*;

public class Token extends Savable implements IToken {

	private Board board;
	private ArrayList<Player> players;

	public Token() {
		super();
		this.board = new Board();
		this.players = new ArrayList<Player>();
	}
	public Token(String ID) {
		super(ID);
		this.board = new Board();
		this.players = new ArrayList<Player>();
	}
	public Token(String ID, ArrayList<Player> players) {
		super(ID);
		this.board = new Board();
		this.players = players;
	}
	public Token(String ID, ArrayList<Player> players, Board board) {
		super(ID);
		this.board = board;
		this.players = players;
	}

	@Override
	public ArrayList<Player> get_players() {
		return players;
	}

	@Override
	// new player ID must match ID of player to be replaced
	public ArrayList<Player> update_player(Player new_player) {
		try {
			int index = players.indexOf(player_findByID(new_player.get_id()));
			players.set(index, new_player);
		} catch (Exception e) {
			players.add(new_player);
		}

		return players;
	}

	@Override
	public Player player_findByID(String ID) {
		return this.players.stream().filter(player -> player.get_id() == ID).findFirst().orElse(null);
	}

	@Override
	public Board get_gameboard() {
		return board;
	}
	@Override
	public Board update_gameboard(Board new_board) {
		this.board = new_board;
		return board;
	}
}