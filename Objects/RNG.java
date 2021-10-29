import java.util.*;

/* 
Class return an array of generated random elements
*/
public class RNG{
    String ID; int repeat, seed; double[] range; Random rand;
    /*
    Attributes:
        ID: every random generator has an unique ID
        Range: range of random numbers, [Lowbound, highbound] => [1,4]
        repeat: number of random numbers should return
        Seed: the seed that random generator use
    */

    public RNG(){
        this.ID = UUID.randomUUID().toString();
        this.range[0] = 0;
        this.range[1] = 1;
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(double[] range){
        this.ID = UUID.randomUUID().toString();
        this.range = range;
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(String id, double[] range){
        this.ID = id;
        this.range = range;
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }
    
    public RNG(String id, double[] range, int repeat){
        this.ID = id;
        this.range = range;
        this.seed = -1;
        this.repeat = repeat;
        this.rand = new Random();
    }
    
    public RNG(String id, double[] range, int seed, int repeat){
        this.ID = id;
        this.range = range;
        this.seed = seed;
        this.repeat = repeat;
        this.rand = new Random(this.seed);
    }

    public String get_id(){return this.ID;}

    public void replace_repeat(int new_repeat){this.repeat = new_repeat;}

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
        double[] result; result = new double[this.repeat];
        for(int i=0; i < this.repeat; i++){
            result[i] = rand.nextDouble()*(this.range[1]-this.range[0]) + this.range[0];
        }
        return result;
    }

    public float[] ran_float(){
        float[] result; result = new float[this.repeat];
        for(int i=0; i < this.repeat; i++){
            result[i] = rand.nextFloat()*(float)(this.range[1]-this.range[0]) + (float)this.range[0];
        }
        return result;
    }

    public int[] ran_int(){
        int[] result; result = new int[this.repeat];
        for(int i=0; i < this.repeat; i++){
            result[i] =  rand.nextInt((int)(this.range[1]-this.range[0])+1) + (int)this.range[0];
        }
        return result;
    }

    public Hashtable<String, String> to_json(){
        Hashtable<String, String> result = new Hashtable<String, String>();
        result.put("id", this.ID);
        String ranges = "";
        for(int i = 0; i < 2; i++){
            ranges += " " + this.range[i];
        }
        result.put("ranges", ranges);
        result.put("repeat", new String(Integer.toString(this.repeat)));
        result.put("seed", new String(Integer.toString(this.seed)));
        return result;
    }

}