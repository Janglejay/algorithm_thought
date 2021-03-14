package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EnergyStone {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Stone implements Comparable<Stone>{
        private int time;
        private int energy;
        private int decrease;

        public Stone(int time, int energy, int decrease) {
            this.time = time;
            this.energy = energy;
            this.decrease = decrease;
        }

        @Override
        public int compareTo(Stone o) {
            return Integer.compare(this.time * o.decrease, this.decrease * o.time);
        }
    }
    public static void main(String[] args) {
        int t = in.nextInt();
        for (int T = 1; T <= t; T++) {
            int n = in.nextInt();
            int m = 0;
            Stone[] stones = new Stone[n + 1];
            for (int i = 1; i < n + 1; i++) {
                int s = in.nextInt();
                int e = in.nextInt();
                int l = in.nextInt();
                stones[i] = new Stone(s, e, l);
                m += s;
            }
            int[] dp = new int[m + 1];
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = 0;
            Arrays.sort(stones, 1, n + 1);
            for (int i = 1; i < n + 1; i++) {
                for (int j = m; j >= stones[i].time; j--) {
                    //注意能量是会随着时间变短的，实际退减时间为(j-stones[i].time)而不是j
                    //因为石头在吃到的瞬间就不会退减了，stones[i].time只是消化时间
                    //能量不能递减到小于0
                    int decreaseTime = j - stones[i].time;
                    dp[j] = Math.max(dp[j], dp[j - stones[i].time] + Math.max(0, stones[i].energy -  decreaseTime * stones[i].decrease));
                }
            }
            int res = 0;
            for (int j = 0; j <= m; j++) res = Math.max(res, dp[j]);
            out.println("Case #" + T +": " + res);
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
