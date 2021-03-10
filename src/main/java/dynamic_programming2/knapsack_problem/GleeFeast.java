package dynamic_programming2.knapsack_problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class GleeFeast {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static class Goods {
        private int price;
        private int value;
        private int number;

        public Goods(int price, int value, int number) {
            this.price = price;
            this.value = value;
            this.number = number;
        }
    }

    public static void main(String[] args) {
        int n = in.nextInt();
        int m = in.nextInt();
        Goods[] goods = new Goods[n + 1];
        LinkedList<Integer> list = new LinkedList<>();
        int[] dp = new int[m + 1];
        int[] bak = new int[m + 1];
        for (int i = 1; i <= n; i++) goods[i] = new Goods(in.nextInt(), in.nextInt(), in.nextInt());
        for (int i = 1; i <= n; i++) {
            int value = goods[i].value;
            int price = goods[i].price;
            int number = goods[i].number;
            System.arraycopy(dp, 0, bak, 0, m + 1);
            for (int r = 0; r < price; r++) {
                list.clear();
                for (int k = 0; price * k + r <= m; k++) {
                    if (!list.isEmpty() && k - list.getFirst() > number) list.removeFirst();
                    while (!list.isEmpty() && bak[r + k * price] - k * value >=
                            bak[r + list.getLast() * price] - list.getLast() * value
                    ) {
                        list.removeLast();
                    }
                    list.add(k);
                    dp[r + k * price] = bak[r + list.getFirst() * price] + ((k - list.getFirst()) * value);
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
