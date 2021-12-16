package Objects;

import java.util.*;

/* 
Class return an array of generated random elements
*/
public class RNG extends Savable {
    private int seed;
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
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int constant) {
        super();
        this.range = new int[] { constant, constant };
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int[] range) {
        super();
        this.range = range;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(String id, int[] range) {
        super();
        this.range = range;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(int[] range, int repeat) {
        super();
        this.range = range;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(String id, int[] range, int repeat) {
        super(id);
        this.range = range;
        this.seed = -1;
        this.rand = new Random();
    }

    public RNG(String id, int[] range, int seed, int repeat) {
        super(id);
        this.range = range;
        this.seed = seed;
        this.rand = new Random(this.seed);
    }

    public int[] getRange() {
        return this.range;
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

    public double randDouble() {
        return rand.nextDouble() * (this.range[1] - this.range[0]) + this.range[0];
    }

    public float randFloat() {
        return rand.nextFloat() * (float) (this.range[1] - this.range[0]) + (float) this.range[0];
    }

    public int randInt() {
        return rand.nextInt((int) (this.range[1] - this.range[0]) + 1) + (int) this.range[0];
    }
}