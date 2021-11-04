import java.util.ArrayList;
import java.util.UUID;

public class Token implements IToken{
	
	private String ID;
	private Board board;
	private ArrayList<Player> players;
	
	public Token() {
		this.ID = UUID.randomUUID().toString();
		this.players = new ArrayList<Player>();
		this.board = new Board();

	}
	public Token(String ID, ArrayList<Player> players) {
		this.ID = ID;
		this.players = players;
		this.board = new Board();
	}
	public Token(String ID, ArrayList<Player> players, Board board) {
		this.ID = ID;
		this.players = players;
		this.board = board;
	}
	public Token(String ID, Board board) {
		this.ID = ID;
		this.players = new ArrayList<Player>();
		this.board = board;
	}
	public String get_ID() {
		return this.ID;
	}
	

	@Override
	public ArrayList<Player> get_players() {		
		return players;
	}
	@Override
	// new player ID must match ID of player to be replaced
	public ArrayList<Player> update_player(Player new_player) {
		try {
			int index = players.indexOf(player_findByID(new_player.ID));
			players.set(index, new_player);
		} catch (Exception e) {
			players.add(new_player);
		}
		
		return players;
	}
	@Override
	public Player player_findByID(String ID) {
		 return this.players.stream().filter(player -> player.ID == ID).findFirst().orElse(null);
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
