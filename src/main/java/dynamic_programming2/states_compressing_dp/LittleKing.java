package dynamic_programming2.states_compressing_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LittleKing {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    private static int n;
    private static int m;
    private static int[] count;
    private static List<Integer> legalState;
    private static List<List<Integer>> legalTransfer;

    //x二进制表示中1的个数
    private static int countNumber(int x) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += (x >> i) & 1;
        }
        return res;
    }

    //判断状态是否合法,不能有相邻的1
    private static boolean check(int state) {
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1 && ((state >> (i + 1)) & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    private static void init() {
        count = new int[1 << n];
        legalState = new ArrayList<>();
        legalTransfer = new ArrayList<>();
        //找到所有合法状态
        for (int i = 0; i < 1 << n; i++) {
            if (check(i)) {
                legalState.add(i);
                count[i] = countNumber(i);
            }
        }
        for (int i = 0; i < legalState.size(); i++) {
            legalTransfer.add(new ArrayList<>());
        }
        //枚举所有合法状态，找到合法转移
        for (int i = 0; i < legalState.size(); i++) {
            for (int j = 0; j < legalState.size(); j++) {
                int a = legalState.get(i);
                int b = legalState.get(j);
                if ((a & b) == 0 && check(a | b)) {
                    //存储的下标
                    legalTransfer.get(i).add(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        init();
        long[][][] dp = new long[n + 2][m + 1][1 << n];
        //什么也不放算一种方案
        dp[0][0][0] = 1;
        //枚举到第i+1行
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 0; j <= m; j++) {
                //枚举第i行状态
                for (int a = 0; a < legalState.size(); a++) {
                    int stateA = legalState.get(a);
                    //合法的第i-1行状态
                    for (int b : legalTransfer.get(a)) {
                        int stateB = legalState.get(b);
                        int c = count[stateB];
                        //国王数量不够
                        if (j < c) continue;
                        dp[i][j][stateA] += dp[i - 1][j - c][stateB];
                    }
                }
            }
        }
        out.println(dp[n + 1][m][0]);
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
