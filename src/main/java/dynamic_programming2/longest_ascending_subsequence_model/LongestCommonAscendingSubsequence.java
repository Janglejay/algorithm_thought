package dynamic_programming2.longest_ascending_subsequence_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LongestCommonAscendingSubsequence {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
//        int res = function1();
        int res = functino2();
        out.println(res);
        out.flush();
        out.close();
    }

    private static int functino2() {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) a[i] = in.nextInt();
        for (int i = 1; i < n + 1; i++) b[i] = in.nextInt();
        int res = 0;
        int max;
        for (int i = 1; i < n + 1; i++) {
            max = 0;
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (a[i] == b[j]) dp[i][j] = Math.max(dp[i][j], max + 1);
                if (a[i] > b[j]) max = Math.max(max, dp[i - 1][j]);
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    private static int function1() {
        int n = in.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i < n + 1; i++) a[i] = in.nextInt();
        for (int i = 1; i < n + 1; i++) b[i] = in.nextInt();
        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (a[i] == b[j]) {
                    dp[i][j] = Math.max(dp[i][j], 1);
                    for (int k = 1; k < j; k++) {
                        if (b[k] < b[j])
                            dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + 1);
                    }
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
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
