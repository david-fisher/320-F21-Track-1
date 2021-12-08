package src.main.java.objects;

import java.util.*;

/* 
Class return an array of generated random elements
*/
public class RNG extends Saveable {
	private int repeat, seed; private double[] range;
    //private transient Random rand;
    private Random rand;
    /*
    Attributes:
        ID: every random generator has an unique ID
        Range: range of random numbers, [Lowbound, highbound] => [1,4]
        repeat: number of random numbers should return
        Seed: the seed that random generator use
    */

    public RNG(){
        super();
        this.range = new double[]{0,1};
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(double[] range){
    	super();
        this.range = range;
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(String id, double[] range){
    	super();
        this.range = range;
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(double[] range, int repeat){
    	super();
        this.range = range;
        this.seed = -1;
        this.repeat = repeat;
        this.rand = new Random();
    }
    
    public RNG(String id, double[] range, int repeat){
    	super(id);
        this.range = range;
        this.seed = -1;
        this.repeat = repeat;
        this.rand = new Random();
    }
    
    public RNG(String id, double[] range, int seed, int repeat){
    	super(id);
        this.range = range;
        this.seed = seed;
        this.repeat = repeat;
        this.rand = new Random(this.seed);
    }

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

    // generate an array of random doubles, the length of array would be the number of repeats specified
    public double[] ran_double(){
        double[] result; 
        result = new double[this.repeat];
        for(int i=0; i < this.repeat; i++){
            result[i] = rand.nextDouble()*(this.range[1]-this.range[0]) + this.range[0];
        }
        return result;
    }

    // generate an array of random floats, the length of array would be the number of repeats specified
    public float[] ran_float(){
        float[] result; result = new float[this.repeat];
        for(int i=0; i < this.repeat; i++){
            result[i] = rand.nextFloat()*(float)(this.range[1]-this.range[0]) + (float)this.range[0];
        }
        return result;
    }

    // generate an array of random integers, the length of array would be the number of repeats specified
    public int[] ran_int(){
        int[] result; result = new int[this.repeat];
        for(int i=0; i < this.repeat; i++){
            result[i] = rand.nextInt((int)(this.range[1]-this.range[0])+1) + (int)this.range[0];
        }
        return result;
    }

    // return a random arrayList of rules, length specified by the number of repeats
    //   parem: input - an arrayList of rules from which the output will randomly take from
    public ArrayList<Rule> ran_rule(ArrayList<Rule> input){
        ArrayList<Rule> result = new ArrayList<Rule>();
        for(int i=0; i < this.repeat; i++){
            int index = rand.nextInt((int)(this.range[1]-this.range[0])+1) + (int)this.range[0];
            result.add(input.get(index));
        }
        return result;
    }
 
    // return a random arrayList of pieces, length specified by the number of repeats
    //   parem: input - an arrayList of pieces from which the output will randomly take from
    public ArrayList<Piece> ran_piece(ArrayList<Piece> input){
        ArrayList<Piece> result = new ArrayList<Piece>();
        for(int i=0; i < this.repeat; i++){
            int index = rand.nextInt((int)(this.range[1]-this.range[0])+1) + (int)this.range[0];
            result.add(input.get(index));
        }
        return result;
    }
}