package dynamic_programming.tree_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PartyWithoutBoss {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[][] dp;
    private static int[] happy;
    private static boolean[] hasParent;
    private static void add(int a, int b) {
        graph.get(a).add(b);
    }
    private static void dfs(int u) {
        dp[u][1] = happy[u];
        for (Integer i : graph.get(u)) {
            // 到叶子节点就自动停止递归了
            dfs(i);
            dp[u][0] += Math.max(dp[i][0], dp[i][1]);
            dp[u][1] += dp[i][0];
        }
    }
    public static void main(String[] args) {
        int n = in.nextInt();
        happy = new int[n + 1];
        hasParent = new boolean[n + 1];
        dp = new int[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) happy[i] = in.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            hasParent[a] = true;
            // b是a的直接上司
            add(b, a);
        }
        int root = 1;
        // 找到根节点，即没有父节点的点
        while (hasParent[root]) root++;
        dfs(root);
        out.println(Math.max(dp[root][0], dp[root][1]));
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
