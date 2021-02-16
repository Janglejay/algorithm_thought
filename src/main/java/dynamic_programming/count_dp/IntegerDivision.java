package dynamic_programming.count_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IntegerDivision {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
//        function1();
//        function2();
        function3();
    }


    private static void function1() {
        int mod = (int) (1e9 + 7);
        int n = in.nextInt();
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) dp[i][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j - i >= 0)
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - i]) % mod;
                else dp[i][j] = dp[i - 1][j] % mod;
            }
        }
        out.println(dp[n][n]);
        out.flush();
        out.close();
    }

    private static void function2() {
        int mod = (int) (1e9 + 7);
        int n = in.nextInt();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = i; j < n + 1; j++) {
                dp[j] = (dp[j] + dp[j - i]) % mod;
            }
        }
        out.println(dp[n]);
        out.flush();
        out.close();
    }

    private static void function3() {
        int mod = (int) (1e9 + 7);
        int n = in.nextInt();
        int[][] dp = new int[n + 1][n + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (i >= j)
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - j][j]) % mod;
                else dp[i][j] = dp[i - 1][j - 1] % mod;
            }
        }
        int res = 0;
        for (int i = 1; i < n + 1; i++) res = (res + dp[n][i]) % mod;
        out.println(res);
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
