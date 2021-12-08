package src.main.java.objects;

import java.util.*;

public interface IToken {
    public ArrayList<Player> get_players(); 
    public Player player_findByID(String ID);
    public Board get_gameboard();
	public ArrayList<Player> update_player(Player new_player);
	public Board update_gameboard(Board new_board);
}