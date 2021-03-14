package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MixedBackpack {
    private static class Goods {
        private int volume;
        private int value;
        private int type;

        public Goods(int volume, int value, int type) {
            this.volume = volume;
            this.value = value;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Goods[] goods = new Goods[n + 1];
        int[] dp = new int[m + 1];
        for (int i = 1; i < n + 1; i++) {
            goods[i] = new Goods(in.nextInt(), in.nextInt(), in.nextInt());
        }
        for (int i = 1; i < n + 1; i++) {
            int s = goods[i].type;
            if (s == 0) {
                for (int j = goods[i].volume; j <= m; j++) {
                    dp[j] = Math.max(dp[j], dp[j - goods[i].volume] + goods[i].value);
                }
            } else {
                //01背包看成特殊的多重背包
                if (s == -1) s = 1;
                //二进制打包
                for (int j = 1; s >= j; s -= j, j <<= 1) {
                    //01背包过程
                    for (int k = m; k >= j * goods[i].volume; k--) {
                        dp[k] = Math.max(dp[k], dp[k - j * goods[i].volume] + j * goods[i].value);
                    }
                }
                //打包剩下的
                if (s > 0) {
                    for (int k = m; k >= s * goods[i].volume; k--) {
                        dp[k] = Math.max(dp[k], dp[k - s * goods[i].volume] + s * goods[i].value);
                    }
                }
            }
        }
        out.println(dp[m]);
        out.flush();
        out.close();
    }

    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

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
