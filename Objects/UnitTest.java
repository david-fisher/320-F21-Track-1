

public class UnitTest {
    public static void main(String[] args){
        int num = 100;
        RNG new_RNG = new RNG(1,new double[]{1,10},num);
        int[] result = new int[2];
        result = new_RNG.ran_int();
        for(int i = 0; i < num; ++i){
            System.out.println(result[i]);

        }
        
    }
}
