package dynamic_programming2.state_machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DesignCode {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    private static int[] getNextArray(char[] cs) {
        int[] next = new int[cs.length];
        for (int i = 2, j = 0; i < cs.length; i++) {
            while (j != 0 && cs[i] != cs[j + 1]) {
                j = next[j];
            }
            if (cs[i] == cs[j + 1]) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    private static int[][] initTable(String str) {
        int m = str.length();
        char[] cs = new char[m + 1];
        int[][] table = new int[m + 1][26];
        System.arraycopy(str.toCharArray(), 0, cs, 1, m);
        int[] nextArray = getNextArray(cs);
        //
        for (int i = 0; i < m; i++) {
            for (char k = 'a'; k <= 'z'; k++) {
                int j = i;
                while (j != 0 && cs[j + 1] != k) j = nextArray[j];
                if (cs[j + 1] == k) j++;
                table[i][k - 'a'] = j;
            }
        }
        return table;
    }
    public static void main(String[] args) {
        int n = in.nextInt();
        String str = in.next();
        int m = str.length();
        final int MOD = (int)(1e9 + 7);
        int[][] table = initTable(str);
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (char k = 'a'; k <= 'z'; k++) {
                for (int j = 0; j <= m; j++) {
                    int u = table[j][k - 'a'];
                    if (u < m) dp[i][u] = (dp[i][u] + dp[i - 1][j]) % MOD;
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= m; i++) res = (res + dp[n][i]) % MOD;
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
