package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TwoDimensionalChargePack {
    private static class Goods {
        private int volume;
        private int weight;
        private int value;

        public Goods(int volume, int weight, int value) {
            this.volume = volume;
            this.weight = weight;
            this.value = value;
        }
    }

    private static void function1() {
        int n = in.nextInt();
        int v = in.nextInt();
        int w = in.nextInt();
        int[][][] dp = new int[n + 1][v + 1][w + 1];
        Goods[] goods = new Goods[n + 1];
        for (int i = 1; i < n + 1; i++) goods[i] = new Goods(in.nextInt(), in.nextInt(), in.nextInt());
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                for (int k = 1; k < w + 1; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= goods[i].volume && k >= goods[i].weight) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - goods[i].volume][k - goods[i].weight] + goods[i].value);
                    }
                }
            }
        }
        out.println(dp[n][v][w]);
        out.flush();
        out.close();
    }
    private static void function2() {
        int n = in.nextInt();
        int v = in.nextInt();
        int w = in.nextInt();
        int[][] dp = new int[v + 1][w + 1];
        Goods[] goods = new Goods[n + 1];
        for (int i = 1; i < n + 1; i++) goods[i] = new Goods(in.nextInt(), in.nextInt(), in.nextInt());
        for (int i = 1; i <= n; i++) {
            for (int j = v; j >= goods[i].volume; j--) {
                for (int k = w; k >= goods[i].weight; k--) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - goods[i].volume][k - goods[i].weight] + goods[i].value);
                }
            }
        }
        out.println(dp[v][w]);
        out.flush();
        out.close();
    }

    public static void main(String[] args) {
//        function1();
        function2();
    }

    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

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
