package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Pokemon {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Pet {
        private int number;
        private int power;

        public Pet(int number, int power) {
            this.number = number;
            this.power = power;
        }
    }

    public static void main(String[] args) {
        int number = in.nextInt();
        int power = in.nextInt();
        int n = in.nextInt();
        Pet[] pets = new Pet[n + 1];
        int[][] dp = new int[number + 1][power];
        for (int i = 1; i < n + 1; i++) pets[i] = new Pet(in.nextInt(), in.nextInt());
        for (int i = 1; i < n + 1; i++) {
            for (int j = number; j >= pets[i].number; j--) {
                for (int k = power - 1; k >= pets[i].power; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - pets[i].number][k - pets[i].power] + 1);
                }
            }
        }
        int num = dp[number][power - 1];
        int res = power - 1;
        for (int i = power - 1; i >= 0; i--) {
            if (num == dp[number][i]) res = i;
            else break;
        }
        out.println(num + " " + (power - res));
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
