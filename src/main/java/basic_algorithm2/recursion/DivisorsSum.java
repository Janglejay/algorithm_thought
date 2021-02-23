package basic_algorithm2.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DivisorsSum {
    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;
    static int mod = 9901;
    private static int fastPower(int a, int b, int q) {
        long res = 1L;
        a %= q;
        while (b > 0) {
            if ((b & 1) == 1) {
                res = res * a % q;
            }
            b >>= 1;
            a = a * a % q;
        }
        return (int) res;
    }
    private static int sum(int p, int k) {
        if (k == 1) return 1;
        if (k % 2 == 0) return (1 + fastPower(p, k / 2, mod)) * sum(p, k / 2) % mod;
        return (sum(p, k - 1) + fastPower(p, k - 1, mod)) % mod;
    }
    public static void main(String[] args) {
        int a = in.nextInt();
        int b = in.nextInt();
        int res = 1;
        // 对a分解质因数
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0) {
                int s = 0;
                while (a % i == 0) {
                    a /= i;
                    s++;
                }
                res = res * sum(i, b * s + 1) % mod;
            }
        }
        if (a > 1) res = res * sum(a, b + 1) % mod;
        if (a == 0) res = 0;
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
