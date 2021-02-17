package dynamic_programming.mnemonic_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Skiing {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    private static int[][] dp;
    private static int[][] map;
    private static int n;
    private static int m;
    private static int[] dx = new int[]{-1, 0, 1, 0};
    private static int[] dy = new int[]{0, 1, 0, -1};
    private static int mnemonicSearch(int x, int y) {
        // 状态已经被算过了
        if (dp[x][y] != -1) {
            return dp[x][y];
        }
        dp[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int bx = x + dx[i];
            int by = y + dy[i];
            if (bx < 0 || by < 0 || bx >= n || by >= m || map[bx][by] >= map[x][y]) continue;
            dp[x][y] = Math.max(dp[x][y], mnemonicSearch(bx, by) + 1);
        }
        return dp[x][y];
    }
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        map = new int[n][m];
        dp = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) in.nextIntegerArray(map[i]);
        // 初始化所有状态，表示所有状态都没被更新过
        for (int i = 0; i < n + 1; i++) Arrays.fill(dp[i], -1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, mnemonicSearch(i, j));
            }
        }
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
