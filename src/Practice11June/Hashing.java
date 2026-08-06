package Practice11June;

import java.util.*;

import static java.lang.Math.max;

public class Hashing {

    public static void main(String[] args) {
        List<Integer> list = List.of(5, 4, 5, 6, 6, 5);
        System.out.println(list);

        int maxInt = Integer.MIN_VALUE;

        for (Integer i : list) {
            if (i > maxInt) {
                maxInt = i;
            }
        }

        // now we have the max int

        int[] hash = new int[maxInt + 1]; // this will initialize the array with 0.
        for (Integer i : list) {
            hash[i]++;
        }

        System.out.println("Hash array: " + Arrays.toString(hash));

        System.out.println(hash[5]);
    }

}
