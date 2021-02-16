package dynamic_programming.digit_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CountProblem {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    private static int get(List<Integer> num, int l, int r) {
        int res = 0;
        for (int i = l; i >= r; i--) {
            res = res * 10 + num.get(i);
        }
        return res;
    }

    private static int count(int n, int x) {
        // 题目中 n,x 不可能同时为 0
        if (n == 0) return 0;
        List<Integer> num = new ArrayList<>();
        while (n > 0) {
            num.add(n % 10);
            n /= 10;
        }
        n = num.size();
        int res = 0;
        // 当x=0时，首位不可能为0
        // 所以需要从第二位开始枚举，也就是num的倒数第二位
        for (int i = n - 1 - (x == 0 ? 1 : 0); i >= 0; i--) {
            if (i < n - 1) {
                res += get(num, n - 1, i + 1) * Math.pow(10, i);
                if (x == 0) res -= Math.pow(10, i);
            }
            if (num.get(i) == x) res += get(num, i - 1, 0) + 1;
            else if (num.get(i) > x) res += Math.pow(10, i);
        }
        return res;
    }

    public static void main(String[] args) {
        int n;
        int m;
        while ((n = in.nextInt()) != 0 && (m = in.nextInt()) != 0) {
            if (n > m) {
                int t = n;
                n = m;
                m = t;
            }
            for (int i = 0; i < 10; i++) {
                out.print(count(m, i) - count(n - 1, i) + " ");
            }
            out.println();
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
