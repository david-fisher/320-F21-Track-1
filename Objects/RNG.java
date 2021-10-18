package Objects;
import java.util.Random;
import java.util.*;

/* 
Class return an array of generated random elements
*/
public class RNG{
    int id, num, seed; int[] range; Random rand;
    /*
    Attributes:
        ID: every random generator has an unique ID
        Range: range of random numbers, [Lowbound, highbound] => [1,4]
        Num: number of random numbers should return
        Seed: the seed that random generator use
    */

    public RNG(int id){
        this.id = id;
        this.range[0] = 0;
        this.range[1] = 1;
        this.num = -1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int id, int[] range){
        this.id = id;
        this.range = range;
        this.num = -1;
        this.seed = -1;
        this.rand = new Random();
    }
    
    public RNG(int id, int[] range, int seed){
        this.id = id;
        this.range = range;
        this.seed = seed;
        this.num = -1;
        this.rand = new Random(this.seed);
    }
    
    public RNG(int id, int[] range, int seed, int num){
        this.id = id;
        this.range = range;
        this.seed = seed;
        this.num = num;
        this.rand = new Random(this.seed);
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
        result.put("id", this.id);
        result.put("min", this.range[0]);
        result.put("max", this.range[1]);
        result.put("num", this.num);
        result.put("seed", this.seed);
        return result;
    }

}