package Objects;
import java.util.*;
import Objects.*;

/* 
Class return an array of generated random elements
*/
public class RNG{
    int ID, num, seed; int[] range; Random rand;
    /*
    Attributes:
        ID: every random generator has an unique ID
        Range: range of random numbers, [Lowbound, highbound] => [1,4]
        Num: number of random numbers should return
        Seed: the seed that random generator use
    */

    public RNG(int id){
        this.ID = id;
        this.range[0] = 0;
        this.range[1] = 1;
        this.num = -1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int id, int[] range){
        this.ID = id;
        this.range = range;
        this.num = -1;
        this.seed = -1;
        this.rand = new Random();
    }
    
    public RNG(int id, int[] range, int seed){
        this.ID = id;
        this.range = range;
        this.seed = seed;
        this.num = -1;
        this.rand = new Random(this.seed);
    }
    
    public RNG(int id, int[] range, int seed, int num){
        this.ID = id;
        this.range = range;
        this.seed = seed;
        this.num = num;
        this.rand = new Random(this.seed);
    }

    public int get_id(){return this.ID;}

    public int get_seed(){return this.seed;}

    public int delete_seed(){
        int current_seed = this.seed;
        this.seed = -1;
        this.rand = new Random();
        return current_seed;
    }

    public int replace_seed(int new_seed){
        int current_seed = this.seed;
        this.seed = new_seed;
        this.rand = new Random(this.seed);
        return current_seed;
    }


    public double[] ran_double(){
        double[] result; result = new double[this.num];
        for(int i=0; i < this.num; i++){
            result[i] = rand.nextDouble()*(this.range[1]-this.range[0]+1) + this.range[0];
        }
        return result;
    }

    public float[] ran_float(){
        float[] result; result = new float[this.num];
        for(int i=0; i < this.num; i++){
            result[i] = rand.nextFloat()*(this.range[1]-this.range[0]+1) + this.range[0];
        }
        return result;
    }

    public int[] ran_int(){
        int[] result; result = new int[this.num];
        for(int i=0; i < this.num; i++){
            result[i] = rand.nextInt()*(this.range[1]-this.range[0]+1) + this.range[0];
        }
        return result;
    }

    public Hashtable<String, Integer> to_json(){
        Hashtable<String, Integer> result = new Hashtable<String, Integer>();
        result.put("id", this.ID);
        result.put("min", this.range[0]);
        result.put("max", this.range[1]);
        result.put("num", this.num);
        result.put("seed", this.seed);
        return result;
    }

}