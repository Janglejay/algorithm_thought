package number_theory.combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Combination3 {
    private static class Combination {
        private int mod;

        public void setMod(int mod) {
            this.mod = mod;
        }
        public Combination() {

        }
        private int fastPower(long a, long b, long q) {
            long res = 1L;
            while (b > 0) {
                if ((b & 1) == 1) {
                    res = res * a % q;
                }
                b >>= 1;
                a = a * a % q;
            }
            return (int) res;
        }
        private long com(int a, int b) {
            long res = 1L;
            for (int i = 1, j = a; i <= b; i++, j--) {
                res = res * j % mod;
                res = res * fastPower(i, mod - 2, mod) % mod;
            }
            return (int) res;
        }
        public int lucas(long a, long b) {
            if (a < mod && b < mod) {
                return (int) com((int)a, (int)b);
            }
            return (int) (com((int)(a % mod), (int)(b % mod)) * lucas(a / mod, b / mod) % mod);
        }
    }

    static final MyScanner in = new MyScanner();
    static final MyWriter myOut = new MyWriter();
    static final PrintWriter out = myOut.out;

    public static void main(String[] args) {
        int n = in.nextInt();
        Combination c = new Combination();
        while (n-- > 0) {
            long a = in.nextLong();
            long b = in.nextLong();
            int p = in.nextInt();
            c.setMod(p);
            int res = c.lucas(a, b);
            out.println(res);
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
