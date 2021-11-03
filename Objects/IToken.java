import java.util.*;

public interface IToken {
    public ArrayList<Player> get_players(); 
    public Player update_player();
    public Player player_findByID(String ID);
    public Board get_gameboard();
    public Board update_gameboard();
}
