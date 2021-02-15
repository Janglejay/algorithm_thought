package dynamic_programming.linear_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumericTriangle {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static void function1() {
        int n = in.nextInt();
        int res = Integer.MIN_VALUE;
        int[][] arr = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 2];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][1] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
            }
        }
        for (int i = 1; i < n + 1; i++) {
            res = Math.max(res, dp[n][i]);
        }
        out.println(res);
        out.flush();
        out.close();
    }
    private static void function2() {
        int n = in.nextInt();
        int res = Integer.MIN_VALUE;
        int[][] arr = new int[n + 1][n + 1];
        int[] dp = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                arr[i][j] = in.nextInt();
            }
        }
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[1] = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = i; j >= 1; j--) {
                dp[j] = Math.max(dp[j - 1], dp[j]) + arr[i][j];
            }
        }
        for (int i = 1; i < n + 1; i++) {
            res = Math.max(res, dp[i]);
        }
        out.println(res);
        out.flush();
        out.close();
    }
    public static void main(String[] args) {
        function1();
        function2();
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
