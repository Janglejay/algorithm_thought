package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BudgetProgram {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Combination {
        private int price;
        private int value;

        public Combination(int price, int value) {
            this.price = price;
            this.value = value;
        }
    }

    public static void main(String[] args) {
//        function1();
        function2();
    }

    private static class Accessories {
        private int price;
        private int value;

        public Accessories(int price, int value) {
            this.price = price;
            this.value = value;
        }
    }

    //二进制枚举
    private static void function2() {
        int m = in.nextInt();
        int n = in.nextInt();
        int[] dp = new int[m + 1];
        Accessories[] master = new Accessories[n + 1];
        List<Accessories>[] accessories = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) accessories[i] = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            int p = in.nextInt();
            int w = in.nextInt();
            int q = in.nextInt();
            if (q != 0) accessories[q].add(new Accessories(p, w * p));
            else master[i] = new Accessories(p, p * w);
        }
        for (int i = 1; i < n + 1; i++) {
            if (master[i] == null) continue;
            for (int j = m; j >= 0; j--) {
                for (int x = 0; x < (1 << accessories[i].size()); x++) {
                    int price = master[i].price;
                    int value = master[i].value;
                    for (int k = 0; k < accessories[i].size(); k++) {
                        if ((1 & (x >> k)) == 1) {
                            price += accessories[i].get(k).price;
                            value += accessories[i].get(k).value;
                        }
                    }
                    if (j >= price) dp[j] = Math.max(dp[j], dp[j - price] + value);
                }
            }
        }
        out.println(dp[m]);
        out.flush();
        out.close();
    }

    private static void function1() {
        int m = in.nextInt();
        int n = in.nextInt();
        List<Combination>[] combinations = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) combinations[i] = new ArrayList<>();
        int[] dp = new int[m + 1];
        for (int i = 1; i < n + 1; i++) {
            int price = in.nextInt();
            int weight = in.nextInt();
            int p = in.nextInt();
            if (p != 0) {
                List<Combination> list = new ArrayList<>(combinations[p]);
                List<Combination> clist = combinations[p];
                for (Combination c : list) {
                    clist.add(new Combination(c.price + price, c.value + price * weight));
                }
            } else {
                combinations[i].add(new Combination(price, price * weight));
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = m; j >= 0; j--) {
                for (int k = 0; k < combinations[i].size(); k++) {
                    Combination c = combinations[i].get(k);
                    if (j >= c.price) {
                        dp[j] = Math.max(dp[j], dp[j - c.price] + c.value);
                    }
                }
            }
        }
        out.println(dp[m]);
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
