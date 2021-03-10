package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Urinatores {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Cylinder {
        private int oxygen;
        private int nitrogen;
        private int weight;

        public Cylinder(int oxygen, int nitrogen, int weight) {
            this.oxygen = oxygen;
            this.nitrogen = nitrogen;
            this.weight = weight;
        }
    }

    private static void function1() {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[][][] dp = new int[q + 1][n + 1][m + 1];
        Cylinder[] cylinders = new Cylinder[q + 1];
        for (int i = 1; i <= q; i++) cylinders[i] = new Cylinder(in.nextInt(), in.nextInt(), in.nextInt());
        for (int i = 0; i < q + 1; i++)
            for (int j = 0; j < n + 1; j++) {
                for (int k = 0; k < m + 1; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE / 2;
                }
            }
        for (int i = 0; i < q + 1; i++) dp[i][0][0] = 0;
        for (int i = 1; i < q + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                for (int k = 0; k < m + 1; k++) {
                    dp[i][j][k] = Math.min(dp[i - 1][j][k],
                            dp[i - 1][Math.max(0, j - cylinders[i].oxygen)][Math.max(0, k - cylinders[i].nitrogen)] + cylinders[i].weight
                    );
                }
            }
        }
        out.println(dp[q][n][m]);
        out.flush();
        out.close();
    }

    private static void function2() {
        int n = in.nextInt();
        int m = in.nextInt();
        int q = in.nextInt();
        int[][] dp = new int[n + 1][m + 1];
        Cylinder[] cylinders = new Cylinder[q + 1];
        for (int i = 1; i <= q; i++) cylinders[i] = new Cylinder(in.nextInt(), in.nextInt(), in.nextInt());
        for (int j = 0; j < n + 1; j++) {
            for (int k = 0; k < m + 1; k++) {
                dp[j][k] = Integer.MAX_VALUE / 2;
            }
        }
        dp[0][0] = 0;
        for (int i = 1; i < q + 1; i++) {
            for (int j = n; j >= 0; j--) {
                for (int k = m; k >= 0; k--) {
                    dp[j][k] = Math.min(dp[j][k],
                            dp[Math.max(0, j - cylinders[i].oxygen)][Math.max(0, k - cylinders[i].nitrogen)] + cylinders[i].weight
                    );
                }
            }
        }
        out.println(dp[n][m]);
        out.flush();
        out.close();
    }

    public static void main(String[] args) {
        function1();
//        function2();
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
