package dynamic_programming2.states_compressing_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CornField {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    private static int n;
    private static int m;
    //用二进制表示地图
    private static int[] map;
    private static List<Integer> legalState;
    private static List<List<Integer>> legalTransfer;

    private static boolean check(int state) {
        for (int i = 0; i < m; i++) {
            if (((state >> i) & 1) == 1 && (state >> (i + 1) & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    private static void init() {
        legalState = new ArrayList<>();
        legalTransfer = new ArrayList<>();
        for (int i = 0; i < 1 << m; i++) {
            if (check(i)) {
                legalState.add(i);
            }
        }
        for (int i = 0; i < legalState.size(); i++) legalTransfer.add(new ArrayList<>());
        for (int i = 0; i < legalState.size(); i++) {
            for (int j = 0; j < legalState.size(); j++) {
                int a = legalState.get(i);
                int b = legalState.get(j);
                if ((a & b) == 0) {
                    legalTransfer.get(i).add(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        map = new int[n + 2];
        final int MOD = (int)1e8;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int t = in.nextInt();
                //如果t是0代表不能种植，把他存为这一位已经占用了即为1
                map[i] += (t ^ 1) << j;
            }
        }
        init();
        int[][] dp = new int[n + 2][1 << m];
        dp[0][0] = 1;
        for (int i = 1; i <= n + 1; i++) {
            for (int a = 0; a < legalState.size(); a++) {
                int stateA = legalState.get(a);
                for (int b : legalTransfer.get(a)) {
                    int stateB = legalState.get(b);
                    //种植到了不能种植的地方
                    if ((map[i] & stateA) != 0) continue;
                    dp[i][stateA] = (dp[i][stateA] + dp[i - 1][stateB]) % MOD;
                }
            }
        }
        out.println(dp[n + 1][0]);
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
