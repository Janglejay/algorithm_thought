package dynamic_programming2.knapsack_problem;

import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KnapsackProblemSolutionsNumber {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    private static class Goods{
        private int volume;
        private int value;

        public Goods(int volume, int value) {
            this.volume = volume;
            this.value = value;
        }
    }
    public static void main(String[] args) {
        function1();
    }
    private static void function2() {
        int n = in.nextInt();
        int m = in.nextInt();
        int mod = (int)(1e9 + 7);
        Goods[] goods = new Goods[n + 1];
        for (int i = 1; i < n + 1; i++) goods[i] = new Goods(in.nextInt(), in.nextInt());
        int[] dp = new int[m + 1]; int[] g = new int[m + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0; g[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= goods[i].volume; j--) {
                int max = Math.max(dp[j], dp[j - goods[i].volume] + goods[i].value);
                if (max != dp[j]) g[j] = 0;
                if (max == dp[j - goods[i].volume] + goods[i].value) g[j] = (g[j] + g[j - goods[i].volume]) % mod;
                dp[j] = max;
            }
        }
        int max = 0;
        int res = 0;
        for (int j = 0; j < m + 1; j++) max = Math.max(max, dp[j]);
        for (int j = 0; j < m + 1; j++) {
            if (dp[j] == max) {
                res = (res + g[j]) % mod;
            }
        }
        out.println(res);
        out.flush();
        out.close();
    }

    private static void function1() {
        int n = in.nextInt();
        int m = in.nextInt();
        int mod = (int)(1e9 + 7);
        Goods[] goods = new Goods[n + 1];
        for (int i = 1; i < n + 1; i++) goods[i] = new Goods(in.nextInt(), in.nextInt());
        int[][] dp = new int[n + 1][m + 1];
        int[][] g = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) Arrays.fill(dp[i], Integer.MIN_VALUE);
        //从前i个选，体积为0最大值都为0
        for (int i = 0; i < n + 1; i++) dp[i][0] = 0;
        //从前i个选，体积为0，最优选法都是不选，即为一种
        for (int j = 0; j < m + 1; j++) g[0][j] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                int max = dp[i - 1][j];
                if (j >= goods[i].volume) {
                    max = Math.max(max, dp[i - 1][j - goods[i].volume] + goods[i].value);
                }
                if (max == dp[i - 1][j]) g[i][j] = (g[i][j] + g[i - 1][j]) % mod;
                if (j >= goods[i].volume && max == dp[i - 1][j - goods[i].volume] + goods[i].value) g[i][j] = (g[i][j] + g[i - 1][j - goods[i].volume]) % mod;
                dp[i][j] = max;
            }
        }
        int max = 0;
        for (int j = 0; j < m + 1; j++) max = Math.max(max, dp[n][j]);
        int res = 0;
        for (int j = 0; j < m + 1; j++) {
            if (dp[n][j] == max) res = (res + g[n][j]) % mod;
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
