package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BackpackProblemOfDependency {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static List<List<Integer>> graph = new ArrayList<>();
    static Goods[] goods;
    static int[][] dp;
    static int n;
    static int m;
    private static class Goods{
        private int volume;
        private int value;

        public Goods(int volume, int value) {
            this.volume = volume;
            this.value = value;
        }
    }
    private static void add(int a, int b) {
        graph.get(b).add(a);
    }
    private static void dfs(int u) {
        List<Integer> list = graph.get(u);
        for (int x : list) {
            dfs(x);
            //根节点是必选的，说剩下体积就是m-goods[u].volume
            //分组背包过程
            for (int j = m - goods[u].volume; j >= 0; j--) {
                //枚举子树所占[0,j]体积的方案
                for (int k = 0; k <= j; k++) {
                    dp[u][j] = Math.max(dp[u][j], dp[u][j - k] + dp[x][k]);
                }
            }
        }
        //每个方案需要加上本身选择root的价值
        for (int i = m; i >= goods[u].volume; i--) {
            dp[u][i] = dp[u][i - goods[u].volume] + goods[u].value;
        }
        //对于背包体积装不下root的情况，设置为0
        for (int i = 0; i < goods[u].volume; i++) dp[u][i] = 0;
    }
    public static void main(String[] args) {
        n = in.nextInt();
        m = in.nextInt();
        goods = new Goods[n + 1];
        dp = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        int root = -1;
        for (int i = 1; i < n + 1; i++) {
            int volume = in.nextInt();
            int value = in.nextInt();
            int dependence = in.nextInt();
            goods[i] = new Goods(volume, value);
            if (dependence == -1) {
                root = i;
                continue;
            }
            add(i, dependence);
        }
        dfs(root);
        out.println(dp[root][m]);
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
