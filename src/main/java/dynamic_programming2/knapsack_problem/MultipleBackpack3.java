package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MultipleBackpack3 {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Goods {
        private int volume;
        private int weight;
        private int number;

        public Goods(int volume, int weight, int number) {
            this.volume = volume;
            this.weight = weight;
            this.number = number;
        }
    }

    public static void main(String[] args) {
//        function1();
        function2();
    }

    private static void function2() {
        int n = in.nextInt();
        int m = in.nextInt();
        Goods[] gs = new Goods[n + 1];
        for (int i = 1; i < n + 1; i++) gs[i] = new Goods(in.nextInt(), in.nextInt(), in.nextInt());
        int[] dp = new int[m + 1];
        int[] bak = new int[m + 1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            System.arraycopy(dp, 0, bak, 0, m + 1);
            int v = gs[i].volume;
            int w = gs[i].weight;
            int number = gs[i].number;
            //r : remainder
            for (int r = 0; r < v; r++) {
                list.clear();
                for (int k = 0; r + k * v <= m; k++) {
                    if (!list.isEmpty() && k - list.getFirst() > number) list.removeFirst();
                    while (!list.isEmpty() &&
                            bak[r + k * v] - k * w >=
                                    bak[r + list.getLast() * v] - list.getLast() * w
                    ) {
                        list.removeLast();
                    }
                    list.add(k);
                    dp[r + k * v] = bak[r + list.getFirst() * v] + ((k - list.getFirst()) * w);
                }
            }
        }
        out.println(dp[m]);
        out.flush();
        out.close();
    }

    private static void function1() {
        int n = in.nextInt();
        int m = in.nextInt();
        Goods[] gs = new Goods[n + 1];
        for (int i = 1; i < n + 1; i++) gs[i] = new Goods(in.nextInt(), in.nextInt(), in.nextInt());
        int[][] dp = new int[n + 1][m + 1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            int v = gs[i].volume;
            int w = gs[i].weight;
            int number = gs[i].number;
            //r : remainder
            for (int r = 0; r < v; r++) {
                list.clear();
                for (int k = 0; r + k * v <= m; k++) {
                    if (!list.isEmpty() && k - list.getFirst() > number) list.removeFirst();
                    while (!list.isEmpty() &&
                            dp[i - 1][r + k * v] - k * w >=
                                    dp[i - 1][r + list.getLast() * v] - list.getLast() * w
                    ) {
                        list.removeLast();
                    }
                    list.add(k);
                    dp[i][r + k * v] = dp[i - 1][r + list.getFirst() * v] + ((k - list.getFirst()) * w);
                }
            }
        }
        out.println(dp[n][m]);
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
