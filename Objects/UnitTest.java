import java.util.*;

public class UnitTest {
    public static void print(String str){System.out.println(str);}

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
        Tile a = new Tile(0, 0, new ArrayList<Rule>(), new Hashtable<String,String>());
        Tile b = new Tile(0, 1, new ArrayList<Rule>(), new Hashtable<String,String>());
        Tile c = new Tile(1, 0, new ArrayList<Rule>(), new Hashtable<String,String>());
        Tile d = new Tile(1, 1, new ArrayList<Rule>() , new Hashtable<String,String>());
        a.update_neighbor("right", b);
        b.update_neighbor("left", a);
        a.update_neighbor("down", c);
        

        print(a.get_id());
        
    }

}
