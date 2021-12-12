package objects.test;

import java.util.*;

public class test {
    public static void main(String[] args) {
        ArrayList<Integer> temp = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
            }
        };

        for (int element : temp) {
            System.out.println(element);
        }
    }

}
