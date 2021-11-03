import java.util.*;

public class Demos {
    public static void print(String str){System.out.println(str);}
    public static void print(int integer){System.out.println(integer);}

    public static Tile tiles(){
        /* tiles:
            | a | b |
            | c | d |
        */
        Tile a = new Tile(0, 0, new ArrayList<Rule>(), new Hashtable<String,String>(){{put("color", "blue");}});
        Tile b = new Tile(0, 1, new ArrayList<Rule>(), new Hashtable<String,String>());
        Tile c = new Tile(1, 0, new ArrayList<Rule>(), new Hashtable<String,String>());
        // Tile d = new Tile(1, 1, new ArrayList<Rule>(), new Hashtable<String,String>());
        print(String.format("Current tile: id: %30s; x: %2d; y: %2d;", a.get_id(), a.get_x(), a.get_y()));

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

        a.remove_attributes();
        attributes = a.get_attributes();
        setofAttributes = attributes.keySet();
        print("\tattributes after erase entire attributes:");
        for(String key: setofAttributes){
            String temp = attributes.get(key);
            print(String.format("\t\tattribute: %12s; value: %6s;", key, temp));
        }


        a.update_neighbor("right", b);
        b.update_neighbor("left", a);
        a.update_neighbor("down", c);
        c.update_neighbor("up", a);
        Hashtable<String,Tile> neighbors = a.get_neighbors();
        Set<String> setofDirection = neighbors.keySet();
        print("\tneighbors:");
        for(String key: setofDirection){
            Tile temp = neighbors.get(key);
            print(String.format("\t\tdirection: %6s; x: %2d; y: %2d", key, temp.get_x(), temp.get_y()));
        }

        return a;
    }

    public static Player player(){
        /* tiles:
            | a (Player) | b |
            | c          | d |
        */
        Tile a = tiles();
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
        Hashtable<String,Tile> neighbors = one.get_Tile().get_neighbors();
        one.update_tile(neighbors.get("right"));
        print(String.format("Current location: (%d,%d)", one.get_Tile().get_x(), one.get_Tile().get_y()));
        return one;
    }

    public static boolean RNG_10_INT(){
        int num = 100;
        RNG new_RNG = new RNG(new double[]{1,10},num);
        int[] result = new int[num];
        result = new_RNG.ran_int();
        for(int i = 0; i < num; ++i){
            System.out.println(result[i]);
            if (result[i] > 10 || result[i] < 1) return false;
        }
        return true;
    }
    public static void main(String[] args){
        RNG_10_INT();
        tiles();
        player();
    }

}
