package dynamic_programming2.state_machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Stockjobbing5 {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
        function1();
        function2();
    }

    private static void function1() {
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        int[][] dp = new int[n + 1][3];
        dp[0][0] = dp[0][1] = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) arr[i] = in.nextInt();
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - arr[i]);
            dp[i][1] = dp[i - 1][0] + arr[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        out.println(Math.max(dp[n][1], dp[n][2]));
        out.flush();
        out.close();
    }
    private static void function2() {
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        int[] dp = new int[3];
        dp[0] = dp[1] = Integer.MIN_VALUE;
        for (int i = 1; i < n + 1; i++) arr[i] = in.nextInt();
        for (int i = 1; i < n + 1; i++) {
            int t0 = dp[0];
            int t1 = dp[1];
            dp[0] = Math.max(dp[0], dp[2] - arr[i]);
            dp[1] = t0 + arr[i];
            dp[2] = Math.max(dp[2], t1);
        }
        out.println(Math.max(dp[1], dp[2]));
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
