import java.util.*;

public class RNG extends Saveable {
  private int[] range;
  private Random rand;
  private int value;

  public RNG(int[] range) {
    this.range = range;
    this.rand = new Random();
    nextValue();
  }

  public RNG(int constant) {
    this.range = new int[] {constant, constant};
    this.rand = new Random(); 
    nextValue();
  }

  private void nextValue() {
    value = rand.nextInt(this.range[1] - this.range[0] + 1) + this.range[0];
  }

  public int pop() {
    int output = value;
    nextValue();
    return output;
  }

  public int[] getRange() {
    return range;
  }
}
