package dynamic_programming2.states_compressing_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class AngryBirds {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static int n;
    private static int m;
    private static int[][] path;
    private static Bird[] birds;
    private static final double EPS = 1e-8;
    private static int compare(double a, double b) {
        if (Math.abs(a - b) <= 1e-8) return 0;
        if (a > b) return 1;
        return -1;
    }
    private static class Bird {
        private double x;
        private double y;

        public Bird(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void initPath() {
        //存储两个点所组成的抛物线，能够覆盖的情况二进制表示
        path = new int[n][n];
        for (int i = 0; i < n; i++) {
            //这条线一定可以覆盖自己
            //能够处理只有一个点的情况
            path[i][i] = 1 << i;
            for (int j = 0; j < n; j++) {
                double x1 = birds[i].x;
                double x2 = birds[j].x;
                double y1 = birds[i].y;
                double y2 = birds[j].y;
                //x1 != x2
                if (compare(x1, x2) == 0) continue;
                double a = (y1 / x1 - y2 / x2) / (x1 - x2);
                double b = y1 / x1 - a * x1;
                // a要<0
                if (compare(a, 0D) >= 0) continue;
                int state = 0;
                //看这条抛物线能够穿过哪些点
                for (int k = 0; k < n; k++) {
                    double x = birds[k].x;
                    double y = birds[k].y;
                    if (compare(a * x * x + b * x, y) == 0) {
                        state += 1 << k;
                    }
                }
                path[i][j] = state;
            }
        }
    }

    public static void main(String[] args) {
        int t = in.nextInt();
        while (t-- > 0) {
            n = in.nextInt();
            m = in.nextInt();
            int[] dp = new int[1 << n];
            Arrays.fill(dp, Integer.MAX_VALUE / 2);
            //状态为0时，不需要抛物线
            dp[0] = 0;
            birds = new Bird[n];
            for (int i = 0; i < n; i++) birds[i] = new Bird(in.nextDouble(), in.nextDouble());
            initPath();
            //i == (1 << n) - 1时就已经全部打到了
            for (int i = 0; i < 1 << n; i++) {
                int x = -1;
                for (int j = 0; j < n; j++) {
                    //找到一个没有被打到的点
                    if (((i >> j) & 1) == 0) {
                        x = j;
                        break;
                    }
                }
                //全是1的情况
                if (x == -1) {
                    break;
                }
                //枚举所有经过x的抛物线
                for (int j = 0; j < n; j++) {
                    // (i | path[x][j])就是选择这条抛物线之后的new_state
                    // 此时状态i可以在选择这条抛物线时更新new_state的状态
                    dp[i | path[x][j]] = Math.min(dp[i | path[x][j]], dp[i] + 1);
                }
            }
            //有n只小猪没有被打
            out.println(dp[(1 << n) - 1]);
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
