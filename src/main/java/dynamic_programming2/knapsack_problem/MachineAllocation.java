package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MachineAllocation {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;


    private static void function1() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] table = new int[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        int[][] path = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                table[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                for (int k = 0; k < m + 1; k++) {
                    if (j >= k && dp[i][j] < dp[i - 1][j - k] + table[i][k]) {
                        dp[i][j] = dp[i - 1][j - k] + table[i][k];
                        path[i][j] = k;
                    }
                }
            }
        }
        out.println(dp[n][m]);
        int c = m;
        for (int i = n; i >= 1; i--) {
            out.println(i + " " + path[i][c]);
            c -= path[i][c];
        }
        out.flush();
        out.close();
    }
    private static void function2() {
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] table = new int[n + 1][m + 1];
        int[] dp = new int[m + 1];
        int[][] path = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                table[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 0; j--) {
                for (int k = 0; k < m + 1; k++) {
                    if (j >= k && dp[j] < dp[j - k] + table[i][k]) {
                        dp[j] = dp[j - k] + table[i][k];
                        path[i][j] = k;
                    }
                }
            }
        }
        out.println(dp[m]);
        int c = m;
        for (int i = n; i >= 1; i--) {
            out.println(i + " " + path[i][c]);
            c -= path[i][c];
        }
        out.flush();
        out.close();
    }

    public static void main(String[] args) {
//        function1();
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
