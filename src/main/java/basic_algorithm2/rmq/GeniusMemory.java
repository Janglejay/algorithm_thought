package basic_algorithm2.rmq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GeniusMemory {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class skipTable {
        private int[][] dp;
        private int[] arr;
        public skipTable(int n, int[] arr) {
            int need = (int) (Math.log(n) / Math.log(2)) + 1;
            this.dp = new int[n + 1][need + 1];
            this.arr = arr;
            // 枚举区间长度 2^need
            for (int j = 0; j <= need; j++) {
                // 枚举左端点
                for (int i = 1; i + (1 << j) - 1 <= n; i++) {
                    // 区间里只有一个数，最大值就是它本身
                    if (j == 0) dp[i][j] = arr[i];
                    else dp[i][j] = Math.max(dp[i][j - 1], dp[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
        public int query(int l, int r) {
            int len = r - l + 1;
            int k = (int) (Math.log(len) / Math.log(2));
            return Math.max(dp[l][k], dp[r - (1 << k) + 1][k]);
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) arr[i] = in.nextInt();
        skipTable st = new skipTable(n, arr);
        int m = in.nextInt();
        while (m-- > 0) {
            int l = in.nextInt();
            int r = in.nextInt();
            out.println(st.query(l, r));
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
