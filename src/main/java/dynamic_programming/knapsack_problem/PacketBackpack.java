package dynamic_programming.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PacketBackpack {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Packet {
        int weight;
        int value;

        public Packet(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    private static int function1() {
        int n = in.nextInt();
        int m = in.nextInt();
        Packet[][] a = new Packet[n][];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            size[i] = s;
            a[i] = new Packet[s];
            for (int j = 0; j < s; j++) {
                a[i][j] = new Packet(in.nextInt(), in.nextInt());
            }
        }
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = 0; k < size[i - 1]; k++) {
                    if (j >= a[i - 1][k].weight)
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - a[i - 1][k].weight] + a[i - 1][k].value);
                }
            }
        }
        return dp[n][m];
    }

    private static int function2() {
        int n = in.nextInt();
        int m = in.nextInt();
        Packet[][] a = new Packet[n][];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            size[i] = s;
            a[i] = new Packet[s];
            for (int j = 0; j < s; j++) {
                a[i][j] = new Packet(in.nextInt(), in.nextInt());
            }
        }
        int[] dp = new int[m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 0; j--) {
                for (int k = 0; k < size[i - 1]; k++) {
                    if (j >= a[i - 1][k].weight)
                        dp[j] = Math.max(dp[j], dp[j - a[i - 1][k].weight] + a[i - 1][k].value);
                }
            }
        }
        return dp[m];
    }


    public static void main(String[] args) {
//        int res = function1();
        int res = function2();
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
