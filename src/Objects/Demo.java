package Objects;

import java.util.*;
import java.io.*;

public class Demo {
    public static void print(String str){System.out.println(str);}
    public static void print(int integer){System.out.println(integer);}

    public static ArrayList<Tile> tiles(){
        /* tiles:
            | a | b |
            | c | d |
        */

        Piece pa = new Piece(0, 0, new ArrayList<Rule>(), new Hashtable<String, String>());

        Tile a = new Tile(0, 0, new ArrayList<Rule>(), new Hashtable<String,String>(){{put("color", "blue");}});
        Tile b = new Tile(0, 1, new ArrayList<Rule>(), new Hashtable<String,String>());
        Tile c = new Tile(1, 0, new ArrayList<Rule>(), new Hashtable<String,String>());
        Tile d = new Tile(1, 1, new ArrayList<Rule>(), new Hashtable<String,String>());
        print(String.format("Current tile: id: %30s; x: %2d; y: %2d;", a.get_id(), a.get_x(), a.get_y()));

        a.get_deck().update_piece(pa);
        ArrayList<Piece> AP = a.get_deck().get_pieces();
        for (int m=0; m < AP.size(); m++){
            print(AP.get(m).get_id());
        }

        a.update_attribute("brightness", "3");
        Hashtable<String,String> attributes = a.get_attributes();
        Set<String> setofAttributes = attributes.keySet();
        print("\tattributes after adding brightness:");
        for(String key: setofAttributes){
            String temp = attributes.get(key);
            print(String.format("\t\tattribute: %12s; value: %6s;", key, temp));
        }

        a.update_attributes(new Hashtable<String,String>(){{put("color", "red"); put("trap", "true");}});
        attributes = a.get_attributes();
        setofAttributes = attributes.keySet();
        print("\tattributes after updating color and adding trap:");
        for(String key: setofAttributes){
            String temp = attributes.get(key);
            print(String.format("\t\tattribute: %12s; value: %6s;", key, temp));
        }

        a.remove_attribute("trap");
        attributes = a.get_attributes();
        setofAttributes = attributes.keySet();
        print("\tattributes after deleting trap:");
        for(String key: setofAttributes){
            String temp = attributes.get(key);
            print(String.format("\t\tattribute: %12s; value: %6s;", key, temp));
        }

        //a.remove_attributes();
        attributes = a.get_attributes();
        setofAttributes = attributes.keySet();
        print("\tattributes after erase entire attributes:");
        for(String key: setofAttributes){
            String temp = attributes.get(key);
            print(String.format("\t\tattribute: %12s; value: %6s;", key, temp));
        }


        a.update_neighbor(b);
        //b.update_neighbor(a);
        a.update_neighbor(c);
        //c.update_neighbor(a);
        ArrayList<Tile> neighbors = a.get_neighbors();
        print("\tneighbors:");
        for(Tile neighbor: neighbors){
            print(String.format("\t\tx: %2d; y: %2d", neighbor.get_x(), neighbor.get_y()));
        }

        return new ArrayList<Tile>(){{add(a);add(b);add(c);add(d);}};
    }

    public static Player Player(){
        /* tiles:
            | a (Player) | b |
            | c          | d |
        */
        ArrayList<Tile> tile = tiles();
        Tile a = tile.get(0);
        Player one = new Player(a);
        print(String.format("Player: %s, Score: %d", one.get_id(), one.get_score()));
        one.update_score(3);
        print(String.format("Player: %s, Score: %d", one.get_id(), one.get_score()));

        //update current tile in player one
        a = one.get_Tile();
        a.update_attribute("color", "green");
        one.update_tile(a);

        // move to the tile on the right
        /* tiles:
            | a | b (player) |
            | c | d          |
        */
        print(String.format("Current location: (%d,%d)", one.get_Tile().get_x(), one.get_Tile().get_y()));
        ArrayList<Tile> neighbors = one.get_Tile().get_neighbors();
        one.update_tile(neighbors.get(0));
        print(String.format("Current location: (%d,%d)", one.get_Tile().get_x(), one.get_Tile().get_y()));
        return one;
    }

    public static Board Board(){
        Board board = new Board();

        ArrayList<Tile> tiles= tiles();
        board.update_tiles(tiles);
        Rule rule = new Rule("rng greater than 50% fall into a trap", -1, "Trap", new RNG(new double[]{0,1}, 1));
        tiles.get(3).add_rule(rule); // update a new rule to tile d

        // board.update_tiles(tiles);
        Tile d = board.get_tiles().get(3); // get tile d
        print("");
        print(d.get_rules().get(0).to_string()); // print the rule content
        
        // removing tile d from board
        print(String.format("\nCurrent number of Tiles: %2d", board.get_tiles().size()));
        //board.remove_tile(d);
        print(String.format("Current number of Tiles: %2d", board.get_tiles().size()));

        return board;
    }

    public static Token Token(){
        Token token = new Token();
        token.update_gameboard(Board());
        token.update_player(Player());
        token.get_players().get(0).update_tile(token.get_gameboard().get_tiles().get(2));
        print(String.format("Successfully update gameboard: '%s' and player object: '%s' into token",
                             token.get_gameboard().get_id(), token.get_players().get(0).get_id()));

        return token;
    }
    
    public static Token JSON() throws IOException{
    	Token token = Token();
		JSONConverter json = new JSONConverter(token, "Token.json");
		json.To_JSON();
		print(String.format("Successfully convert the token object with ID: %s into JSON file",
                token.get_id()));
		return json.From_JSON();
	}

    public static int[] RNG_10_INT(){
        int num = 100;
        RNG new_RNG = new RNG(new double[]{1,10},num);
        int[] result = new int[num];
        result = new_RNG.ran_int();
        for(int i = 0; i < num; ++i){
            System.out.println(result[i]);
            // if (result[i] > 10 || result[i] < 1) return false;
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        // Board();
        //Token();
    	JSON();
        //JSONConverter jsonConverter = new JSONConverter(JSON(), "After.json");
        //jsonConverter.To_JSON();
        // RNG_10_INT();
        // tiles();
        // Player();
    }
}