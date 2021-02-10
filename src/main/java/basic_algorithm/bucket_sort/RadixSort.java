package basic_algorithm.bucket_sort;

import java.util.Arrays;
import java.util.Random;

public class RadixSort {
    private static int[] sort(int[] arr) {
        int[] temp = new int[arr.length];
        int[] count = new int[10];
        int mul = 1;
        for (int i = 0; i < 3; i++) {
            mul *= 10;
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / mul % 10;
                count[num]++;
            }
            for (int m = 1; m < count.length; m++) count[m] += count[m - 1];
            for (int k = arr.length - 1; k >= 0; k--) {
                int num = arr[k] / mul % 10;
                temp[--count[num]] = arr[k];
            }
            System.arraycopy(temp, 0, arr, 0, arr.length);
            Arrays.fill(count, 0);
        }
        return arr;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int i1 = random.nextInt(100);
            arr[i] = i1;
        }
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
