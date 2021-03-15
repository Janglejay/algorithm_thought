package dynamic_programming2.states_compressing_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ArtilleryPosition {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    private static int n;
    private static int m;
    private static int[] map;
    private static int[] count;
    private static List<Integer> legalState;

    private static int countNumber(int x) {
        int res = 0;
        for (int i = 0; i < m; i++) {
            res += (x >> i) & 1;
        }
        return res;
    }

    private static boolean check(int state) {
        for (int i = 0; i < m; i++) {
            if (
                    ((state >> i) & 1) == 1 && (((state >> (i + 1)) & 1) == 1 || ((state >> (i + 2)) & 1) == 1)
            ) {
                return false;
            }
        }
        return true;
    }

    private static void init() {
        legalState = new ArrayList<>();
        count = new int[1 << m];
        for (int i = 0; i < 1 << m; i++) {
            if (check(i)) {
                legalState.add(i);
                count[i] = countNumber(i);
            }
        }
    }

    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        map = new int[n + 3];
        //用滚动数组进行优化
        //i,i-1必然是一个奇数一个偶数
        int[][][] dp = new int[2][1 << m][1 << m];
        char[][] charArray = new char[n + 1][m];
        for (int i = 1; i <= n; i++) {
            String str = in.next();
            System.arraycopy(str.toCharArray(), 0, charArray[i], 0, m);
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (charArray[i][j] == 'H') {
                    map[i] += 1 << j;
                }
            }
        }
        init();
        for (int i = 1; i <= n + 2; i++) {
            for (int a = 0; a < legalState.size(); a++) {
                int stateA = legalState.get(a);
                for (int b = 0; b < legalState.size(); b++) {
                    int stateB = legalState.get(b);
                    for (int c = 0; c < legalState.size(); c++) {
                        int stateC = legalState.get(c);
                        //第i行和第i-1行有放在不能放的地方
                        if ((map[i] & stateA) != 0 || (map[i - 1] & stateB) != 0) {
                            continue;
                        }
                        //不能有邻边
                        if ((stateA & stateB) == 0 && (stateA & stateC) == 0 && (stateB & stateC) == 0) {
                            dp[i & 1][stateA][stateB] = Math.max(dp[i & 1][stateA][stateB], dp[(i - 1) & 1][stateB][stateC] + count[stateA]);
                        }
                    }
                }
            }
        }
        out.println(dp[(n + 2) & 1][0][0]);
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
