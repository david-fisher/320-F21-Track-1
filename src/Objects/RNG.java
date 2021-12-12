package src.main.java.objects;

import java.util.*;

/* 
Class return an array of generated random elements
*/
public class RNG extends Saveable {
    private int repeat, seed;
    private int[] range;
    // private transient Random rand;
    private Random rand;
    /*
     * Attributes:
     * ID: every random generator has an unique ID
     * Range: range of random numbers, [Lowbound, highbound] => [1,4]
     * repeat: number of random numbers should return
     * Seed: the seed that random generator use
     */

    public RNG() {
        super();
        this.range = new int[] { 0, 1 };
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int constant) {
        super();
        this.range = new int[] { constant, constant };
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int[] range) {
        super();
        this.range = range;
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(String id, int[] range) {
        super();
        this.range = range;
        this.repeat = 1;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int[] range, int repeat) {
        super();
        this.range = range;
        this.seed = -1;
        this.repeat = repeat;
        this.rand = new Random();
    }

    public RNG(String id, int[] range, int repeat) {
        super(id);
        this.range = range;
        this.seed = -1;
        this.repeat = repeat;
        this.rand = new Random();
    }

    public RNG(String id, int[] range, int seed, int repeat) {
        super(id);
        this.range = range;
        this.seed = seed;
        this.repeat = repeat;
        this.rand = new Random(this.seed);
    }

    public void replaceRepeat(int new_repeat) {
        this.repeat = new_repeat;
    }

    public int getSeed() {
        return this.seed;
    }

    public int deleteSeed() {
        int current_seed = this.seed;
        this.seed = -1;
        this.rand = new Random();
        return current_seed;
    }

    public int replaceSeed(int new_seed) {
        int current_seed = this.seed;
        this.seed = new_seed;
        this.rand = new Random(this.seed);
        return current_seed;
    }

    public double[] randDouble() {
        double[] result;
        result = new int[this.repeat];
        for (int i = 0; i < this.repeat; i++) {
            result[i] = rand.nextDouble() * (this.range[1] - this.range[0]) + this.range[0];
        }
        return result;
    }

    public float[] randFloat() {
        float[] result;
        result = new float[this.repeat];
        for (int i = 0; i < this.repeat; i++) {
            result[i] = rand.nextFloat() * (float) (this.range[1] - this.range[0]) + (float) this.range[0];
        }
        return result;
    }

    public int[] randInt() {
        int[] result;
        result = new int[this.repeat];
        for (int i = 0; i < this.repeat; i++) {
            result[i] = rand.nextInt((int) (this.range[1] - this.range[0]) + 1) + (int) this.range[0];
        }
        return result;
    }

    public ArrayList<Rule> randRule(ArrayList<Rule> input) {
        ArrayList<Rule> result = new ArrayList<Rule>();
        for (int i = 0; i < this.repeat; i++) {
            int index = rand.nextInt((int) (this.range[1] - this.range[0]) + 1) + (int) this.range[0];
            result.add(input.get(index));
        }
        return result;
    }

    public ArrayList<Piece> randPiece(ArrayList<Piece> input) {
        ArrayList<Piece> result = new ArrayList<Piece>();
        for (int i = 0; i < this.repeat; i++) {
            int index = rand.nextInt((int) (this.range[1] - this.range[0]) + 1) + (int) this.range[0];
            result.add(input.get(index));
        }
        return result;
    }
}