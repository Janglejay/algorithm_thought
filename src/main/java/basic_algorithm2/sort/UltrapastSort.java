package basic_algorithm2.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UltrapastSort {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static long res;
    private static void countNumber(int[] arr, int[] temp, int start, int end) {
        if (start >= end) return;
        int mid = start + (end - start) / 2;
        int l = start;
        int r = mid + 1;
        int idx = start;
        countNumber(arr, temp, start, mid);
        countNumber(arr, temp, mid + 1, end);
        while (l <= mid && r <= end) {
            if (arr[l] <= arr[r]) {
                temp[idx++] = arr[l++];
            } else {
                res += mid - l + 1;
                temp[idx++] = arr[r++];
            }
        }
        while (l <= mid) temp[idx++] = arr[l++];
        while (r <= end)  temp[idx++] = arr[r++];
        System.arraycopy(temp, start, arr, start, end - start + 1);
    }
    public static void main(String[] args) {
        int n;
        while ((n = in.nextInt()) != 0) {
            int[] arr = new int[n];
            in.nextIntegerArray(arr);
            res = 0L;
            countNumber(arr, new int[n], 0, n - 1);
            out.println(res);
        }
        out.flush();
        out.close();
    }

    private static class MyWriter {

        private final PrintWriter out;

        private MyWriter() {
            out = new PrintWriter(System.out);
        }

        private void printlnArray(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                out.println(arr[i]);
            }
        }

        private void printArrayJoinSpace(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                out.println(arr[i] + " ");
            }
        }

        private void printlnArray(int[] arr) {
            for (int x : arr) {
                out.println(x);
            }
        }

        private void printArrayJoinSpace(int[] arr) {
            for (int i : arr) {
                out.print(i + " ");
            }
        }

    }

    private static class MyScanner {
        private final BufferedReader br;
        private StringTokenizer st;

        private MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        private String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        private int nextInt() {
            return Integer.parseInt(next());
        }

        private long nextLong() {
            return Long.parseLong(next());
        }

        private double nextDouble() {
            return Double.parseDouble(next());
        }

        private String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        private void nextIntegerArray(int[] arr, int start, int end) {
            for (int i = start; i < end; i++) {
                arr[i] = nextInt();
            }
        }

        private void nextIntegerArray(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = nextInt();
            }
        }
    }
}
