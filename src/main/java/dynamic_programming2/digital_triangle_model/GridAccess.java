package dynamic_programming2.digital_triangle_model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GridAccess {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
        int n = in.nextInt();
        int[][] arr = new int[n + 1][n + 1];
        int row;
        int col;
        int val;
        row = in.nextInt();col = in.nextInt();val = in.nextInt();
        while (row != 0 || col != 0 || val != 0) {
            arr[row][col] = val;
            row = in.nextInt();col = in.nextInt();val = in.nextInt();
        }
        int[][][] dp = new int[2 * n + 1][n + 1][n + 1];
        for (int k = 2; k < 2 * n + 1; k++) {
            for (int i = 1; i < n + 1; i++) {
                for (int i2 = 1; i2 < n + 1; i2++) {
                    int j = k - i;
                    int j2 = k - i2;
                    if (j >= 1 && j <= n && j2 >= 1 && j2 <= n) {
                        int add = arr[i][j];
                        if (i != i2) add += arr[i2][j2];
                        dp[k][i][i2] = Math.max(dp[k][i][i2], dp[k - 1][i - 1][i2 - 1] + add);
                        dp[k][i][i2] = Math.max(dp[k][i][i2], dp[k - 1][i][i2 - 1] + add);
                        dp[k][i][i2] = Math.max(dp[k][i][i2], dp[k - 1][i - 1][i2] + add);
                        dp[k][i][i2] = Math.max(dp[k][i][i2], dp[k - 1][i][i2] + add);
                    }
                }
            }
        }
        out.println(dp[2 * n][n][n]);
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
