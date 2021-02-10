package basic_algorithm.bucket_sort;

import java.util.Arrays;
import java.util.Random;

public class CountSort {
    private static int[] sort(int[] arr) {
        int[] result = new int[arr.length];
        int[] count = new int[100];
        for (int i = 0; i < arr.length; i++) count[arr[i]]++;
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
        for (int i = arr.length - 1; i >= 0; i--) result[--count[arr[i]]] = arr[i];
        return result;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            int i1 = random.nextInt(100);
            arr[i] = i1;
        }
        System.out.println(Arrays.toString(arr));
        int[] res = sort(arr);
        System.out.println(Arrays.toString(res));
    }
}
